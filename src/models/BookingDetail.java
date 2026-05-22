/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author regina
 */
public class BookingDetail {
    private int idDetail;
    private String nomorKursi;

    public BookingDetail() {
    }

    public BookingDetail(int idDetail, String nomorKursi) {

        this.idDetail = idDetail;
        this.nomorKursi = nomorKursi;
    }

    public int getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(int idDetail) {
        this.idDetail = idDetail;
    }

    public String getNomorKursi() {
        return nomorKursi;
    }

    public void setNomorKursi(String nomorKursi) {
        this.nomorKursi = nomorKursi;
    }
}
