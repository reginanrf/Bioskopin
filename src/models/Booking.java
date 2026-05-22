/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;
import java.util.ArrayList;
/**
 *
 * @author regina
 */
public class Booking {
      private int idBooking;
    private Kasir kasir;
    private Schedule schedule;
    private String namaPelanggan;
    private Timestamp waktuTransaksi;
    private double totalTiket;
    private double totalFnb;
    private double grandTotal;
    private String statusPembayaran;

    private ArrayList<BookingDetail> bookingDetails;
    private ArrayList<BookingFnBDetail> bookingFnBDetails;

    public Booking() {

        bookingDetails = new ArrayList<>();
        bookingFnBDetails = new ArrayList<>();
    }

    public Booking(int idBooking, Kasir kasir,
                   Schedule schedule, String namaPelanggan,
                   Timestamp waktuTransaksi,
                   double totalTiket,
                   double totalFnb,
                   double grandTotal,
                   String statusPembayaran) {

        this.idBooking = idBooking;
        this.kasir = kasir;
        this.schedule = schedule;
        this.namaPelanggan = namaPelanggan;
        this.waktuTransaksi = waktuTransaksi;
        this.totalTiket = totalTiket;
        this.totalFnb = totalFnb;
        this.grandTotal = grandTotal;
        this.statusPembayaran = statusPembayaran;

        bookingDetails = new ArrayList<>();
        bookingFnBDetails = new ArrayList<>();
    }

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public Kasir getKasir() {
        return kasir;
    }

    public void setKasir(Kasir kasir) {
        this.kasir = kasir;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public void setNamaPelanggan(String namaPelanggan) {
        this.namaPelanggan = namaPelanggan;
    }

    public Timestamp getWaktuTransaksi() {
        return waktuTransaksi;
    }

    public void setWaktuTransaksi(Timestamp waktuTransaksi) {
        this.waktuTransaksi = waktuTransaksi;
    }

    public double getTotalTiket() {
        return totalTiket;
    }

    public void setTotalTiket(double totalTiket) {
        this.totalTiket = totalTiket;
    }

    public double getTotalFnb() {
        return totalFnb;
    }

    public void setTotalFnb(double totalFnb) {
        this.totalFnb = totalFnb;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getStatusPembayaran() {
        return statusPembayaran;
    }

    public void setStatusPembayaran(String statusPembayaran) {
        this.statusPembayaran = statusPembayaran;
    }

    public ArrayList<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }

    public ArrayList<BookingFnBDetail> getBookingFnbDetails() {
        return bookingFnBDetails;
    }
}
