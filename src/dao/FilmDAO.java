/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseHelper;
import models.Film;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author regina
 */
public class FilmDAO {
    
    Connection conn = DatabaseHelper.getConnection();

    // =========================
    // INSERT FILM
    // =========================
    public boolean insertFilm(Film film) {

        String sql = "INSERT INTO films "
                + "(judul, genre, durasi_menit, sinopsis, poster_path) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, film.getJudul());
            stmt.setString(2, film.getGenre());
            stmt.setInt(3, film.getDurasiMenit());
            stmt.setString(4, film.getSinopsis());
            stmt.setString(5, film.getPosterPath());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // GET ALL FILMS
    // =========================
    public ArrayList<Film> getAllFilms() {

        ArrayList<Film> listFilm = new ArrayList<>();

        String sql = "SELECT * FROM films";

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Film film = new Film();

                film.setIdFilm(rs.getInt("id_film"));
                film.setJudul(rs.getString("judul"));
                film.setGenre(rs.getString("genre"));
                film.setDurasiMenit(rs.getInt("durasi_menit"));
                film.setSinopsis(rs.getString("sinopsis"));
                film.setPosterPath(rs.getString("poster_path"));

                listFilm.add(film);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return listFilm;
    }

    // =========================
    // GET FILM BY ID
    // =========================
    public Film getFilmById(int idFilm) {

        String sql = "SELECT * FROM films WHERE id_film = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFilm);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Film film = new Film();

                film.setIdFilm(rs.getInt("id_film"));
                film.setJudul(rs.getString("judul"));
                film.setGenre(rs.getString("genre"));
                film.setDurasiMenit(rs.getInt("durasi_menit"));
                film.setSinopsis(rs.getString("sinopsis"));
                film.setPosterPath(rs.getString("poster_path"));

                return film;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =========================
    // UPDATE FILM
    // =========================
    public boolean updateFilm(Film film) {

        String sql = "UPDATE films SET "
                + "judul = ?, "
                + "genre = ?, "
                + "durasi_menit = ?, "
                + "sinopsis = ?, "
                + "poster_path = ? "
                + "WHERE id_film = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, film.getJudul());
            stmt.setString(2, film.getGenre());
            stmt.setInt(3, film.getDurasiMenit());
            stmt.setString(4, film.getSinopsis());
            stmt.setString(5, film.getPosterPath());
            stmt.setInt(6, film.getIdFilm());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // =========================
    // DELETE FILM
    // =========================
    public boolean deleteFilm(int idFilm) {
        String sql = "DELETE FROM films WHERE id_film = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idFilm);
            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
