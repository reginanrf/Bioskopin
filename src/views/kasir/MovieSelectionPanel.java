package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.ColorPalette;

/**
 * @author riikq
 */
public class MovieSelectionPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private DefaultTableModel tableModel;

  public MovieSelectionPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
    initTable();
    loadMockMoviesFromDB();
    setBackground(ColorPalette.BACKGROUND);
  }

  private void initTable() {
    tableModel = new DefaultTableModel(
        new Object[][] {},
        new String[] { "ID", "Judul Film", "Genre", "Durasi (Menit)" }) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tableMovies.setModel(tableModel);
  }

  private void loadMockMoviesFromDB() {
    // MOCK DATA: Replace this with your actual Database SELECT query for movies
    tableModel.addRow(new Object[] { 1, "Avengers: Endgame", "Action", 181 });
    tableModel.addRow(new Object[] { 2, "Interstellar", "Sci-Fi", 169 });
    tableModel.addRow(new Object[] { 3, "The Conjuring", "Horror", 112 });
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableMovies = new javax.swing.JTable();
    btnNext = new javax.swing.JButton();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel1.setText("Langkah 1: Pilih Film");

    jScrollPane1.setViewportView(tableMovies);

    btnNext.setText("Selanjutnya (Pilih Jadwal) >");
    btnNext.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNextActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnNext)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
    int selectedRow = tableMovies.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Silakan pilih film terlebih dahulu!", "Peringatan",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    // 1. Get selected movie details
    int movieId = (int) tableModel.getValueAt(selectedRow, 0);
    String movieTitle = (String) tableModel.getValueAt(selectedRow, 1);

    // 2. Save to Session
    BookingSession session = parentFrame.getSession();
    session.setMovieTitle(movieTitle);

    // 3. Navigate to next panel and tell it to load schedules for this movie
    parentFrame.goToScheduleSelection();
    parentFrame.refreshSchedulePanel(); // We will add this helper to the Main Frame later
  }// GEN-LAST:event_btnNextActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:initComponents
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tableMovies;
  // End of variables declaration//GEN-END:initComponents
}
