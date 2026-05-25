/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ActionButtonRenderer
        extends JPanel
        implements TableCellRenderer {

    private JButton btnEdit;
    private JButton btnDelete;

    public ActionButtonRenderer() {
        setLayout(
                new FlowLayout(FlowLayout.CENTER, 5, 5)
        );

        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Hapus");
        add(btnEdit);
        add(btnDelete);
    }

    @Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column
    ) {
        return this;
    }
}
