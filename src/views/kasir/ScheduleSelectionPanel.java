package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author riikq
 */
public class ScheduleSelectionPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private DefaultTableModel tableModel;

  public ScheduleSelectionPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
    initTable();
  }

  private void initTable() {
    tableModel = new DefaultTableModel(
        new Object[][] {},
        new String[] { "Studio", "Jam Tayang", "Kapasitas", "Harga (Rp)" }) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tableSchedules.setModel(tableModel);
  }

  // This gets called by MainBookingFrame right before showing this panel
  public void loadSchedulesForSelectedMovie() {
    tableModel.setRowCount(0); // Clear old data

    BookingSession session = parentFrame.getSession();
    lblMovieInfo.setText("Jadwal untuk film: " + session.getMovieTitle());

    // MOCK DATA: In reality, run a SELECT query WHERE movie_title =
    // session.getMovieTitle()
    if (session.getMovieTitle().equals("Avengers: Endgame")) {
      tableModel.addRow(new Object[] { "Studio 1", "14:00", 60, 50000 });
      tableModel.addRow(new Object[] { "Studio 2", "18:30", 40, 50000 });
    } else {
      tableModel.addRow(new Object[] { "Studio 3", "16:00", 50, 45000 });
      tableModel.addRow(new Object[] { "Studio 1", "20:00", 60, 45000 });
    }
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    lblMovieInfo = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableSchedules = new javax.swing.JTable();
    btnBack = new javax.swing.JButton();
    btnNext = new javax.swing.JButton();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel1.setText("Langkah 2: Pilih Jadwal & Studio");

    lblMovieInfo.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
    lblMovieInfo.setForeground(new java.awt.Color(102, 102, 102));
    lblMovieInfo.setText("Jadwal untuk film: -");

    jScrollPane1.setViewportView(tableSchedules);

    btnBack.setText("< Kembali");
    btnBack.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBackActionPerformed(evt);
      }
    });

    btnNext.setText("Selanjutnya (Pilih Kursi) >");
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(lblMovieInfo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMovieInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
    parentFrame.goToMovieSelection(); // Send user back if they want to change the movie
  }// GEN-LAST:event_btnBackActionPerformed

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
    int selectedRow = tableSchedules.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Silakan pilih jadwal dan studio terlebih dahulu!", "Peringatan",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    // 1. Extract data from table
    String studioName = (String) tableModel.getValueAt(selectedRow, 0);
    String scheduleTime = (String) tableModel.getValueAt(selectedRow, 1);
    int kapasitasStudio = (int) tableModel.getValueAt(selectedRow, 2);
    int price = (int) tableModel.getValueAt(selectedRow, 3);

    // 2. Save details to Session Cart
    BookingSession session = parentFrame.getSession();
    session.setStudioName(studioName);
    session.setScheduleTime(scheduleTime);
    session.setMoviePrice(price);

    // 3. Move to Seat Selection and pass the capacity
    parentFrame.goToSeatSelection();
    parentFrame.refreshSeatPanel(kapasitasStudio);
  }// GEN-LAST:event_btnNextActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:initComponents
  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblMovieInfo;
  private javax.swing.JTable tableSchedules;
  // End of variables declaration//GEN-END:initComponents
}
