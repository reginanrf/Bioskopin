/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author regina
 */
public class Studio {
     private int idStudio;
    private String namaStudio;
    private int jumlahBaris;
    private int jumlahKolom;

    public Studio() {
    }

    public Studio(int idStudio, String namaStudio,
                  int jumlahBaris, int jumlahKolom) {

        this.idStudio = idStudio;
        this.namaStudio = namaStudio;
        this.jumlahBaris = jumlahBaris;
        this.jumlahKolom = jumlahKolom;
    }

    public int getIdStudio() {
        return idStudio;
    }

    public void setIdStudio(int idStudio) {
        this.idStudio = idStudio;
    }

    public String getNamaStudio() {
        return namaStudio;
    }

    public void setNamaStudio(String namaStudio) {
        this.namaStudio = namaStudio;
    }

    public int getJumlahBaris() {
        return jumlahBaris;
    }

    public void setJumlahBaris(int jumlahBaris) {
        this.jumlahBaris = jumlahBaris;
    }

    public int getJumlahKolom() {
        return jumlahKolom;
    }

    public void setJumlahKolom(int jumlahKolom) {
        this.jumlahKolom = jumlahKolom;
    }

    @Override
    public String toString() {
        return namaStudio;
    }
}
