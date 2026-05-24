/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.StudioDAO;
import models.Studio;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rhy1caa
 */
public class StudioController {
    private StudioDAO studioDAO;

    public StudioController() {
        this.studioDAO = new StudioDAO();
    }
}

public DefaultTableModel AmbilDataTabel() {
        String[] header = {"ID Studio", "Nama Studio", "Jumlah Baris", "Jumlah Kolom"};
        DefaultTableModel model = new DefaultTableModel(header, 0);

        ArrayList<Studio> list = studioDAO.getAllStudios();

        // Looping untuk memasukkan data baris demi baris
        for (Studio s : list) {
            Object[] row = {
                s.getIdStudio(),
                s.getNamaStudio(),
                s.getJumlahBaris(),
                s.getJumlahKolom()
            };
            model.addRow(row);
        }

        return model;
    }

    public boolean tambahStudio(String nama, int baris, int kolom) {
        Studio studio = new Studio();
        studio.setNamaStudio(nama);
        studio.setJumlahBaris(baris);
        studio.setJumlahKolom(kolom);
        return studioDAO.insertStudio(studio);
    }

