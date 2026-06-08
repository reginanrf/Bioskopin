package views.kasir;

import models.BookingSession;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

public class FnBPurchasePanel extends javax.swing.JPanel {

  private MainBookingFrame parentFrame;
  private DefaultTableModel tableModel;

  public FnBPurchasePanel(MainBookingFrame parentFrame) {
    this.parentFrame = parentFrame;
    initComponents();
    initTable();
  }

  private void initTable() {
    tableModel = new DefaultTableModel(
        new Object[][] {},
        new String[] { "ID F&B", "Nama Item", "Kategori", "Harga (Rp)", "Stok Tersedia" }) {
      @Override
      public boolean isCellEditable(int row, int column) { return false; }
    };
    tableFnB.setModel(tableModel);
    
    // Sembunyikan ID F&B dari user interface kasir
    tableFnB.getColumnModel().getColumn(0).setMinWidth(0);
    tableFnB.getColumnModel().getColumn(0).setMaxWidth(0);
    tableFnB.getColumnModel().getColumn(0).setWidth(0);
  }

  public void loadFnBFromDB() {
    tableModel.setRowCount(0);
    lblFnbSummary.setText("Total Belanja F&B: Rp " + parentFrame.getSession().getTotalFnbCost());
    
    try {
        ResultSet rs = parentFrame.getController().getAllFnBItems();
        while (rs.next()) {
            tableModel.addRow(new Object[] {
                rs.getInt("id_fnb"),
                rs.getString("nama_fnb"),
                rs.getString("kategori"),
                rs.getDouble("harga"),
                rs.getInt("stok")
            });
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal memuat menu F&B: " + e.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {
    jLabel1 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    tableFnB = new javax.swing.JTable();
    btnBack = new javax.swing.JButton();
    btnNext = new javax.swing.JButton();
    btnAddFnB = new javax.swing.JButton();
    lblFnbSummary = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    txtQty = new javax.swing.JSpinner(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); 
    jLabel1.setText("Langkah 4: Pilih Makanan & Minuman (Opsional)");
    jScrollPane1.setViewportView(tableFnB);

    btnBack.setText("< Kembali");
    btnBack.addActionListener(evt -> parentFrame.goToSeatSelection());

    btnNext.setText("Lanjut ke Pembayaran >");
    btnNext.addActionListener(evt -> {
        parentFrame.goToCheckout();
        parentFrame.refreshCheckoutPanel();
    });

    btnAddFnB.setText("Tambah ke Pesanan +");
    btnAddFnB.addActionListener(evt -> btnAddFnBActionPerformed(evt));

    lblFnbSummary.setFont(new java.awt.Font("Segoe UI", 1, 14)); 
    lblFnbSummary.setForeground(new java.awt.Color(0, 153, 51));
    lblFnbSummary.setText("Total F&B: Rp 0");
    jLabel2.setText("Jumlah Beli:");

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
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFnbSummary)
                    .addGap(18, 18, 18)
                    .addComponent(btnNext))
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel1)
                    .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnAddFnB)))
            .addContainerGap())
    );

    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jLabel1)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnAddFnB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(lblFnbSummary))
            .addContainerGap())
    );
  }

  private void btnAddFnBActionPerformed(java.awt.event.ActionEvent evt) {
    int selectedRow = tableFnB.getSelectedRow();
    if (selectedRow == -1) {
      JOptionPane.showMessageDialog(this, "Pilih item dari tabel terlebih dahulu!");
      return;
    }

    int idFnb = (int) tableModel.getValueAt(selectedRow, 0);
    String itemName = (String) tableModel.getValueAt(selectedRow, 1);
    double price = (double) tableModel.getValueAt(selectedRow, 3);
    int currentStock = (int) tableModel.getValueAt(selectedRow, 4);
    int qtyInput = (int) txtQty.getValue();

    if (qtyInput > currentStock) {
        JOptionPane.showMessageDialog(this, "Stok tidak mencukupi! Sisa stok: " + currentStock, "Stok Kurang", JOptionPane.ERROR_MESSAGE);
        return;
    }

    BookingSession session = parentFrame.getSession();
    session.addFnBItem(idFnb, itemName, price, qtyInput);

    lblFnbSummary.setText("Total F&B: Rp " + session.getTotalFnbCost());
    JOptionPane.showMessageDialog(this, qtyInput + "x " + itemName + " berhasil ditambahkan.");
  }

  private javax.swing.JButton btnAddFnB;
  private javax.swing.JButton btnBack;
  private javax.swing.JButton btnNext;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JSpinner txtQty;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lblFnbSummary;
  private javax.swing.JTable tableFnB;
}