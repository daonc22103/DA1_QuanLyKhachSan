/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.asmduan1;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;

import com.QLKhachSan.entity.NhanVien;
import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AD
 */
public class frm_LuongNV extends javax.swing.JFrame {

    /**
     * Creates new form frm_LuongNV
     */
    private List<NhanVien> listsp = new ArrayList<>();

    public frm_LuongNV() {
        initComponents();
        try {
            ketnoi();
            loadTable();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Không thể kết nối CSDL!");
        }
    }

    Statement stm;
    Connection con;

    public void ketnoi() throws ClassNotFoundException, SQLException {
        //dùng để kết nối JDBC của SQL `
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String username = "sa";
        String password = "123456";
        //địa chỉ IP máy chủ
        String host = "localhost";
        //tên của DATABASE cần kết nối
        String database = "QLKHACHSAN";
        //1433: là cổng mặc định của SQL để kết nối ,encrypt=false sử dụng kết nối không mã hoa1
        String uml = "jdbc:sqlserver://" + host + ":1433;database=" + database + ";encrypt=false";
        //con là 1 biến đại diện cho đối tượng kết nối tới CSDL,khởi tạo và gọi từ DriverManager.getConnection
        //biến này cho phép java kết nối và tương tác với CSDL
        con = DriverManager.getConnection(uml, username, password);
        //Statement dùng để thực thi truy vấn SQL 
        stm = con.createStatement();
    }

