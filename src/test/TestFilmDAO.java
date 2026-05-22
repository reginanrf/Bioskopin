/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import dao.FilmDAO;
import models.Film;
/**
 *
 * @author regina
 */
public class TestFilmDAO {
    public static void main(String[] args) {

        Film film = new Film();

        film.setJudul("Avengers");
        film.setGenre("Action");
        film.setDurasiMenit(120);
        film.setSinopsis("Film superhero");
        film.setPosterPath("poster.jpg");

        FilmDAO dao = new FilmDAO();

        boolean result = dao.insertFilm(film);

        if(result) {
            System.out.println("Insert berhasil");
        } else {
            System.out.println("Insert gagal");
        }
    }
}
