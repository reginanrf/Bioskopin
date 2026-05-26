/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.admin.FilmManagement;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import utils.ColorPalette;
import com.formdev.flatlaf.FlatLightLaf;

/**
 *
 * @author regina
 */
public class FilmManagementFrame extends javax.swing.JFrame {
    
    controllers.FilmController controller = new controllers.FilmController();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FilmManagementFrame.class.getName());

    /**
     * Creates new form FilmManagementFrame
     */
    public FilmManagementFrame() {
        initComponents();
        loadDataTabel();
        this.setLocationRelativeTo(null);
        

        jPanel2.setBackground(ColorPalette.WHITE); 
        jPanel1.setBackground(ColorPalette.SIDEBAR);
        jPanel3.setBackground(ColorPalette.BACKGROUND);


        jButtonTambahFilm.setBackground(ColorPalette.PRIMARY);
        jButtonTambahFilm.setForeground(ColorPalette.WHITE);

        jButtonCari.setBackground(ColorPalette.PRIMARY);
        jButtonCari.setForeground(ColorPalette.WHITE);

        jTableFilm.setBackground(ColorPalette.WHITE);
        jTableFilm.setGridColor(ColorPalette.BORDER);
        jTableFilm.setSelectionBackground(ColorPalette.PRIMARY);
        jTableFilm.setSelectionForeground(ColorPalette.WHITE);
    }
    
    private void loadDataTabel() {
        jTableFilm.setModel(controller.getFilmTableModel());

        Action aksiEdit = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int barisDipilih = Integer.parseInt(e.getActionCommand());
                int idFilm = (int) jTableFilm.getValueAt(barisDipilih, 0);
                
                DialogEditFilm dialogEdit = new DialogEditFilm(FilmManagementFrame.this, true, idFilm);
                dialogEdit.setVisible(true);
                
                String keyword = jTextFieldCari.getText().trim();
                if (keyword.isEmpty()) {
                    loadDataTabel();
                } else {
                    loadDataTabelCari(keyword);
                }
            }
        };

        Action aksiHapus = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int barisDipilih = Integer.parseInt(e.getActionCommand());
                int idFilm = (int) jTableFilm.getValueAt(barisDipilih, 0);
                String judul = (String) jTableFilm.getValueAt(barisDipilih, 1);

                int konfirmasi = JOptionPane.showConfirmDialog(null, 
                        "Apakah Anda yakin ingin menghapus film '" + judul + "'?", 
                        "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                if (konfirmasi == JOptionPane.YES_OPTION) {
                    if (controller.removeFilm(idFilm)) {
                        JOptionPane.showMessageDialog(null, "Film berhasil dihapus!");
                        loadDataTabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal menghapus film.");
                    }
                }
            }
        };

        new utils.ButtonColumn(jTableFilm, 6, aksiEdit, aksiHapus);
    }
    
    private void loadDataTabelCari(String keyword) {
        jTableFilm.setModel(controller.getSearchFilmTableModel(keyword));

        Action aksiEdit = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int barisDipilih = Integer.parseInt(e.getActionCommand());
                int idFilm = (int) jTableFilm.getValueAt(barisDipilih, 0);
                
                DialogEditFilm dialogEdit = new DialogEditFilm(FilmManagementFrame.this, true, idFilm);
                dialogEdit.setVisible(true);
                
                String keyword = jTextFieldCari.getText().trim();
                if (keyword.isEmpty()) {
                    loadDataTabel();
                } else {
                    loadDataTabelCari(keyword);
                }
            }
        };

        Action aksiHapus = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int barisDipilih = Integer.parseInt(e.getActionCommand());
                int idFilm = (int) jTableFilm.getValueAt(barisDipilih, 0);
                String judul = (String) jTableFilm.getValueAt(barisDipilih, 1);

                int konfirmasi = JOptionPane.showConfirmDialog(null, "Hapus film '" + judul + "'?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (konfirmasi == JOptionPane.YES_OPTION) {
                    if (controller.removeFilm(idFilm)) {
                        JOptionPane.showMessageDialog(null, "Film berhasil dihapus!");
                        String keyword = jTextFieldCari.getText().trim();
                        if (keyword.isEmpty()) {
                            loadDataTabel();
                        } else {
                            loadDataTabelCari(keyword);
                        }
                    }
                }
            }
        };

        new utils.ButtonColumn(jTableFilm, 6, aksiEdit, aksiHapus);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        sidebarpanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        sidebarDashboard = new javax.swing.JLabel();
        sidebarFilm = new javax.swing.JLabel();
        sidebarStudio = new javax.swing.JLabel();
        sidebarCalendar = new javax.swing.JLabel();
        sidebarFnB = new javax.swing.JLabel();
        sidebarLogout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabelJudul = new javax.swing.JLabel();
        jLabelSubJudul = new javax.swing.JLabel();
        jButtonTambahFilm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFilm = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldCari = new javax.swing.JTextField();
        jButtonCari = new javax.swing.JButton();
        jLabelJudul1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manajemen Film");

        jPanel1.setBackground(new java.awt.Color(16, 25, 53));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 0));

        sidebarpanel.setBackground(new java.awt.Color(16, 25, 53));
        sidebarpanel.setPreferredSize(new java.awt.Dimension(240, 730));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo_bioskopin.png"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 76));

        sidebarDashboard.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarDashboard.setForeground(new java.awt.Color(239, 239, 239));
        sidebarDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/home.png"))); // NOI18N
        sidebarDashboard.setText("  Dashboard");

        sidebarFilm.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarFilm.setForeground(new java.awt.Color(195, 156, 0));
        sidebarFilm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio-1.png"))); // NOI18N
        sidebarFilm.setText("  Film");

        sidebarStudio.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarStudio.setForeground(new java.awt.Color(239, 239, 239));
        sidebarStudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio.png"))); // NOI18N
        sidebarStudio.setText("  Studio");

        sidebarCalendar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarCalendar.setForeground(new java.awt.Color(204, 204, 204));
        sidebarCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png"))); // NOI18N
        sidebarCalendar.setText("  Jadwal");

        sidebarFnB.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarFnB.setForeground(new java.awt.Color(239, 239, 239));
        sidebarFnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/food.png"))); // NOI18N
        sidebarFnB.setText("  F&B");

        sidebarLogout.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarLogout.setForeground(new java.awt.Color(239, 239, 239));
        sidebarLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout.png"))); // NOI18N
        sidebarLogout.setText("  Logout");

        javax.swing.GroupLayout sidebarpanelLayout = new javax.swing.GroupLayout(sidebarpanel);
        sidebarpanel.setLayout(sidebarpanelLayout);
        sidebarpanelLayout.setHorizontalGroup(
            sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarpanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidebarpanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sidebarDashboard)
                            .addComponent(sidebarFilm)
                            .addComponent(sidebarStudio)
                            .addComponent(sidebarFnB)
                            .addComponent(sidebarCalendar)
                            .addComponent(sidebarLogout))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarpanelLayout.setVerticalGroup(
            sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarpanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(sidebarDashboard)
                .addGap(18, 18, 18)
                .addComponent(sidebarFilm)
                .addGap(18, 18, 18)
                .addComponent(sidebarStudio)
                .addGap(18, 18, 18)
                .addComponent(sidebarCalendar)
                .addGap(18, 18, 18)
                .addComponent(sidebarFnB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 335, Short.MAX_VALUE)
                .addComponent(sidebarLogout)
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidebarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(sidebarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1126, 578));

        jLabelJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelJudul.setForeground(new java.awt.Color(51, 51, 51));
        jLabelJudul.setText("Manajemen Film");

        jLabelSubJudul.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelSubJudul.setForeground(new java.awt.Color(51, 51, 51));
        jLabelSubJudul.setText("Kelola data film");

        jButtonTambahFilm.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButtonTambahFilm.setText("+ Tambah Film");
        jButtonTambahFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonTambahFilmMouseClicked(evt);
            }
        });

        jTableFilm.setBackground(new java.awt.Color(102, 102, 102));
        jTableFilm.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Judul Film", "Genre", "Durasi (Menit)", "Sinopsis", "Posterpath", "Aksi"
            }
        ));
        jTableFilm.setRowHeight(40);
        jScrollPane1.setViewportView(jTableFilm);

        jTextFieldCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariKeyReleased(evt);
            }
        });

        jButtonCari.setText("Cari");
        jButtonCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCariMouseClicked(evt);
            }
        });

        jLabelJudul1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabelJudul1.setForeground(new java.awt.Color(51, 51, 51));
        jLabelJudul1.setText("Cari Film");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelJudul1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonCari)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabelJudul1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCari))
                .addGap(14, 14, 14))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelJudul)
                            .addComponent(jLabelSubJudul))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonTambahFilm))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButtonTambahFilm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabelJudul)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelSubJudul)
                        .addGap(26, 26, 26)))
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 742, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonTambahFilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTambahFilmMouseClicked
        // TODO add your handling code here:
        DialogTambahFilm tambahfilm = new DialogTambahFilm(this, true);
        tambahfilm.setVisible(true);
        loadDataTabel();
    }//GEN-LAST:event_jButtonTambahFilmMouseClicked

    private void jButtonCariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCariMouseClicked
        // TODO add your handling code here:
        String keyword = jTextFieldCari.getText().trim();
   
        if (keyword.isEmpty()) {
            loadDataTabel(); 
        } else {
            loadDataTabelCari(keyword);
        }
    }//GEN-LAST:event_jButtonCariMouseClicked

    private void jTextFieldCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCariKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCariKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FilmManagementFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCari;
    private javax.swing.JButton jButtonTambahFilm;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelJudul;
    private javax.swing.JLabel jLabelJudul1;
    private javax.swing.JLabel jLabelSubJudul;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFilm;
    private javax.swing.JTextField jTextFieldCari;
    private javax.swing.JLabel sidebarCalendar;
    private javax.swing.JLabel sidebarDashboard;
    private javax.swing.JLabel sidebarFilm;
    private javax.swing.JLabel sidebarFnB;
    private javax.swing.JLabel sidebarLogout;
    private javax.swing.JLabel sidebarStudio;
    private javax.swing.JPanel sidebarpanel;
    // End of variables declaration//GEN-END:variables
}
