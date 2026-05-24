/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.admin;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import utils.ColorPalette;

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
        

        jPanel1.setBackground(ColorPalette.BACKGROUND); 

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
        jPanel2 = new javax.swing.JPanel();
        jLabelJudul = new javax.swing.JLabel();
        jLabelSubJudul = new javax.swing.JLabel();
        jButtonTambahFilm = new javax.swing.JButton();
        jTextFieldCari = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFilm = new javax.swing.JTable();
        jButtonCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manajemen Film");
        setPreferredSize(new java.awt.Dimension(1366, 768));

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(240, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setPreferredSize(new java.awt.Dimension(1126, 578));

        jLabelJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelJudul.setForeground(new java.awt.Color(255, 255, 255));
        jLabelJudul.setText("Manajemen Film");

        jLabelSubJudul.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelSubJudul.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSubJudul.setText("Kelola data film");

        jButtonTambahFilm.setText("Tambah Film");
        jButtonTambahFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonTambahFilmMouseClicked(evt);
            }
        });

        jTextFieldCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCariKeyReleased(evt);
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

        jButtonCari.setText("Cari");
        jButtonCari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCariMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelJudul)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1084, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTextFieldCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCari)
                                .addGap(201, 201, 201)
                                .addComponent(jButtonTambahFilm))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabelSubJudul)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabelJudul)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelSubJudul)
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonTambahFilm)
                        .addComponent(jButtonCari))
                    .addComponent(jTextFieldCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
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
    private javax.swing.JLabel jLabelJudul;
    private javax.swing.JLabel jLabelSubJudul;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFilm;
    private javax.swing.JTextField jTextFieldCari;
    // End of variables declaration//GEN-END:variables
}
