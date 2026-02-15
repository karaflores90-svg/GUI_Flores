
package adopters;

import config.config;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class browsedogs extends javax.swing.JFrame {

  
    public browsedogs() {
        initComponents();
        setupSidebar();
        setActive(jLabel3);
        loadDogs();
       jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);

    // Column widths
    jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
   jTable1.getColumnModel().getColumn(1).setPreferredWidth(90);
    jTable1.getColumnModel().getColumn(2).setPreferredWidth(120);
    jTable1.getColumnModel().getColumn(3).setPreferredWidth(120);
    jTable1.getColumnModel().getColumn(4).setPreferredWidth(60);
    jTable1.getColumnModel().getColumn(5).setPreferredWidth(60);
    jTable1.getColumnModel().getColumn(6).setPreferredWidth(70);
    jTable1.getColumnModel().getColumn(7).setPreferredWidth(120);
    jTable1.getColumnModel().getColumn(8).setPreferredWidth(100);
   
   // ✅ CENTER HEADER TEXT
    javax.swing.table.JTableHeader header = jTable1.getTableHeader();
    javax.swing.table.DefaultTableCellRenderer headerRenderer =
            (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    
    // Table colors
jTable1.setBackground(java.awt.Color.WHITE);
jTable1.setForeground(java.awt.Color.BLACK);
jTable1.setGridColor(new java.awt.Color(220, 220, 220));

jTable1.setSelectionBackground(new java.awt.Color(230, 230, 250));
jTable1.setSelectionForeground(java.awt.Color.BLACK);

// Header colors
jTable1.getTableHeader().setBackground(java.awt.Color.WHITE);
jTable1.getTableHeader().setForeground(java.awt.Color.BLACK);

// ScrollPane background
jScrollPane1.getViewport().setBackground(java.awt.Color.WHITE);

    }

   private void setupSidebar() {
     
    javax.swing.JLabel[] labels = {
        jLabel6, jLabel3, jLabel4, jLabel5,
       jLabel8 
    };

    for (javax.swing.JLabel lbl : labels) {
        lbl.setOpaque(false); // 👈 NO background initially
        lbl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }
}

   private void resetSidebar() {
    javax.swing.JLabel[] labels = {
        jLabel6, jLabel3, jLabel4, jLabel5,
       jLabel8 
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
 private void loadDogs() {
        try {
            config conf = new config();
            // Only show available dogs
            ResultSet rs = conf.getRecords("SELECT * FROM tbl_Dogs");


            DefaultTableModel model = new DefaultTableModel(
                    new Object[]{"ID", "Photo", "Dog Name", "Breed", "Age", "Sex", "Size", "Health Status", "Action"}, 0);

            while (rs.next()) {
                // ImageIcon for photo
                javax.swing.ImageIcon icon = null;
                String photoPath = rs.getString("photo");
                if (photoPath != null && !photoPath.isEmpty()) {
                    icon = new javax.swing.ImageIcon(
                            new javax.swing.ImageIcon(photoPath).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
                }

                model.addRow(new Object[]{
                        rs.getInt("dog_id"),
                        icon,
                        rs.getString("name"),
                        rs.getString("breed"),
                        rs.getString("age"),
                        rs.getString("sex"),
                        rs.getString("size"),
                        rs.getString("health_status"),
                        "Adopt"
                });
            }

            jTable1.setModel(model);
            jTable1.setRowHeight(50);

            // Set Image column renderer
            jTable1.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public void setValue(Object value) {
                    if (value instanceof javax.swing.ImageIcon) {
                        setIcon((javax.swing.ImageIcon) value);
                        setText("");
                    } else {
                        setIcon(null);
                        super.setValue(value);
                    }
                }
            });

            // Center text for other columns
            javax.swing.table.DefaultTableCellRenderer centerRenderer = new javax.swing.table.DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                if (i != 1 && i != 8) // skip image and action
                    jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            // Action column
            jTable1.getColumnModel().getColumn(8).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
                @Override
                public void setValue(Object value) {
                    if (value != null) {
                        setText("<html><span style='color:green; cursor:pointer;'>" + value.toString() + "</span></html>");
                        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                    }
                }
            });

            // Mouse click for Adopt
            jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    int row = jTable1.rowAtPoint(evt.getPoint());
                    int col = jTable1.columnAtPoint(evt.getPoint());

                    if (col == 8) { // Action column
                        int dogId = (int) jTable1.getValueAt(row, 0);
                        adoptDog(dogId);
                    }
                }
            });
            
            // Set row height for images
        jTable1.setRowHeight(50);

        // Set Photo column renderer to display images
         jTable1.getColumnModel().getColumn(1).setCellRenderer(new javax.swing.table.DefaultTableCellRenderer() {
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

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading dogs: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
private void adoptDog(int dogId) {
        try {
            config conf = new config();

            // Insert adoption application
            String sqlApp = "INSERT INTO tbl_applications (dog_id, adopter_id, status) VALUES (?, ?, 'pending')";
            int adopterId = 1; // replace with logged-in adopter ID
            conf.addRecord(sqlApp, dogId, adopterId);

            // Update dog status to pending
            String sqlDog = "UPDATE tbl_dogs SET status='pending' WHERE dog_id=?";
            conf.addRecord(sqlDog, dogId);

            JOptionPane.showMessageDialog(this, "Adoption request sent!");
            loadDogs(); // refresh table
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adopting dog: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(204, 153, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Browse Dogs");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 160, 30));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("My Application");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 160, 30));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Surrender Dog");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 240, 170, 30));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Dashboard");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 90, 170, 30));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Profile");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 290, 170, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, 460));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Photo", "Dog Name", "Breed", "Age", "Sex", "Size", "Health Status", "Action"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 540, 420));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 460));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        resetSidebar();
      setActive(jLabel3);
        jLabel3.setOpaque(true);
        jLabel3.setBackground(java.awt.Color.WHITE);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
         resetSidebar();
      setActive(jLabel4);
        jLabel4.setOpaque(true);
        jLabel4.setBackground(java.awt.Color.WHITE);
        application myapp = new application();
        myapp.setVisible(true);
          this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        resetSidebar();
      setActive(jLabel5);
        jLabel5.setOpaque(true);
        jLabel5.setBackground(java.awt.Color.WHITE);
        surrender adsurr = new surrender();
        adsurr.setVisible(true);
          this.dispose();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        resetSidebar();
      setActive(jLabel6);
        jLabel6.setOpaque(true);
        jLabel6.setBackground(java.awt.Color.WHITE);
        adoptersDashboard dashboardForm = new adoptersDashboard();
        dashboardForm.setVisible(true);
      this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        resetSidebar();
      setActive(jLabel8);
        jLabel8.setOpaque(true);
        jLabel8.setBackground(java.awt.Color.WHITE);

        adopteracc profileForm = new adopteracc();
        profileForm.setVisible(true);

        // Optionally close the dashboard
        this.dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(browsedogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(browsedogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(browsedogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(browsedogs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new browsedogs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
