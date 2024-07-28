/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.asmduan1;

import com.QLKhachSan.entity.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author AD
 */
public class frm_QuanLyNhanVien extends javax.swing.JFrame {

    private List<NhanVien> listsp = new ArrayList<>();

    public frm_QuanLyNhanVien() {
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
        String password = "123";
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
        String SQL = "SELECT * FROM NHANVIEN"; // Đảm bảo tên bảng là NHANVIEN, thay cho SanPham

        try (PreparedStatement st = con.prepareStatement(SQL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.maNV = rs.getString("MaNV");
                nv.tenNV = rs.getString("TenNV");
                nv.chucVu = rs.getString("ChucVu");
                nv.congViec = rs.getString("CongViec");
                nv.ngaySinh = rs.getDate("NamSinh");
                try {
                    nv.gioiTinh = rs.getBoolean("GioiTinh");
                } catch (SQLException ex) {
                    Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, "Error while fetching GioiTinh", ex);
                    // Thêm thông báo lỗi nếu cần
                }
                listsp.add(nv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Lỗi khi tải dữ liệu từ CSDL!");
        }

        // Tạo model để hiển thị trong JTable
        String[] header = {"Mã", "Tên", "Chức vụ", "Công việc", "Ngày sinh", "Giới tính"};
        DefaultTableModel model = new DefaultTableModel(header, 0);
        for (NhanVien nv : listsp) {
            Object[] row = {nv.maNV, nv.tenNV, nv.chucVu, nv.congViec, nv.ngaySinh, nv.gioiTinh};
            model.addRow(row);
        }
        tbl_Data.setModel(model);
    }

    public NhanVien getForm() {
        NhanVien NhanVien = new NhanVien();
        NhanVien.maNV = txt_MaNV.getText();
        NhanVien.tenNV = txt_TenNV.getText();
        NhanVien.chucVu = (String) cbo_ChucVu.getSelectedItem();
        NhanVien.congViec = txt_CongViec.getText();
        NhanVien.ngaySinh = parseDate(txt_NamSinh.getText());
        NhanVien.gioiTinh = rdo_Nam.isSelected();
        //isSelected kiểm tra đã chon hay

        return NhanVien;
    }
    // Ví dụ khởi tạo JComboBox

    public void showThongTin(int i) {
        NhanVien NV = listsp.get(i);
        txt_MaNV.setText(NV.maNV);
        txt_TenNV.setText(NV.tenNV);
        cbo_ChucVu.setSelectedItem(NV.chucVu);

        txt_CongViec.setText(NV.congViec);
        txt_NamSinh.setText(String.valueOf(NV.ngaySinh));
        if (NV.gioiTinh) {
            rdo_Nam.setSelected(true);
            rdo_Nu.setSelected(false);
        } else {
            rdo_Nam.setSelected(false);
            rdo_Nu.setSelected(true);
        }
//        if (NV.chucVu.equalsIgnoreCase("Quản Lý")) {
//            cbo_ChucVu.setSelectedItem("Quản Lý");
//        } else {
//            cbo_ChucVu.setSelectedItem("Lễ Tân");
//        }

        txt_MaNV.setEditable(false); // cấm sửa     
    }

    public void lammoi() throws SQLException {
        listsp.clear();
        String t = "";
        txt_MaNV.setText(t);
        txt_TenNV.setText(t);
        cbo_ChucVu.setSelectedItem(null);
        txt_CongViec.setText(t);
        txt_NamSinh.setText(t);
        buttonGroup1.clearSelection();//Xóa lựa chọn
        txt_MaNV.setEditable(true);
        loadTable();
    }
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    // Phương thức để phân tích ngày từ chuỗi
    // Hoặc định dạng của bạn

    public void them() throws SQLException {
        NhanVien NV = getForm();
        if (JOptionPane.showConfirmDialog(this,
                "Bạn có muốn thêm hay không?") == 0) {
            String SQL = "INSERT INTO NHANVIEN (MaNV, TenNV, ChucVu, CongViec, NamSinh, GioiTinh) VALUES(?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, NV.maNV);
            st.setString(2, NV.tenNV);

            // Lấy giới tính từ combo box
            String chucVu = (String) cbo_ChucVu.getSelectedItem();
            st.setString(3, chucVu);

            st.setString(4, NV.congViec);
            st.setBoolean(5, NV.gioiTinh);

            int rs = st.executeUpdate();
            if (rs > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                lammoi();
                listsp.clear();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công");
            }
        }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txt_TimMaNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_TimTenNV = new javax.swing.JTextField();
        btn_TimKiem = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txt_MaNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_TenNV = new javax.swing.JTextField();
        txt_NamSinh = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rdo_Nam = new javax.swing.JRadioButton();
        rdo_Nu = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        cbo_ChucVu = new javax.swing.JComboBox<>();
        jPasswordField1 = new javax.swing.JPasswordField();
        txt_CongViec = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Data = new javax.swing.JTable();
        btn_Thêm = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_CapNhat = new javax.swing.JButton();
        btn_Thoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setBackground(new java.awt.Color(153, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("QUẢN LÝ NHÂN VIÊN");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm Kiếm"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Mã nhân viên");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên nhân viên");

        btn_TimKiem.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txt_TimMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txt_TimTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btn_TimKiem)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_TimMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_TimTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_TimKiem))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Mã nhân viên");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên nhân viên");

        txt_NamSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_NamSinhActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Năm sinh");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Email");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Công Việc");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Giới tính");

        buttonGroup1.add(rdo_Nam);
        rdo_Nam.setText("Nam");

        buttonGroup1.add(rdo_Nu);
        rdo_Nu.setText("Nữ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Chức vụ");

        cbo_ChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chức vụ" }));

        jPasswordField1.setText("jPasswordField1");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel10))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txt_MaNV)
                    .addComponent(txt_TenNV)
                    .addComponent(txt_NamSinh, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                    .addComponent(cbo_ChucVu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(104, 104, 104)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rdo_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdo_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(txt_CongViec))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_TenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_CongViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_NamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(rdo_Nam)
                    .addComponent(rdo_Nu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbo_ChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        tbl_Data.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã ", "Tên ", "Chức vụ", "CongViec", "Năm sinh", "Giới tính "
            }
        ));
        tbl_Data.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_DataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Data);

        btn_Thêm.setText("Thêm");
        btn_Thêm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThêmActionPerformed(evt);
            }
        });

        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(242, 242, 242)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btn_Thêm)
                .addGap(34, 34, 34)
                .addComponent(btn_LamMoi)
                .addGap(32, 32, 32)
                .addComponent(btn_Xoa)
                .addGap(32, 32, 32)
                .addComponent(btn_CapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Thoat)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Thoat)
                    .addComponent(btn_CapNhat)
                    .addComponent(btn_Xoa)
                    .addComponent(btn_LamMoi)
                    .addComponent(btn_Thêm))
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThoatActionPerformed
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ThoatActionPerformed

    private void txt_NamSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_NamSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_NamSinhActionPerformed

    private void tbl_DataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_DataMouseClicked
        int i = tbl_Data.getSelectedRow();
        showThongTin(i);
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_DataMouseClicked

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        try {
            lammoi();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_ThêmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThêmActionPerformed
        try {
            them();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_ThêmActionPerformed

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        try {
            capnhat();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        try {
            xoa();
            // TODO add your handling code here:
        } catch (SQLException ex) {
            Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_XoaActionPerformed
    public void capnhat() throws SQLException {
        NhanVien nhanvien = getForm();
        if (JOptionPane.showConfirmDialog(this,
                "Bạn có muốn sửa hay không?") == 0) {
            String SQL = "UPDATE NHANVIEN SET TenNV = ?, ChucVu = ?, CongViec = ?, NamSinh = ?,GioiTinh? WHERE masv = ?";
            PreparedStatement st = con.prepareStatement(SQL);
            st.setString(1, nhanvien.tenNV);

            // Lấy giới tính từ combo box
            String chucVu = (String) cbo_ChucVu.getSelectedItem();
            st.setString(3, chucVu);

            st.setString(4, nhanvien.congViec);
            st.setBoolean(5, nhanvien.gioiTinh);

            int rs = st.executeUpdate();
            if (rs > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                lammoi();
                listsp.clear();
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công");
            }
        }
    }
    
    public void xoa() throws SQLException {
        //  if(JOptionPane.showConfirmDialog(this, "bạn có muốn xóa hay không") == 0);
        String SQL = "DELETE NHANVIEN WHERE MaNV = ?";
        PreparedStatement st = con.prepareStatement(SQL);
        st.setString(1, txt_MaNV.getText());
        int rs = st.executeUpdate();
        if (rs > 0) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            lammoi();
            listsp.clear();
            loadTable();
        } else {
            JOptionPane.showMessageDialog(this, "Chưa chọn hàng để xóa");
        }
    }

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
            java.util.logging.Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_QuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JButton btn_Thoat;
    private javax.swing.JButton btn_Thêm;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbo_ChucVu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdo_Nam;
    private javax.swing.JRadioButton rdo_Nu;
    private javax.swing.JTable tbl_Data;
    private javax.swing.JTextField txt_CongViec;
    private javax.swing.JTextField txt_MaNV;
    private javax.swing.JTextField txt_NamSinh;
    private javax.swing.JTextField txt_TenNV;
    private javax.swing.JTextField txt_TimMaNV;
    private javax.swing.JTextField txt_TimTenNV;
    // End of variables declaration//GEN-END:variables

    private Date parseDate(String text) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
