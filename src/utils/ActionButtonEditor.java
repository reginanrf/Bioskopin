/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import models.Schedule;

import views.admin.ScheduleManagement.DeleteSchedule;
import views.admin.ScheduleManagement.ScheduleManagementFrame;
import views.admin.ScheduleManagement.UpdateSchedule;

public class ActionButtonEditor
        extends AbstractCellEditor
        implements TableCellEditor {

    private JPanel panel;

    private JButton btnEdit;
    private JButton btnDelete;

    private ScheduleManagementFrame parent;

    private JTable table;

    // =========================================
    // CONSTRUCTOR
    // =========================================
    public ActionButtonEditor(
            ScheduleManagementFrame parent
    ) {

        this.parent = parent;

        panel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        5,
                        2
                )
        );

        // BUTTON EDIT
        btnEdit = new JButton("Edit");

        // BUTTON DELETE
        btnDelete = new JButton("Hapus");

        panel.add(btnEdit);
        panel.add(btnDelete);

        // =====================================
        // BUTTON EDIT
        // =====================================
        btnEdit.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row >= 0) {

                Schedule schedule =
                        parent.getScheduleList().get(row);

                UpdateSchedule dialog =
                        new UpdateSchedule(
                                parent,
                                true,
                                schedule
                        );

                dialog.setVisible(true);

                parent.loadTable();
            }

            fireEditingStopped();
        });

        // =====================================
        // BUTTON DELETE
        // =====================================
        btnDelete.addActionListener(e -> {

            int row = table.getSelectedRow();

            if(row >= 0) {

                Schedule schedule =
                        parent.getScheduleList().get(row);

                DeleteSchedule dialog =
                        new DeleteSchedule(
                                parent,
                                true,
                                schedule
                        );

                dialog.setVisible(true);

                parent.loadTable();
            }

            fireEditingStopped();
        });
    }

    // =========================================
    // TABLE EDITOR COMPONENT
    // =========================================
    @Override
    public Component getTableCellEditorComponent(
            JTable table,
            Object value,
            boolean isSelected,
            int row,
            int column
    ) {

        this.table = table;

        return panel;
    }

    // =========================================
    // CELL VALUE
    // =========================================
    @Override
    public Object getCellEditorValue() {

        return "";
    }
}