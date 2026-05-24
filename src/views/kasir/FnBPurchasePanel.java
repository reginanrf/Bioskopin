package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author riikq
 */
public class FnBPurchasePanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private DefaultTableModel tableModel;

  public FnBPurchasePanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
    initTable();
    loadMockFnBFromDB();
  }

  private void initTable() {
    tableModel = new DefaultTableModel(
        new Object[][] {},
        new String[] { "Item", "Kategori", "Harga (Rp)" }) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tableFnB.setModel(tableModel);
  }

  private void loadMockFnBFromDB() {
    // MOCK DATA: Replace with SELECT * FROM fnb_items
    tableModel.addRow(new Object[] { "Popcorn Caramel (L)", "Makanan", 45000 });
    tableModel.addRow(new Object[] { "Popcorn Salty (M)", "Makanan", 35000 });
    tableModel.addRow(new Object[] { "Coca Cola (L)", "Minuman", 20000 });
    tableModel.addRow(new Object[] { "Mineral Water", "Minuman", 10000 });
  }

  // Called before showing this panel to clear previous data if user went back
  public void refreshPanel() {
    lblFnbSummary.setText("Total F&B: Rp " + parentFrame.getSession().getFnbTotalCost());
  }

  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableFnB = new javax.swing.JTable();
    btnBack = new javax.swing.JButton();
    btnNext = new javax.swing.JButton();
    btnAddFnB = new javax.swing.JButton();
    lblFnbSummary = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel1.setText("Langkah 4: Pilih Makanan & Minuman (Opsional)");

    jScrollPane1.setViewportView(tableFnB);

    btnBack.setText("< Kembali");
    btnBack.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnBackActionPerformed(evt);
      }
    });

    btnNext.setText("Lanjut ke Pembayaran >");
    btnNext.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnNextActionPerformed(evt);
      }
    });

    btnAddFnB.setText("Tambah ke Pesanan +");
    btnAddFnB.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnAddFnBActionPerformed(evt);
      }
    });

    lblFnbSummary.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    lblFnbSummary.setForeground(new java.awt.Color(0, 153, 51));
    lblFnbSummary.setText("Total F&B: Rp 0");

    jLabel2.setText("Pilih item di tabel, lalu klik Tambah:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblFnbSummary)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddFnB)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFnB, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFnbSummary))
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void btnAddFnBActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddFnBActionPerformed
    int selectedRow = tableFnB.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Pilih item dari tabel terlebih dahulu!");
      return;
    }

    String itemName = (String) tableModel.getValueAt(selectedRow, 0);
    int price = (int) tableModel.getValueAt(selectedRow, 2);

    BookingSession session = parentFrame.getSession();
    session.addFnBItem(itemName, price);

    refreshPanel(); // Update UI label
    JOptionPane.showMessageDialog(this, itemName + " ditambahkan ke pesanan.");
  }// GEN-LAST:event_btnAddFnBActionPerformed

  private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
    parentFrame.goToSeatSelection();
  }// GEN-LAST:event_btnBackActionPerformed

  private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
    parentFrame.goToCheckout();
    parentFrame.refreshCheckoutPanel();
  }// GEN-LAST:event_btnNextActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:initComponents
  private javax.swing.JButton btnAddFnB;
  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblFnbSummary;
  private javax.swing.JTable tableFnB;
  // End of variables declaration//GEN-END:initComponents
}
