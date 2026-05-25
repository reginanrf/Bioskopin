package models;

public class FnBItem {
  private int idFnb;
  private String namaFnb;
  private String kategori;
  private double harga;
  private int stok;

  public FnBItem(int idFnb, String namaFnb, String kategori, double harga, int stok) {
    this.idFnb = idFnb;
    this.namaFnb = namaFnb;
    this.kategori = kategori;
    this.harga = harga;
    this.stok = stok;
  }

  // Getters and Setters
  public int getIdFnb() {
    return idFnb;
  }

  public String getNamaFnb() {
    return namaFnb;
  }

  public String getKategori() {
    return kategori;
  }

  public double getHarga() {
    return harga;
  }

  public int getStok() {
    return stok;
  }
}
