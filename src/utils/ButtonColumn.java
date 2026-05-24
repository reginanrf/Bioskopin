/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
/**
 *
 * @author Rafli Ahmad Fauzi
 */
public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {
    private final JPanel renderPanel;
    private final JPanel editPanel;
    private final JButton btnEditRender, btnHapusRender;
    private final JButton btnEditAction, btnHapusAction;
    private final JTable table;
    private int currentRow;

    public ButtonColumn(JTable table, int column, Action editAction, Action hapusAction) {
        this.table = table;

        // 1. RENDERER PANEL (Hanya untuk Tampilan Visual)
        renderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        renderPanel.setOpaque(true);
        
        btnEditRender = new JButton("Edit");
        btnEditRender.setBackground(new Color(30, 144, 255)); // Biru
        btnEditRender.setForeground(Color.WHITE);
        
        btnHapusRender = new JButton("Hapus");
        btnHapusRender.setBackground(new Color(220, 20, 60)); // Merah
        btnHapusRender.setForeground(Color.WHITE);
        
        renderPanel.add(btnEditRender);
        renderPanel.add(btnHapusRender);

        // 2. EDITOR PANEL (Untuk Menangani Klik Tombol)
        editPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        editPanel.setOpaque(true);
        
        btnEditAction = new JButton("Edit");
        btnEditAction.setBackground(new Color(30, 144, 255));
        btnEditAction.setForeground(Color.WHITE);
        btnEditAction.addActionListener(e -> {
            fireEditingStopped();
            editAction.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(currentRow)));
        });

        btnHapusAction = new JButton("Hapus");
        btnHapusAction.setBackground(new Color(220, 20, 60));
        btnHapusAction.setForeground(Color.WHITE);
        btnHapusAction.addActionListener(e -> {
            fireEditingStopped();
            hapusAction.actionPerformed(new ActionEvent(table, ActionEvent.ACTION_PERFORMED, String.valueOf(currentRow)));
        });

        editPanel.add(btnEditAction);
        editPanel.add(btnHapusAction);

        // 3. PASANG KE KOLOM TABEL
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
        
        // Atur lebar kolom
        columnModel.getColumn(column).setPreferredWidth(160);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            renderPanel.setBackground(table.getSelectionBackground());
        } else {
            renderPanel.setBackground(table.getBackground());
        }
        return renderPanel;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.currentRow = row;
        editPanel.setBackground(table.getSelectionBackground());
        return editPanel;
    }

    @Override
    public Object getCellEditorValue() {
        return "";
    }
}
