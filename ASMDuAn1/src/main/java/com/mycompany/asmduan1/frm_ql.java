/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.asmduan1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author AD
 */
public class frm_ql extends javax.swing.JFrame {
    private Timer timer;
    /**
     * Creates new form frm_form
     */
    public frm_ql() {
        initComponents();
        startClock();
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_DangXuat = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn_KetThuc = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_DongHo = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_DangXuat1 = new javax.swing.JButton();
        btn_DangXuat2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        aa = new javax.swing.JMenu();
        mnui_DangXuat = new javax.swing.JMenuItem();
        mnui_Thoat = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mnu_ThongKe = new javax.swing.JMenuItem();
        mnu_QuanLyNhanVien = new javax.swing.JMenuItem();
        mnu_QuanLyThongKH = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnu_DoanhThu = new javax.swing.JMenuItem();
        mnu_DoanhThu1 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_DangXuat.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Exit.png"))); // NOI18N
        btn_DangXuat.setText("ĐĂNG XUẤT");
        btn_DangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangXuatActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resourcesanh/BackGroundKhachSan.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btn_KetThuc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_KetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Stop.png"))); // NOI18N
        btn_KetThuc.setText("KẾT THÚC");
        btn_KetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_KetThucActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Alarm.png"))); // NOI18N

        lbl_DongHo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_DongHo.setText("00:00");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/5-stars (1).png"))); // NOI18N
        jLabel4.setText("jLabel4");

        btn_DangXuat1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_DangXuat1.setText("LƯƠNG ");
        btn_DangXuat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangXuat1ActionPerformed(evt);
            }
        });

        btn_DangXuat2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn_DangXuat2.setText("THỐNG KÊ");
        btn_DangXuat2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DangXuat2ActionPerformed(evt);
            }
        });

        aa.setText("Hệ thống");

        mnui_DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Exit.png"))); // NOI18N
        mnui_DangXuat.setText("Đăng xuất");
        mnui_DangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnui_DangXuatActionPerformed(evt);
            }
        });
        aa.add(mnui_DangXuat);

        mnui_Thoat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        mnui_Thoat.setText("Kết thức");
        mnui_Thoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnui_ThoatActionPerformed(evt);
            }
        });
        aa.add(mnui_Thoat);

        jMenuBar1.add(aa);

        jMenu2.setText("Quản lý");

        mnu_ThongKe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/List.png"))); // NOI18N
        mnu_ThongKe.setText("Quản lý thống kê");
        mnu_ThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_ThongKeActionPerformed(evt);
            }
        });
        jMenu2.add(mnu_ThongKe);

        mnu_QuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N
        mnu_QuanLyNhanVien.setText("Quản lý nhân viên");
        mnu_QuanLyNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_QuanLyNhanVienActionPerformed(evt);
            }
        });
        jMenu2.add(mnu_QuanLyNhanVien);

        mnu_QuanLyThongKH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Users.png"))); // NOI18N
        mnu_QuanLyThongKH.setText("Quản lý thông tin khách hàng");
        mnu_QuanLyThongKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_QuanLyThongKHActionPerformed(evt);
            }
        });
        jMenu2.add(mnu_QuanLyThongKH);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Thống kê");

        mnu_DoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Notes.png"))); // NOI18N
        mnu_DoanhThu.setText("Lương nhân viên");
        mnu_DoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_DoanhThuActionPerformed(evt);
            }
        });
        jMenu3.add(mnu_DoanhThu);

        mnu_DoanhThu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Numbered list.png"))); // NOI18N
        mnu_DoanhThu1.setText("Tổng hợp thống kê");
        mnu_DoanhThu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnu_DoanhThu1ActionPerformed(evt);
            }
        });
        jMenu3.add(mnu_DoanhThu1);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Liên hệ");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_DongHo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addComponent(btn_DangXuat1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_DangXuat2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_KetThuc)
                .addGap(18, 18, 18)
                .addComponent(btn_DangXuat)
                .addContainerGap(155, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_KetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btn_DangXuat1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_DangXuat2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_DongHo))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnui_ThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnui_ThoatActionPerformed
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_mnui_ThoatActionPerformed

    private void btn_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuatActionPerformed
        frm_DangNhap ql = new frm_DangNhap();
        ql.show();
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DangXuatActionPerformed

    private void mnui_DangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnui_DangXuatActionPerformed
        frm_DangNhap ql = new frm_DangNhap();
        ql.show();
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_mnui_DangXuatActionPerformed

    private void btn_KetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_KetThucActionPerformed
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_KetThucActionPerformed

    private void mnu_QuanLyNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_QuanLyNhanVienActionPerformed
        frm_QuanLyNhanVien qlnv = null;
        qlnv = new frm_QuanLyNhanVien(); // Xử lý ngoại lệ ClassNotFoundException
        // Xử lý ngoại lệ SQLException
        // TODO add your handling code here:
        qlnv.setVisible(true); // Hiển thị form frm_QuanLyNhanVien
    }//GEN-LAST:event_mnu_QuanLyNhanVienActionPerformed

    private void mnu_ThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_ThongKeActionPerformed
        QuanLiThongKe qltk = new QuanLiThongKe();
        qltk.show();
        hide();
        // TODO add your handling code here:
    }//GEN-LAST:event_mnu_ThongKeActionPerformed

    private void mnu_QuanLyThongKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_QuanLyThongKHActionPerformed
        frm_QLKH ql = new frm_QLKH();
        ql.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_mnu_QuanLyThongKHActionPerformed

    private void mnu_DoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_DoanhThuActionPerformed
    frm_LuongNV luong = new frm_LuongNV();
    luong.show();
        hide();
// TODO add your handling code here:
    }//GEN-LAST:event_mnu_DoanhThuActionPerformed

    private void mnu_DoanhThu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnu_DoanhThu1ActionPerformed
        QuanLiThongKe qltk = new QuanLiThongKe();
        qltk.show();
        hide();
    }//GEN-LAST:event_mnu_DoanhThu1ActionPerformed

    private void btn_DangXuat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuat1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DangXuat1ActionPerformed

    private void btn_DangXuat2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DangXuat2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_DangXuat2ActionPerformed

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
            java.util.logging.Logger.getLogger(frm_ql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frm_ql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frm_ql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frm_ql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frm_ql().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu aa;
    private javax.swing.JButton btn_DangXuat;
    private javax.swing.JButton btn_DangXuat1;
    private javax.swing.JButton btn_DangXuat2;
    private javax.swing.JButton btn_KetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_DongHo;
    private javax.swing.JMenuItem mnu_DoanhThu;
    private javax.swing.JMenuItem mnu_DoanhThu1;
    private javax.swing.JMenuItem mnu_QuanLyNhanVien;
    private javax.swing.JMenuItem mnu_QuanLyThongKH;
    private javax.swing.JMenuItem mnu_ThongKe;
    private javax.swing.JMenuItem mnui_DangXuat;
    private javax.swing.JMenuItem mnui_Thoat;
    // End of variables declaration//GEN-END:variables
    private void startClock() {
        // Create a timer that fires every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        // Start the timer
        timer.start();
    }
    private void updateClock() {
        // Get the current time and format it
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(new Date());
        // Set the formatted time to lbl_DongHo
        lbl_DongHo.setText(time);
    }
}
