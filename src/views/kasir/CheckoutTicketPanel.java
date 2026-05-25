package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;

/**
 * @author riikq
 */
public class CheckoutTicketPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;

  public CheckoutTicketPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
  }

  // Called by MainFrame right before this panel is shown
  public void generateReceipt() {
    BookingSession session = parentFrame.getSession();

    StringBuilder receipt = new StringBuilder();
    receipt.append("====================================\n");
    receipt.append("          BIOSKOPIN TICKET          \n");
    receipt.append("====================================\n\n");

    receipt.append("Film     : ").append(session.getMovieTitle()).append("\n");
    receipt.append("Studio   : ").append(session.getStudioName()).append("\n");
    receipt.append("Jadwal   : ").append(session.getScheduleTime()).append("\n");
    receipt.append("Kursi    : ").append(session.getSelectedSeats().toString()).append("\n");

    int ticketTotal = session.getSelectedSeats().size() * session.getMoviePrice();
    receipt.append("Harga Tiket: Rp").append(ticketTotal).append(" (").append(session.getSelectedSeats().size())
        .append("x)\n\n");

    if (!session.getFnbItems().isEmpty()) {
      receipt.append("--- Makanan & Minuman ---\n");
      for (String fnb : session.getFnbItems()) {
        receipt.append("- ").append(fnb).append("\n");
      }
      receipt.append("Harga F&B: Rp").append(session.getFnbTotalCost()).append("\n\n");
    }

    receipt.append("====================================\n");
    receipt.append("TOTAL BAYAR: Rp").append(session.getGrandTotal()).append("\n");
    receipt.append("====================================\n");

    txtReceipt.setText(receipt.toString());
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    txtReceipt = new javax.swing.JTextArea();
    btnBack = new javax.swing.JButton();
    btnConfirm = new javax.swing.JButton();
    btnCancel = new javax.swing.JButton();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel1.setText("Langkah 5: Konfirmasi Pembayaran");

    txtReceipt.setEditable(false);
    txtReceipt.setColumns(20);
    txtReceipt.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
    txtReceipt.setRows(5);
    jScrollPane1.setViewportView(txtReceipt);

    btnBack.setText("< Kembali");
    btnBack.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBackActionPerformed(evt);
      }
    });

    btnConfirm.setBackground(new java.awt.Color(0, 153, 51));
    btnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
    btnConfirm.setText("Selesaikan & Cetak Tiket");
    btnConfirm.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnConfirmActionPerformed(evt);
      }
    });

    btnCancel.setBackground(new java.awt.Color(204, 0, 0));
    btnCancel.setForeground(new java.awt.Color(255, 255, 255));
    btnCancel.setText("Batalkan Transaksi");
    btnCancel.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnCancelActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(btnCancel)
                        .addGap(18, 18, 18)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
    parentFrame.goToFnBSelection();
  }// GEN-LAST:event_btnBackActionPerformed

  private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnConfirmActionPerformed
    // NOTE: Here is where you will do your INSERT INTO database queries!
    // INSERT INTO transactions ...
    // INSERT INTO tickets ...

    JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan! Mencetak tiket...", "Sukses",
        JOptionPane.INFORMATION_MESSAGE);

    // Reset the flow for the next customer
    parentFrame.restartTransaction();
  }// GEN-LAST:event_btnConfirmActionPerformed

  private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCancelActionPerformed
    int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin membatalkan transaksi ini?", "Batal",
        JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
      parentFrame.restartTransaction();
    }
  }// GEN-LAST:event_btnCancelActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:initComponents
  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnCancel;
  private javax.swing.JButton btnConfirm;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea txtReceipt;
  // End of variables declaration//GEN-END:initComponents
}
