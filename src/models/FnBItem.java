/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author regina
 */
public class FnBItem {
    private int idFnb;
    private String namaFnb;
    private String kategori;
    private double harga;
    private int stok;

    public FnBItem() {
    }

    public FnBItem(int idFnb, String namaFnb,
                   String kategori, double harga,
                   int stok) {

        this.idFnb = idFnb;
        this.namaFnb = namaFnb;
        this.kategori = kategori;
        this.harga = harga;
        this.stok = stok;
    }

    public int getIdFnb() {
        return idFnb;
    }

    public void setIdFnb(int idFnb) {
        this.idFnb = idFnb;
    }

    public String getNamaFnb() {
        return namaFnb;
    }

    public void setNamaFnb(String namaFnb) {
        this.namaFnb = namaFnb;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return namaFnb;
    }
}
