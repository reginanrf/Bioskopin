/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Date;
import java.sql.Time;
/**
 *
 * @author regina
 */
public class Schedule {
    private int idJadwal;
    private Film film;
    private Studio studio;
    private java.util.Date tanggalTayang;
    private java.sql.Time jamTayang;
    private double hargaTiket;

    public Schedule() {
    }

    public Schedule(int idJadwal, Film film, Studio studio,
                    Date tanggalTayang, Time jamTayang,
                    double hargaTiket) {

        this.idJadwal = idJadwal;
        this.film = film;
        this.studio = studio;
        this.tanggalTayang = tanggalTayang;
        this.jamTayang = jamTayang;
        this.hargaTiket = hargaTiket;
    }

    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }

    public java.util.Date getTanggalTayang() {
        return tanggalTayang;
    }

    public void setTanggalTayang(java.util.Date tanggalTayang) {
        this.tanggalTayang = tanggalTayang;
    }

    public Time getJamTayang() {
        return jamTayang;
    }

    public void setJamTayang(Time jamTayang) {
        this.jamTayang = jamTayang;
    }

    public double getHargaTiket() {
        return hargaTiket;
    }

    public void setHargaTiket(double hargaTiket) {
        this.hargaTiket = hargaTiket;
    }
    
    
    @Override
    public String toString() {
        return film.getJudul() + " - " + jamTayang;
    }
}
