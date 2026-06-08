package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

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
        new String[] { "ID Jadwal", "Nama Studio", "Tanggal", "Jam Tayang", "Baris", "Kolom", "Harga (Rp)" }) {
      @Override
      public boolean isCellEditable(int row, int column) { return false; }
    };
    tableSchedules.setModel(tableModel);
    
    // Sembunyikan ID Jadwal, Baris, dan Kolom dari User Kasir
    int[] hiddenCols = {0, 4, 5};
    for (int colIndex : hiddenCols) {
        tableSchedules.getColumnModel().getColumn(colIndex).setMinWidth(0);
        tableSchedules.getColumnModel().getColumn(colIndex).setMaxWidth(0);
        tableSchedules.getColumnModel().getColumn(colIndex).setWidth(0);
    }
  }

  public void loadSchedulesForSelectedMovie() {
    tableModel.setRowCount(0);
    BookingSession session = parentFrame.getSession();
    lblMovieInfo.setText("Jadwal untuk film: " + session.getMovieTitle() + " (Pelanggan: " + session.getNamaPelanggan() + ")");

    try {
        ResultSet rs = parentFrame.getController().getSchedulesByFilm(session.getIdFilm());
        while (rs.next()) {
            tableModel.addRow(new Object[] {
                rs.getInt("id_jadwal"),
                rs.getString("nama_studio"),
                rs.getDate("tanggal_tayang").toString(),
                rs.getTime("jam_tayang").toString(),
                rs.getInt("jumlah_baris"),
                rs.getInt("jumlah_kolom"),
                rs.getDouble("harga_tiket")
            });
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat jadwal: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    lblMovieInfo = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableSchedules = new javax.swing.JTable();
    btnBack = new javax.swing.JButton();
    btnNext = new javax.swing.JButton();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    jLabel1.setText("Langkah 2: Pilih Jadwal & Studio");
    lblMovieInfo.setFont(new java.awt.Font("Segoe UI", 2, 14)); 
    lblMovieInfo.setForeground(new java.awt.Color(102, 102, 102));
    lblMovieInfo.setText("Jadwal untuk film: -");
    jScrollPane1.setViewportView(tableSchedules);

    btnBack.setText("< Kembali");
    btnBack.addActionListener(evt -> parentFrame.goToMovieSelection());

    btnNext.setText("Selanjutnya (Pilih Kursi) >");
    btnNext.addActionListener(evt -> btnNextActionPerformed(evt));

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
  }

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {
    int selectedRow = tableSchedules.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Silakan pilih jadwal terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
      return;
    }

    int idJadwal = (int) tableModel.getValueAt(selectedRow, 0);
    String studioName = (String) tableModel.getValueAt(selectedRow, 1);
    String jamTayang = (String) tableModel.getValueAt(selectedRow, 3);
    int baris = (int) tableModel.getValueAt(selectedRow, 4);
    int kolom = (int) tableModel.getValueAt(selectedRow, 5);
    double hargaTiket = (double) tableModel.getValueAt(selectedRow, 6);

    BookingSession session = parentFrame.getSession();
    session.setIdJadwal(idJadwal);
    session.setNamaStudio(studioName);
    session.setJamTayang(jamTayang);
    session.setHargaTiket(hargaTiket);

    parentFrame.goToSeatSelection();
    parentFrame.refreshSeatPanel(baris, kolom);
  }

  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblMovieInfo;
  private javax.swing.JTable tableSchedules;
}