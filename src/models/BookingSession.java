package models;

import java.util.ArrayList;
import java.util.HashMap;

public class BookingSession {
    // Data Film & Jadwal
    private int idFilm;
    private String judulFilm;
    private int idJadwal;
    private String namaStudio;
    private String jamTayang;
    private double hargaTiket;
    
    // Data Pelanggan & Kasir
    private String namaPelanggan = "Umum"; 
    private int idUserKasir = 1; // Default ID kasir (sementara sebelum ada sistem login)

    // Data Kursi & F&B (Menggunakan HashMap untuk menyimpan ID FnB dan Quantity-nya)
    private ArrayList<String> selectedSeats = new ArrayList<>();
    private HashMap<Integer, Integer> fnbCart = new HashMap<>(); // Key: id_fnb, Value: quantity
    private HashMap<Integer, String> fnbNames = new HashMap<>(); // Untuk keperluan cetak struk
    private HashMap<Integer, Double> fnbPrices = new HashMap<>(); // Untuk keperluan hitung subtotal
    
    private double totalTiketCost = 0;
    private double totalFnbCost = 0;

    public void resetSession() {
        idFilm = 0; judulFilm = ""; idJadwal = 0; namaStudio = "";
        jamTayang = ""; hargaTiket = 0.0; totalTiketCost = 0.0; totalFnbCost = 0.0;
        namaPelanggan = "Umum";
        selectedSeats.clear();
        fnbCart.clear();
        fnbNames.clear();
        fnbPrices.clear();
    }

    public void calculateTotals() {
        this.totalTiketCost = this.selectedSeats.size() * this.hargaTiket;
        this.totalFnbCost = 0;
        for (int idFnb : fnbCart.keySet()) {
            this.totalFnbCost += fnbCart.get(idFnb) * fnbPrices.get(idFnb);
        }
    }

    // Fungsi helper untuk menambah F&B ke keranjang belanja
    public void addFnBItem(int idFnb, String namaFnb, double harga, int qty) {
        fnbCart.put(idFnb, fnbCart.getOrDefault(idFnb, 0) + qty);
        fnbNames.put(idFnb, namaFnb);
        fnbPrices.put(idFnb, harga);
        calculateTotals();
    }
    
    private String movieTitle;

    public String getMovieTitle() {
        return this.movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    // Getters & Setters
    public int getIdFilm() { return idFilm; }
    public void setIdFilm(int idFilm) { this.idFilm = idFilm; }
    public String getJudulFilm() { return judulFilm; }
    public void setJudulFilm(String judulFilm) { this.judulFilm = judulFilm; }
    public int getIdJadwal() { return idJadwal; }
    public void setIdJadwal(int idJadwal) { this.idJadwal = idJadwal; }
    public String getNamaStudio() { return namaStudio; }
    public void setNamaStudio(String namaStudio) { this.namaStudio = namaStudio; }
    public String getJamTayang() { return jamTayang; }
    public void setJamTayang(String jamTayang) { this.jamTayang = jamTayang; }
    public double getHargaTiket() { return hargaTiket; }
    public void setHargaTiket(double hargaTiket) { this.hargaTiket = hargaTiket; }
    public String getNamaPelanggan() { return namaPelanggan; }
    public void setNamaPelanggan(String namaPelanggan) { this.namaPelanggan = namaPelanggan; }
    public int getIdUserKasir() { return idUserKasir; }
    public void setIdUserKasir(int idUserKasir) { this.idUserKasir = idUserKasir; }
    public ArrayList<String> getSelectedSeats() { return selectedSeats; }
    public void setSelectedSeats(ArrayList<String> selectedSeats) { this.selectedSeats = selectedSeats; calculateTotals(); }
    public HashMap<Integer, Integer> getFnbCart() { return fnbCart; }
    public HashMap<Integer, String> getFnbNames() { return fnbNames; }
    public HashMap<Integer, Double> getFnbPrices() { return fnbPrices; }
    public double getTotalTiketCost() { return totalTiketCost; }
    public double getTotalFnbCost() { return totalFnbCost; }
    public double getGrandTotal() { return totalTiketCost + totalFnbCost; }
}