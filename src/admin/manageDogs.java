/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author elinapajo
 */
public class manageDogs extends javax.swing.JFrame {

    /**
     * Creates new form manageDogs
     */
    public manageDogs() {
        initComponents();
         setupSidebar();
        setActive(jLabel12);
         loadDogs();
        DogTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    // Column widths
    DogTable.getColumnModel().getColumn(0).setPreferredWidth(50);
    DogTable.getColumnModel().getColumn(1).setPreferredWidth(90);
    DogTable.getColumnModel().getColumn(2).setPreferredWidth(120);
    DogTable.getColumnModel().getColumn(3).setPreferredWidth(120);
    DogTable.getColumnModel().getColumn(4).setPreferredWidth(60);
    DogTable.getColumnModel().getColumn(5).setPreferredWidth(60);
    DogTable.getColumnModel().getColumn(6).setPreferredWidth(70);
    DogTable.getColumnModel().getColumn(7).setPreferredWidth(120);
    DogTable.getColumnModel().getColumn(8).setPreferredWidth(100);
    DogTable.getColumnModel().getColumn(9).setPreferredWidth(100);
    DogTable.getColumnModel().getColumn(10).setPreferredWidth(100);

    // ✅ CENTER HEADER TEXT
    javax.swing.table.JTableHeader header = DogTable.getTableHeader();
    javax.swing.table.DefaultTableCellRenderer headerRenderer =
            (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    
    // Table colors
DogTable.setBackground(java.awt.Color.WHITE);
DogTable.setForeground(java.awt.Color.BLACK);
DogTable.setGridColor(new java.awt.Color(220, 220, 220));

DogTable.setSelectionBackground(new java.awt.Color(230, 230, 250));
DogTable.setSelectionForeground(java.awt.Color.BLACK);

// Header colors
DogTable.getTableHeader().setBackground(java.awt.Color.WHITE);
DogTable.getTableHeader().setForeground(java.awt.Color.BLACK);

// ScrollPane background
jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);

    }
      
   private void setupSidebar() {
     
    javax.swing.JLabel[] labels = {
        jLabel11, jLabel12, jLabel13, jLabel14,
        jLabel15, jLabel16 
    };

    for (javax.swing.JLabel lbl : labels) {
        lbl.setOpaque(false); // 👈 NO background initially
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}
    private void resetSidebar() {
    javax.swing.JLabel[] labels = {
        jLabel11, jLabel12, jLabel13, jLabel14,
        jLabel15, jLabel16
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
 public void loadDogs() {
    try {
        config.config conf = new config.config();
        ResultSet rs = conf.getRecords("SELECT * FROM tbl_Dogs");
        DefaultTableModel model = (DefaultTableModel) DogTable.getModel();
        model.setRowCount(0); // clear existing rows

        while(rs.next()) {
            // Convert photo path to scaled ImageIcon
            String photoPath = rs.getString("photo");
            javax.swing.ImageIcon icon = null;
            if (photoPath != null && !photoPath.isEmpty()) {
                icon = new javax.swing.ImageIcon(
                    new javax.swing.ImageIcon(photoPath).getImage()
                    .getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)
                );
            }

            model.addRow(new Object[]{
                rs.getInt("dog_id"),
                icon, // ImageIcon object
                rs.getString("name"),
                rs.getString("breed"),
                rs.getString("age"),
                rs.getString("sex"),
                rs.getString("size"),
                rs.getString("health_status"),
                rs.getString("status"),
                rs.getString("date_added"),
                "Edit | Delete"
            });
        }

        // Set row height for images
        DogTable.setRowHeight(50);

        // Set Photo column renderer to display images
        DogTable.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                if (value instanceof javax.swing.ImageIcon) {
                    setIcon((javax.swing.ImageIcon) value);
                    setText(""); // no text
                } else {
                    setIcon(null);
                    super.setValue(value);
                }
            }
            
        });

    } catch(Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(this, "Error loading dogs: " + ex.getMessage());
        ex.printStackTrace();
    }
    // Center cell content for all columns
javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

for (int i = 0; i < DogTable.getColumnCount(); i++) {
    // Skip the photo column (already has ImageIcon renderer)
    if (i == 1) continue;
    DogTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
}

// Custom renderer for "Actions" column (last column)
DogTable.getColumnModel().getColumn(10).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
    @Override
    public void setValue(Object value) {
        if (value != null) {
            String text = value.toString();
            String html = text.replace("Edit", "<span style='color:purple;'>Edit</span>")
                              .replace("Delete", "<span style='color:red;'>Delete</span>");
            setText("<html>" + html + "</html>");
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        } else {
            setText("");
        }
    }
});




}


 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DogTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Dashboard");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 160, 30));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Manage Dogs");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 160, 30));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Adopters");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 190, 170, 30));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Adoption");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 240, 170, 30));

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Users");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 290, 170, 30));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Account");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 160, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 460));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("Filter: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 90, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Dogs", "Surrendered", "Adopted" }));
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 120, 30));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Search: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 90, 30));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 190, 30));

        jButton1.setBackground(new java.awt.Color(204, 153, 255));
        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Add Dog");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 90, 30));

        DogTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Photo", "Dog Name", "Breed", "Age", "Sex", "Size", "Health Status", "Status", "Date Added", "Actions"
            }
        ));
        DogTable.setToolTipText("");
        jScrollPane1.setViewportView(DogTable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 570, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        resetSidebar();
        setActive(jLabel11);
        jLabel11.setOpaque(true);
        jLabel11.setBackground(java.awt.Color.WHITE);
        adminDashboard admin = new adminDashboard();
       admin.setVisible(true);
       this.dispose(); 
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        resetSidebar();
        setActive(jLabel12);
        jLabel12.setOpaque(true);
        jLabel12.setBackground(java.awt.Color.WHITE);
        manageDogs ad = new manageDogs();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        resetSidebar();
        setActive(jLabel13);
        jLabel13.setOpaque(true);
        jLabel13.setBackground(java.awt.Color.WHITE);
        adoptersDashboard ad = new adoptersDashboard();
         ad.setVisible(true);
         this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        resetSidebar();
        setActive(jLabel14);
        jLabel14.setOpaque(true);
        jLabel14.setBackground(java.awt.Color.WHITE);
        adoptionDashboard ad = new adoptionDashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        resetSidebar();
        setActive(jLabel15);
        jLabel15.setOpaque(true);
        jLabel15.setBackground(java.awt.Color.WHITE);
        userTable ut = new userTable();
        ut.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel15MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        resetSidebar();
        setActive(jLabel16);
        jLabel16.setOpaque(true);
        jLabel16.setBackground(java.awt.Color.WHITE);

        accounts acc = new accounts();
        acc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dogForm form = new dogForm(); 
    form.setVisible(true); 
    this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(manageDogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageDogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageDogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageDogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manageDogs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DogTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
