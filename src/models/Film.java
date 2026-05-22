/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author regina
 */
public class Film {
    private int idFilm;
    private String judul;
    private String genre;
    private int durasiMenit;
    private String sinopsis;
    private String posterPath;

    public Film() {
    }

     public Film(int idFilm, String judul, String genre,
                int durasiMenit, String sinopsis,
                String posterPath) {

        this.idFilm = idFilm;
        this.judul = judul;
        this.genre = genre;
        this.durasiMenit = durasiMenit;
        this.sinopsis = sinopsis;
        this.posterPath = posterPath;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasiMenit() {
        return durasiMenit;
    }

    public void setDurasiMenit(int durasiMenit) {
        this.durasiMenit = durasiMenit;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public String toString() {
        return judul;
    }
}
