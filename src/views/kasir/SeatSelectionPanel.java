package views.kasir;

import models.BookingSession;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SeatSelectionPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private final ArrayList<String> selectedSeats = new ArrayList<>();

  public SeatSelectionPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
  }

  public void loadSeatGrid(int rows, int cols) {
    selectedSeats.clear();
    lblSelectedSeatsSummary.setText("Kursi Dipilih: []");

    jPanelGridContainer.setLayout(new GridLayout(rows, cols, 6, 6));
    jPanelGridContainer.removeAll();

    // Mengambil daftar nomor_kursi yang sudah terisi di database untuk id_jadwal ini
    ArrayList<String> bookedSeatsList = new ArrayList<>();
    try {
        bookedSeatsList = parentFrame.getController().getBookedSeats(parentFrame.getSession().getIdJadwal());
    } catch (Exception e) {
        e.printStackTrace();
    }

    for (int r = 0; r < rows; r++) {
      char rowLetter = (char) ('A' + r);
      for (int c = 1; c <= cols; c++) {
        String seatCode = String.format("%c%d", rowLetter, c);

        JToggleButton seatButton = new JToggleButton(seatCode);
        seatButton.setFont(new Font("SansSerif", Font.BOLD, 10));
        seatButton.setFocusPainted(false);

        // Jika nomor kursi ada di array list hasil query DB, kunci tombolnya!
        if (bookedSeatsList.contains(seatCode)) {
            seatButton.setEnabled(false);
            seatButton.setBackground(Color.LIGHT_GRAY);
        } else {
            seatButton.addActionListener(e -> {
              if (seatButton.isSelected()) {
                selectedSeats.add(seatCode);
                seatButton.setBackground(Color.GREEN);
              } else {
                selectedSeats.remove(seatCode);
                seatButton.setBackground(null);
              }
              lblSelectedSeatsSummary.setText("Kursi Dipilih: " + selectedSeats.toString());
            });
        }
        jPanelGridContainer.add(seatButton);
      }
    }

    jPanelGridContainer.revalidate();
    jPanelGridContainer.repaint();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    lblScreenTheater = new javax.swing.JLabel();
    jScrollPaneContainer = new javax.swing.JScrollPane();
    jPanelGridContainer = new javax.swing.JPanel();
    jPanelControlBar = new javax.swing.JPanel();
    lblSelectedSeatsSummary = new javax.swing.JLabel();
    btnNext = new javax.swing.JButton();
    btnBack = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();

    lblScreenTheater.setFont(new java.awt.Font("SansSerif", 1, 14)); 
    lblScreenTheater.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblScreenTheater.setText("LAYAR BIOSKOP (SCREEN)");
    lblScreenTheater.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(102, 102, 102)));

    jPanelGridContainer.setLayout(new java.awt.GridLayout(1, 0, 5, 5));
    jScrollPaneContainer.setViewportView(jPanelGridContainer);
    jPanelControlBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    lblSelectedSeatsSummary.setText("Kursi Dipilih: []");

    btnBack.setText("< Kembali");
    btnBack.addActionListener(evt -> parentFrame.goToScheduleSelection());

    btnNext.setText("Selanjutnya (Pilih Makanan) >");
    btnNext.addActionListener(evt -> btnNextActionPerformed(evt));

    javax.swing.GroupLayout jPanelControlBarLayout = new javax.swing.GroupLayout(jPanelControlBar);
    jPanelControlBar.setLayout(jPanelControlBarLayout);
    jPanelControlBarLayout.setHorizontalGroup(
        jPanelControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(18, 18, 18)
                .addComponent(lblSelectedSeatsSummary, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNext)
                .addContainerGap()));
    jPanelControlBarLayout.setVerticalGroup(
        jPanelControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelControlBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelControlBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectedSeatsSummary)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    jLabel1.setText("Langkah 3: Pilih Kursi");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblScreenTheater, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneContainer)
                    .addComponent(jPanelControlBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblScreenTheater, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelControlBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
  }

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {
    if (selectedSeats.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Silakan pilih minimal satu kursi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
      return;
    }
    parentFrame.getSession().setSelectedSeats(new ArrayList<>(selectedSeats));
    parentFrame.goToFnBSelection();
  }

  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanelControlBar;
  private javax.swing.JPanel jPanelGridContainer;
  private javax.swing.JScrollPane jScrollPaneContainer;
  private javax.swing.JLabel lblScreenTheater;
  private javax.swing.JLabel lblSelectedSeatsSummary;
}