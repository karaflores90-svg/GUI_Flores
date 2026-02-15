/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import admin.adminDashboard;
import admin.manageDogs;      
import admin.adoptersDashboard;
import admin.adoptionDashboard;
import javax.swing.JOptionPane;

public class userTable extends javax.swing.JFrame {

    /**
     * Creates new form userTable
     */
    public userTable() {
        initComponents();
        setupSidebar();
        displayUser();
        setupLiveSearch();
        setActive(jLabel7);
    }
    
    public void displayUser() {
    config.config conf = new config.config();
    String sql = "SELECT * FROM tbl_account";
    conf.displayData(sql, table_users);
    
    }
    private void searchUser() {
    String keyword = jTextField1.getText().trim();

    config.config conf = new config.config();
    String sql;

    if (keyword.isEmpty()) {
        sql = "SELECT * FROM tbl_account";
    } else {
        sql = "SELECT * FROM tbl_account WHERE " +
              "full_name LIKE '%" + keyword + "%' OR " +
              "email LIKE '%" + keyword + "%' OR " +
              "contact LIKE '%" + keyword + "%'";
    }

    conf.displayData(sql, table_users);
}
        
     private void setupSidebar() {
     
    javax.swing.JLabel[] labels = {
        jLabel3, jLabel4, jLabel5, jLabel6,
        jLabel7, jLabel8
    };
    for (javax.swing.JLabel lbl : labels) {
        lbl.setOpaque(false); // 👈 NO background initially
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}
    private void resetSidebar() {
    javax.swing.JLabel[] labels = {
        jLabel3, jLabel4, jLabel5, jLabel6,
        jLabel7, jLabel8
    };

    for (javax.swing.JLabel lbl : labels) {
        lbl.setOpaque(false);   // remove background
        lbl.repaint();
    }
    
    }
    
    private void setActive(javax.swing.JLabel label) {
    resetSidebar();
    label.setOpaque(true);
    label.setBackground(java.awt.Color.WHITE);
    label.repaint();
}
    
    private int getSelectedUserId() {
    int row = table_users.getSelectedRow();
    if (row == -1) {
        javax.swing.JOptionPane.showMessageDialog(this, "Please select a record first.");
        return -1;
    }
    return Integer.parseInt(table_users.getValueAt(row, 0).toString()); // ID column
}
    
    private void setupLiveSearch() {
    jTextField1.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {

        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            searchUser();
        }

        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            searchUser();
        }

        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            searchUser();
        }
    });
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_users = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(204, 153, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dashboard");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 160, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Manage Dogs");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 160, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Adopters");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 190, 170, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Adoption");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 240, 170, 30));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Users");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 290, 170, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Account");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 160, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 460));

        jButton1.setBackground(new java.awt.Color(204, 153, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 70, 30));

        jButton2.setBackground(new java.awt.Color(204, 153, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton2.setText("DELETE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 90, 30));

        jButton3.setBackground(new java.awt.Color(204, 153, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 90, 30));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 150, 30));

        jButton4.setBackground(new java.awt.Color(204, 153, 255));
        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton4.setText("SEARCH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 10, 80, 30));

        table_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(table_users);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 620, 400));

        jButton6.setBackground(new java.awt.Color(204, 153, 255));
        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton6.setText("REFRESH");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 90, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        resetSidebar();
        setActive(jLabel3);
        jLabel3.setOpaque(true);
        jLabel3.setBackground(java.awt.Color.WHITE);
        
    adminDashboard ad = new adminDashboard();
    ad.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        resetSidebar();
        setActive(jLabel4);
        jLabel4.setOpaque(true);
        jLabel4.setBackground(java.awt.Color.WHITE);
        manageDogs ad = new manageDogs();
        ad.setVisible(true);
        this.dispose();   
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        resetSidebar();
         setActive(jLabel5);
        jLabel5.setOpaque(true);
        jLabel5.setBackground(java.awt.Color.WHITE);
    adoptersDashboard adp = new adoptersDashboard();
    adp.setVisible(true);
    this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        resetSidebar();
         setActive(jLabel6);
        jLabel6.setOpaque(true);
        jLabel6.setBackground(java.awt.Color.WHITE);
         adoptionDashboard ad = new adoptionDashboard();
        ad.setVisible(true);
        this.dispose();
      
        
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        resetSidebar();
         setActive(jLabel7);
        jLabel7.setOpaque(true);
        jLabel7.setBackground(java.awt.Color.WHITE);
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        resetSidebar();
        jLabel8.setOpaque(true);
        jLabel8.setBackground(java.awt.Color.WHITE);
        accounts acc = new accounts();
        acc.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
                                         
    addUser addUserFrame = new addUser();
    addUserFrame.setVisible(true);
    
    
    this.dispose();  


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
                                                                               
  int row = table_users.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Please select a user first.");
        return;
    }

    int id = Integer.parseInt(table_users.getValueAt(row, 0).toString());
    String fullName = table_users.getValueAt(row, 1).toString();
    String email = table_users.getValueAt(row, 2).toString();
    String contact = table_users.getValueAt(row, 3).toString();
    String password = table_users.getValueAt(row, 4).toString();
    String role = table_users.getValueAt(row, 5).toString();
    String status = table_users.getValueAt(row, 6).toString();

    // Open updateUser JFrame
    updateUser updateFrame = new updateUser(id, fullName, email, contact, password, role, status);
    updateFrame.setVisible(true);
    this.dispose();


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                                        
    int id = getSelectedUserId();
    if(id == -1) return;

    int confirm = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to delete this user?",
        "Confirm Delete",
        javax.swing.JOptionPane.YES_NO_OPTION
    );

    if(confirm != javax.swing.JOptionPane.YES_OPTION) return;

    config.config conf = new config.config();
    String sql = "DELETE FROM tbl_account WHERE u_id=?";
    conf.addRecord(sql, id);

    displayUser();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                                         
    String keyword = jTextField1.getText();
    config.config conf = new config.config();
    String sql = "SELECT * FROM tbl_account WHERE full_name LIKE '%" + keyword + "%' OR email LIKE '%" + keyword + "%' OR contact LIKE '%" + keyword + "%'";
    conf.displayData(sql, table_users);

    jTextField1.setText("");
    
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
    displayUser();
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(userTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userTable.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userTable().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable table_users;
    // End of variables declaration//GEN-END:variables
}