    public void loadTable() throws SQLException {
        // Câu truy vấn SQL
        String SQL = "SELECT * FROM NHANVIEN"; // Đảm bảo tên bảng là NHANVIEN

        try (PreparedStatement st = con.prepareStatement(SQL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setTenNV(rs.getString("TenNV"));
                nv.setChucVu(rs.getString("ChucVu"));
                nv.setCongViec(rs.getString("CongViec"));
                nv.setNgaySinh(rs.getString("NamSinh"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setLuongNgayCong(rs.getDouble("LuongNgayCong"));
                nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
                nv.setPhuCap(rs.getDouble("PhuCap"));
                nv.setKhauTru(rs.getDouble("KhauTru"));

                listsp.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ CSDL!");
        }

        // Tạo model để hiển thị trong JTable
        String[] header = {"Mã NV", "Tên NV", "Chức vụ", "Lương ngày công", "Lương cơ bản", "Phụ cấp", "Khấu trừ", "Tổng lương"};
        DefaultTableModel model = new DefaultTableModel(header, 0);

        for (NhanVien nv : listsp) {
            Object[] row = {
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getChucVu(),
                nv.getLuongNgayCong(),
                nv.getLuongCoBan(),
                nv.getPhuCap(),
                nv.getKhauTru(),
                nv.getTongLuong() // Thêm tổng lương vào bảng
            };
            model.addRow(row);
        }
        tbl_Data.setModel(model);
    }

    public void timkiem() throws SQLException {
        // Xóa dữ liệu phần tử trong listsp để hiện dữ liệu tìm kiếm
        listsp.clear();

        // Lấy chuỗi tìm kiếm từ form nhập vào
        String chuoi = txt_Tim.getText();

        // Câu truy vấn SQL để tìm kiếm
        String SQL = "SELECT * FROM NHANVIEN WHERE MaNV LIKE ? OR TenNV LIKE ?";

        // Thực thi câu lệnh SQL = PreparedStatement
        PreparedStatement st = con.prepareStatement(SQL);

        // Thiết lập giá trị các tham số trong câu lệnh SQL
        st.setString(1, "%" + chuoi + "%");
        st.setString(2, "%" + chuoi + "%");

        // ExecuteQuery thực thi truy vấn và lấy kết quả gán vào ResultSet
        ResultSet rs = st.executeQuery();

        // Duyệt qua rs và tạo các đối tượng NhanVien, sau đó thêm chúng vào listsp
        while (rs.next()) {
            NhanVien nv = new NhanVien();
            nv.setMaNV(rs.getString("MaNV"));
            nv.setTenNV(rs.getString("TenNV"));
            nv.setChucVu(rs.getString("ChucVu"));
            nv.setLuongNgayCong(rs.getDouble("LuongNgayCong"));
            nv.setLuongCoBan(rs.getDouble("LuongCoBan"));
            nv.setPhuCap(rs.getDouble("PhuCap"));
            nv.setKhauTru(rs.getDouble("KhauTru"));

            listsp.add(nv);
        }

        // Tạo 1 mảng header định dạng tên cho từng cột bên trong tbl_Data (JTable)
        String[] header = {"Mã NV", "Tên NV", "Chức vụ", "Lương ngày công", "Lương cơ bản", "Phụ cấp", "Khấu trừ", "Tổng lương"};
        DefaultTableModel model = new DefaultTableModel(header, 0);

        // Thêm dữ liệu vào model
        for (NhanVien nv : listsp) {
            Object[] row = {
                nv.getMaNV(),
                nv.getTenNV(),
                nv.getChucVu(),
                nv.getCongViec(),
                nv.getNgaySinh(),
                nv.getGioiTinhString(),
                nv.getLuongNgayCong(),
                nv.getLuongCoBan(),
                nv.getPhuCap(),
                nv.getKhauTru(),
                nv.getTongLuong() // Tính tổng lương và đưa vào cột
            };
            model.addRow(row);
        }

        // Đặt model cho JTable để hiển thị dữ liệu
        tbl_Data.setModel(model);
    }

    public void lammoi() throws SQLException {
        // Xóa dữ liệu trong danh sách
        listsp.clear();
        String t = "";
        txt_MaNV.setText(t);
        txt_KhauTru.setText(t);
        txt_NgayCong.setText(t);
        txt_Tim.setText(t);
        // Tải lại dữ liệu từ cơ sở dữ liệu vào bảng tbl_Data
        loadTable();
    }

    public void capnhat() throws SQLException {
        NhanVien nhanvien = getForm();

        // Xác nhận sửa đổi
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật hay không?") == JOptionPane.YES_OPTION) {
            // Câu lệnh SQL chỉ cập nhật các trường khâu trừ và lương ngày công
            String SQL = "UPDATE NHANVIEN SET LuongNgayCong = ?, KhauTru = ? WHERE MaNV = ?";
            try (PreparedStatement st = con.prepareStatement(SQL)) {
                // Thiết lập các tham số cho câu lệnh SQL
                st.setDouble(1, nhanvien.getLuongNgayCong());
                st.setDouble(2, nhanvien.getKhauTru());
                st.setString(3, nhanvien.getMaNV());

                // Thực thi câu lệnh cập nhật
                int rs = st.executeUpdate();
                if (rs > 0) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    lammoi(); // Làm mới bảng dữ liệu
                } else {
                    JOptionPane.showMessageDialog(this, "Cập nhật không thành công");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật dữ liệu: " + ex.getMessage());
            }
        }
    }

    public NhanVien getForm() {
        NhanVien nhanVien = new NhanVien();

        // Lấy các thông tin từ form
        nhanVien.setMaNV(txt_MaNV.getText());

        // Lấy thông tin lương
        try {
            nhanVien.setPhuCap(Double.parseDouble(txt_NgayCong.getText()));
            nhanVien.setKhauTru(Double.parseDouble(txt_KhauTru.getText()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Thông tin lương không hợp lệ. Vui lòng kiểm tra lại.");
            return null; // Hoặc xử lý lỗi theo yêu cầu
        }

        return nhanVien;
    }

    public void showThongTin(int i) {
        NhanVien NV = listsp.get(i);

        // Cập nhật các trường thông tin cơ bản
        txt_MaNV.setText(NV.getMaNV());
        // Cập nhật các thông tin về lương
        txt_NgayCong.setText(String.valueOf(NV.getLuongNgayCong()));
        txt_KhauTru.setText(String.valueOf(NV.getKhauTru()));

        // Cấm sửa mã nhân viên
        txt_MaNV.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Data = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txt_Tim = new javax.swing.JTextField();
        btn_XuatExcel = new javax.swing.JButton();
        btn_Tim = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        btn_CapNhat = new javax.swing.JButton();
        btn_Thoat = new javax.swing.JButton();
        txt_KhauTru = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_NgayCong = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("BẢNG LƯƠNG NHÂN VIÊN");
        jLabel1.setToolTipText("");

        tbl_Data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên NV", "Chức vụ", "Lương ngày công", "Lương cơ bản ", "Phụ cấp", "Khâu trừ ", "Tổng lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_Data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Data);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm nhân viên"));

        btn_XuatExcel.setText("Xuất Excel");
        btn_XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XuatExcelActionPerformed(evt);
            }
        });

        btn_Tim.setText("Tìm");
        btn_Tim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimActionPerformed(evt);
            }
        });

        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_CapNhat.setText("Cập nhật");
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_Thoat.setText("Thoát");
        btn_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btn_Tim)
                .addGap(37, 37, 37)
                .addComponent(btn_CapNhat)
                .addGap(33, 33, 33)
                .addComponent(btn_LamMoi)
                .addGap(18, 18, 18)
                .addComponent(btn_XuatExcel)
                .addGap(18, 18, 18)
                .addComponent(btn_Thoat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_Tim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_XuatExcel)
                    .addComponent(btn_Tim)
                    .addComponent(btn_LamMoi)
                    .addComponent(btn_CapNhat)
                    .addComponent(btn_Thoat))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jLabel2.setText("Khâu trừ ");

        jLabel3.setText("Ngày công");

        jLabel4.setText("Mã NV");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txt_KhauTru, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addGap(26, 26, 26)
                        .addComponent(txt_NgayCong, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 86, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(248, 248, 248))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_KhauTru, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txt_NgayCong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        try {
            capnhat();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_LuongNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void btn_TimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimActionPerformed
        try {
            timkiem();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_LuongNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_TimActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        try {
            lammoi();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_LuongNV.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void tbl_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DataMouseClicked
        int i = tbl_Data.getSelectedRow();
        showThongTin(i);
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_DataMouseClicked

    private void btn_XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XuatExcelActionPerformed

        // Tạo Workbook và Sheet
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("LuongNV");

            // Tạo tiêu đề
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tbl_Data.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(tbl_Data.getColumnName(i));
            }

            // Tạo nội dung
            for (int rowIndex = 0; rowIndex < tbl_Data.getRowCount(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                for (int colIndex = 0; colIndex < tbl_Data.getColumnCount(); colIndex++) {
                    Cell cell = row.createCell(colIndex);
                    Object value = tbl_Data.getValueAt(rowIndex, colIndex);
                    if (value instanceof String) {
                        cell.setCellValue((String) value);
                    } else if (value instanceof Double) {
                        cell.setCellValue((Double) value);
                    } else if (value instanceof Boolean) {
                        cell.setCellValue((Boolean) value);
                    } else {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            // Đặt tên và đường dẫn của file Excel
            String filePath = "LuongNV.xlsx";
            File file = new File(filePath);

            // Lưu Workbook ra file
            try (FileOutputStream fileOut = new FileOutputStream(file)) {
                workbook.write(fileOut);
                JOptionPane.showMessageDialog(this, "Xuất Excel thành công!");

                // Mở file Excel
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                } else {
                    JOptionPane.showMessageDialog(this, "Hệ thống không hỗ trợ mở file tự động.");
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Lỗi khi xuất dữ liệu ra Excel: " + e.getMessage());
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tạo workbook: " + e.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_btn_XuatExcelActionPerformed

    private void btn_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatActionPerformed
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThoatActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frm_LuongNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_LuongNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_LuongNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_LuongNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_LuongNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Thoat;
    private javax.swing.JButton btn_Tim;
    private javax.swing.JButton btn_XuatExcel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Data;
    private javax.swing.JTextField txt_KhauTru;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_NgayCong;
    private javax.swing.JTextField txt_Tim;
    // End of variables declaration//GEN-END:variables
}
