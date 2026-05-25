package controllers;

import dao.StudioDAO;
import models.Studio;

import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class StudioController {

    private static final Logger logger = Logger.getLogger(StudioController.class.getName());

    private final StudioDAO studioDAO;

    public StudioController() {
        this.studioDAO = new StudioDAO();
    }

    public boolean isDataKosong() {
        return studioDAO.isStudioTableEmpty();
    }
    
    // CREATE
   
    public boolean tambahStudio(String namaLengkap, int baris, int kolom) {
        Studio studio = new Studio(namaLengkap, baris, kolom);
        return studioDAO.insertStudio(studio);
    }
    
    // READ    

    public DefaultTableModel getTableModel() {
        String[] header = {"ID Studio", "Nama Studio", "Jumlah Baris", "Jumlah Kolom", "Kapasitas"};
        
        DefaultTableModel model = new DefaultTableModel(header, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        ArrayList<Studio> list = studioDAO.getAllStudios();
        for (Studio s : list) {
            Object[] row = {
                s.getIdStudio(),
                s.getNamaStudio(),
                s.getJumlahBaris(),
                s.getJumlahKolom(),
                s.getKapasitas()   // Dihitung otomatis dari model: baris × kolom
            };
            model.addRow(row);
        }
        return model;
    }

 
    public Studio getStudioById(int idStudio) {
        return studioDAO.getStudioById(idStudio);
    }
    
    // UPDATE
   
    public boolean updateStudio(int idStudio, String namaLengkap, int baris, int kolom) {
        Studio studio = new Studio(idStudio, namaLengkap, baris, kolom);
        return studioDAO.updateStudio(studio);
    }

    // DELETE
    
    public boolean hapusStudio(int idStudio) {
        return studioDAO.deleteStudio(idStudio);
    }
}
