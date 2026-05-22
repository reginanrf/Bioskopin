/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseHelper;
import models.Film;
import models.Schedule;
import models.Studio;

import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author regina
 */
public class ScheduleDAO {
     Connection conn = DatabaseHelper.getConnection();

    // INSERT
    public boolean insertSchedule(Schedule schedule) {

        String sql = "INSERT INTO schedules "
                + "(id_film, id_studio, tanggal_tayang, "
                + "jam_tayang, harga_tiket) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, schedule.getFilm().getIdFilm());
            stmt.setInt(2, schedule.getStudio().getIdStudio());
            stmt.setDate(3, schedule.getTanggalTayang());
            stmt.setTime(4, schedule.getJamTayang());
            stmt.setDouble(5, schedule.getHargaTiket());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // GET ALL
    public ArrayList<Schedule> getAllSchedules() {

        ArrayList<Schedule> list = new ArrayList<>();

        String sql = "SELECT s.*, "
                + "f.judul, "
                + "st.nama_studio "
                + "FROM schedules s "
                + "JOIN films f ON s.id_film = f.id_film "
                + "JOIN studios st ON s.id_studio = st.id_studio";

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Film film = new Film();
                film.setIdFilm(rs.getInt("id_film"));
                film.setJudul(rs.getString("judul"));

                Studio studio = new Studio();
                studio.setIdStudio(rs.getInt("id_studio"));
                studio.setNamaStudio(rs.getString("nama_studio"));

                Schedule schedule = new Schedule();

                schedule.setIdJadwal(rs.getInt("id_jadwal"));
                schedule.setFilm(film);
                schedule.setStudio(studio);
                schedule.setTanggalTayang(rs.getDate("tanggal_tayang"));
                schedule.setJamTayang(rs.getTime("jam_tayang"));
                schedule.setHargaTiket(rs.getDouble("harga_tiket"));

                list.add(schedule);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // DELETE
    public boolean deleteSchedule(int idJadwal) {

        String sql = "DELETE FROM schedules WHERE id_jadwal = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idJadwal);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
