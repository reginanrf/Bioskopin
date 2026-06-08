package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class MovieSelectionPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private DefaultTableModel tableModel;

  public MovieSelectionPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
    initTable();
    loadMoviesFromDB();
  }

  private void initTable() {
    tableModel = new DefaultTableModel(
        new Object[][] {},
        new String[] { "ID Film", "Judul Film", "Genre", "Durasi (Menit)" }) {
      @Override
      public boolean isCellEditable(int row, int column) { return false; }
    };
    tableMovies.setModel(tableModel);
    
    // Opsional: Menyembunyikan kolom ID Film dari user interface agar estetik
    tableMovies.getColumnModel().getColumn(0).setMinWidth(0);
    tableMovies.getColumnModel().getColumn(0).setMaxWidth(0);
    tableMovies.getColumnModel().getColumn(0).setWidth(0);
  }

  public void loadMoviesFromDB() {
    tableModel.setRowCount(0);
    try {
        ResultSet rs = parentFrame.getController().getAvailableFilms();
        while (rs.next()) {
            tableModel.addRow(new Object[]{
                rs.getInt("id_film"),
                rs.getString("judul"),
                rs.getString("genre"),
                rs.getInt("durasi_menit")
            });
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal mengambil data film: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableMovies = new javax.swing.JTable();
    btnNext = new javax.swing.JButton();
    jLabelPelanggan = new javax.swing.JLabel();
    txtNamaPelanggan = new javax.swing.JTextField();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    jScrollPane1.setViewportView(tableMovies);

    jLabelPelanggan.setText("Nama Pelanggan:");
    txtNamaPelanggan.setText("Umum");

    btnNext.setText("Selanjutnya (Pilih Jadwal) >");
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
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPelanggan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPelanggan)
                    .addComponent(txtNamaPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
  }

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {
    int selectedRow = tableMovies.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Silakan pilih film terlebih dahulu!", "Peringatan", JOptionPane.WARNING_MESSAGE);
      return;
    }

    int idFilm = (int) tableModel.getValueAt(selectedRow, 0);
    String movieTitle = (String) tableModel.getValueAt(selectedRow, 1);
    String namaPelanggan = txtNamaPelanggan.getText().trim().isEmpty() ? "Umum" : txtNamaPelanggan.getText().trim();

    BookingSession session = parentFrame.getSession();
    session.setIdFilm(idFilm);
    session.setMovieTitle(movieTitle);
    session.setNamaPelanggan(namaPelanggan);

    parentFrame.goToScheduleSelection();
    parentFrame.refreshSchedulePanel();
  }

  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabelPelanggan;
  private javax.swing.JTextField txtNamaPelanggan;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable tableMovies;
}