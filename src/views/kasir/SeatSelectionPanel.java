package views.kasir;

import models.BookingSession;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import utils.ColorPalette;

/**
 * @author riikq
 */
public class SeatSelectionPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private final ArrayList<String> selectedSeats = new ArrayList<>();
  private final int cols = 10;

  public SeatSelectionPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
    setBackground(ColorPalette.BACKGROUND);
  }

  // Called by MainBookingFrame right before showing this panel
  public void loadSeatGrid(int totalSeats) {
    // Reset selections for this new studio
    selectedSeats.clear();
    lblSelectedSeatsSummary.setText("Kursi Dipilih: []");

    int rows = (int) Math.ceil((double) totalSeats / cols);

    jPanelGridContainer.setLayout(new GridLayout(rows, cols, 8, 8));
    jPanelGridContainer.removeAll();

    for (int i = 0; i < totalSeats; i++) {
      char rowLetter = (char) ('A' + (i / cols));
      int seatNum = (i % cols) + 1;
      String seatCode = String.format("%c%d", rowLetter, seatNum);

      JToggleButton seatButton = new JToggleButton(seatCode);
      seatButton.setFont(new Font("SansSerif", Font.BOLD, 11));
      seatButton.setFocusPainted(false);

      // NOTE: In the future, check database here to disable booked seats
      // if (isSeatBookedInDB(seatCode)) { seatButton.setEnabled(false); }

      seatButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (seatButton.isSelected()) {
            selectedSeats.add(seatCode);
          } else {
            selectedSeats.remove(seatCode);
          }
          lblSelectedSeatsSummary.setText("Kursi Dipilih: " + selectedSeats.toString());
        }
      });

      jPanelGridContainer.add(seatButton);
    }

    jPanelGridContainer.revalidate();
    jPanelGridContainer.repaint();
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    lblScreenTheater = new javax.swing.JLabel();
    jScrollPaneContainer = new javax.swing.JScrollPane();
    jPanelGridContainer = new javax.swing.JPanel();
    jPanelControlBar = new javax.swing.JPanel();
    lblSelectedSeatsSummary = new javax.swing.JLabel();
    btnNext = new javax.swing.JButton();
    btnBack = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();

    lblScreenTheater.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
    lblScreenTheater.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    lblScreenTheater.setText("LAYAR BIOSKOP (SCREEN)");
    lblScreenTheater
        .setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(102, 102, 102)));

    jPanelGridContainer.setLayout(new java.awt.GridLayout(1, 0, 5, 5));
    jScrollPaneContainer.setViewportView(jPanelGridContainer);

    jPanelControlBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    lblSelectedSeatsSummary.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
    lblSelectedSeatsSummary.setText("Kursi Dipilih: []");

    btnNext.setText("Selanjutnya (Pilih Makanan) >");
    btnNext.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNextActionPerformed(evt);
      }
    });

    btnBack.setText("< Kembali");
    btnBack.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBackActionPerformed(evt);
      }
    });

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
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel1.setText("Langkah 3: Pilih Kursi");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblScreenTheater, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPaneContainer)
                    .addComponent(jPanelControlBar, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(lblScreenTheater, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPaneContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelControlBar, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
    parentFrame.goToScheduleSelection();
  }// GEN-LAST:event_btnBackActionPerformed

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
    if (selectedSeats.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Silakan pilih minimal satu kursi!", "Peringatan",
          JOptionPane.WARNING_MESSAGE);
      return;
    }

    // 1. Save seats to Session Cart
    BookingSession session = parentFrame.getSession();

    // Create a new ArrayList copy to prevent reference mutation issues
    session.setSelectedSeats(new ArrayList<>(selectedSeats));

    // 2. Go to FnB Panel
    parentFrame.goToFnBSelection();
  }// GEN-LAST:event_btnNextActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:initComponents
  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanelControlBar;
  private javax.swing.JPanel jPanelGridContainer;
  private javax.swing.JScrollPane jScrollPaneContainer;
  private javax.swing.JLabel lblScreenTheater;
  private javax.swing.JLabel lblSelectedSeatsSummary;
  // End of variables declaration//GEN-END:initComponents
}
