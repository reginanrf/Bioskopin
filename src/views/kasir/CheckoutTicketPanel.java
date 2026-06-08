package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import java.util.HashMap;

public class CheckoutTicketPanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;

  public CheckoutTicketPanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
  }

  public void generateReceipt() {
    BookingSession session = parentFrame.getSession();
    session.calculateTotals(); // Pastikan nilai terupdate pasca navigasi halaman

    StringBuilder receipt = new StringBuilder();
    receipt.append("========================================\n");
    receipt.append("          BIOSKOPIN CASHIER SYSTEM      \n");
    receipt.append("========================================\n\n");

    receipt.append("Pelanggan: ").append(session.getNamaPelanggan()).append("\n");
    receipt.append("Film     : ").append(session.getJudulFilm()).append("\n");
    receipt.append("Studio   : ").append(session.getNamaStudio()).append("\n");
    receipt.append("Jadwal   : ").append(session.getJamTayang()).append("\n");
    receipt.append("Kursi    : ").append(session.getSelectedSeats().toString()).append("\n");
    receipt.append("Harga/Tkt: Rp ").append(session.getHargaTiket()).append("\n");
    receipt.append("Subtotal : Rp ").append(session.getTotalTiketCost())
           .append(" (").append(session.getSelectedSeats().size()).append("x Tkt)\n\n");

    HashMap<Integer, Integer> cart = session.getFnbCart();
    if (!cart.isEmpty()) {
      receipt.append("--- Item Food & Beverages ---\n");
      HashMap<Integer, String> names = session.getFnbNames();
      HashMap<Integer, Double> prices = session.getFnbPrices();
      
      for (int idFnb : cart.keySet()) {
          int qty = cart.get(idFnb);
          double sub = qty * prices.get(idFnb);
          receipt.append("- ").append(names.get(idFnb))
                 .append(" (").append(qty).append("x) : Rp ").append(sub).append("\n");
      }
      receipt.append("Subtotal F&B: Rp ").append(session.getTotalFnbCost()).append("\n\n");
    }

    receipt.append("========================================\n");
    receipt.append("GRAND TOTAL : Rp ").append(session.getGrandTotal()).append("\n");
    receipt.append("========================================\n");

    txtReceipt.setText(receipt.toString());
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    txtReceipt = new javax.swing.JTextArea();
    btnBack = new javax.swing.JButton();
    btnConfirm = new javax.swing.JButton();
    btnCancel = new javax.swing.JButton();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    jLabel1.setText("Langkah 5: Konfirmasi Pembayaran");

    txtReceipt.setEditable(false);
    txtReceipt.setColumns(20);
    txtReceipt.setFont(new java.awt.Font("Monospaced", 0, 14)); 
    txtReceipt.setRows(5);
    jScrollPane1.setViewportView(txtReceipt);

    btnBack.setText("< Kembali");
    btnBack.addActionListener(evt -> parentFrame.goToFnBSelection());

    btnConfirm.setBackground(new java.awt.Color(0, 153, 51));
    btnConfirm.setFont(new java.awt.Font("Segoe UI", 1, 12)); 
    btnConfirm.setForeground(new java.awt.Color(255, 255, 255));
    btnConfirm.setText("Selesaikan & Cetak Tiket");
    btnConfirm.addActionListener(evt -> btnConfirmActionPerformed(evt));

    btnCancel.setBackground(new java.awt.Color(204, 0, 0));
    btnCancel.setForeground(new java.awt.Color(255, 255, 255));
    btnCancel.setText("Batalkan Transaksi");
    btnCancel.addActionListener(evt -> btnCancelActionPerformed(evt));

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
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
  }

  private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {
    int confirm = JOptionPane.showConfirmDialog(this, "Simpan Transaksi ke Database?", "Konfirmasi Pembayaran", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    // Eksekusi core transaksi multi-tabel via controller
    boolean success = parentFrame.getController().saveTransaction(parentFrame.getSession());

    if (success) {
        JOptionPane.showMessageDialog(this, "Transaksi Berhasil Disimpan! Mencetak tiket...", "Sukses", JOptionPane.INFORMATION_MESSAGE);
        parentFrame.restartTransaction(); // Mengosongkan keranjang belanja kasir kembali ke Langkah 1
    } else {
        JOptionPane.showMessageDialog(this, "Error: Gagal memproses transaksi. Periksa koneksi DB atau stok barang.", "Database Failure", JOptionPane.ERROR_MESSAGE);
    }
  }

  private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {
    int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin membatalkan transaksi ini?", "Batal Transaksi", JOptionPane.YES_NO_OPTION);
    if (confirm == JOptionPane.YES_OPTION) {
      parentFrame.restartTransaction();
    }
  }

  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnCancel;
  private javax.swing.JButton btnConfirm;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea txtReceipt;
}