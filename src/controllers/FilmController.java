/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import dao.FilmDAO;
import models.Film;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author regina
 */
public class FilmController {
    private final FilmDAO filmDAO;

    // Constructor untuk inisialisasi FilmDAO
    public FilmController() {
        this.filmDAO = new FilmDAO();
    }

    /**
     * Menyimpan film baru setelah memvalidasi input dari GUI
     */
    public boolean addFilm(String judul, String genre, int durasi, String sinopsis, String posterPath) {

        if (judul == null || judul.trim().isEmpty() || genre == null || genre.trim().isEmpty()) {
            System.out.println("Validasi Gagal: Judul atau Genre tidak boleh kosong.");
            return false;
        }
        if (durasi <= 0) {
            System.out.println("Validasi Gagal: Durasi film harus lebih dari 0 menit.");
            return false;
        }

        // Membuat objek model Film
        Film film = new Film(0, judul, genre, durasi, sinopsis, posterPath);
        return filmDAO.insertFilm(film);
    }

    /**
     * Memperbarui data film yang sudah ada berdasarkan ID
     */
    public boolean editFilm(int idFilm, String judul, String genre, int durasi, String sinopsis, String posterPath) {
        if (idFilm <= 0 || judul == null || judul.trim().isEmpty()) {
            return false;
        }
        
        Film film = new Film(idFilm, judul, genre, durasi, sinopsis, posterPath);
        return filmDAO.updateFilm(film);
    }

    /**
     * Menghapus film berdasarkan ID
     */
    public boolean removeFilm(int idFilm) {
        if (idFilm <= 0) {
            return false;
        }
        return filmDAO.deleteFilm(idFilm);
    }

    /**
     * Mengambil satu data film berdasarkan ID
     */
    public Film getFilm(int idFilm) {
        return filmDAO.getFilmById(idFilm);
    }

    /**
     * Membantu memformat data ArrayList dari DAO langsung menjadi DefaultTableModel
     */
    public DefaultTableModel getFilmTableModel() {
        String[] columnNames = {"ID Film", "Judul", "Genre", "Durasi (Menit)", "Sinopsis", "Poster Path", "Aksi"};

            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        ArrayList<Film> listFilm = filmDAO.getAllFilms();
        for (Film film : listFilm) {
            Object[] rowData = {
                film.getIdFilm(),
                film.getJudul(),
                film.getGenre(),
                film.getDurasiMenit(),
                film.getSinopsis(),
                film.getPosterPath(),
                ""
            };
            model.addRow(rowData);
        }
        
        return model;
    }
    
    public DefaultTableModel getSearchFilmTableModel(String keyword) {
        String[] columnNames = {"ID Film", "Judul", "Genre", "Durasi (Menit)", "Sinopsis", "Poster Path", "Aksi"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        ArrayList<models.Film> listFilm = filmDAO.searchFilmsByJudul(keyword);
        for (models.Film film : listFilm) {
            Object[] rowData = {
                film.getIdFilm(),
                film.getJudul(),
                film.getGenre(),
                film.getDurasiMenit(),
                film.getSinopsis(),
                film.getPosterPath(),
                ""
            };
            model.addRow(rowData);
        }

        return model;
    }
}
