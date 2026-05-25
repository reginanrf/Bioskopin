/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseHelper;

import models.Film;
import models.Schedule;
import models.Studio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import java.util.ArrayList;

public class ScheduleDAO {

    Connection conn = DatabaseHelper.getConnection();

    //insert
    public boolean insertSchedule(Schedule schedule) {

        String sql =
                "INSERT INTO schedules "
                + "(id_film, id_studio, "
                + "tanggal_tayang, jam_tayang, harga_tiket) "
                + "VALUES (?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(
                    1,
                    schedule.getFilm().getIdFilm()
            );

            stmt.setInt(
                    2,
                    schedule.getStudio().getIdStudio()
            );

            stmt.setDate(
                    3,
                    new java.sql.Date(
                            schedule
                                    .getTanggalTayang()
                                    .getTime()
                    )
            );

            stmt.setTime(
                    4,
                    schedule.getJamTayang()
            );

            stmt.setDouble(
                    5,
                    schedule.getHargaTiket()
            );

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    //get all schedule
    public ArrayList<Schedule> getAllSchedules() {

        ArrayList<Schedule> list =
                new ArrayList<>();

        String sql =
                "SELECT s.*, "
                + "f.judul, "
                + "st.nama_studio "
                + "FROM schedules s "
                + "JOIN films f "
                + "ON s.id_film = f.id_film "
                + "JOIN studios st "
                + "ON s.id_studio = st.id_studio";

        try {

            Statement stmt =
                    conn.createStatement();

            ResultSet rs =
                    stmt.executeQuery(sql);

            while (rs.next()) {

                Film film = new Film();

                film.setIdFilm(
                        rs.getInt("id_film")
                );

                film.setJudul(
                        rs.getString("judul")
                );

                Studio studio = new Studio();

                studio.setIdStudio(
                        rs.getInt("id_studio")
                );

                studio.setNamaStudio(
                        rs.getString("nama_studio")
                );

                Schedule schedule =
                        new Schedule();

                schedule.setIdJadwal(
                        rs.getInt("id_jadwal")
                );

                schedule.setFilm(film);

                schedule.setStudio(studio);

                schedule.setTanggalTayang(
                        rs.getDate("tanggal_tayang")
                );

                schedule.setJamTayang(
                        rs.getTime("jam_tayang")
                );

                schedule.setHargaTiket(
                        rs.getDouble("harga_tiket")
                );

                list.add(schedule);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return list;
    }

    //delete schedule
    public boolean deleteSchedule(int idJadwal) {

        String sql =
                "DELETE FROM schedules "
                + "WHERE id_jadwal = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, idJadwal);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();

            return false;
        }
    }

    //search schedule
    public ArrayList<Schedule> searchSchedule(
            String keyword,
            String film,
            String studio,
            java.util.Date tanggal
    ) {

        ArrayList<Schedule> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT s.*, "
                    + "f.judul, "
                    + "st.nama_studio "
                    + "FROM schedules s "
                    + "JOIN films f "
                    + "ON s.id_film = f.id_film "
                    + "JOIN studios st "
                    + "ON s.id_studio = st.id_studio "
                    + "WHERE 1=1 ";

            // FILTER KEYWORD
            if(keyword != null && !keyword.isEmpty()) {
                sql += "AND f.judul LIKE ? ";
            }

            // FILTER FILM
            if(film != null && !film.equals("Semua Film")) {
                sql += "AND f.judul = ? ";
            }

            // FILTER STUDIO
            if(studio != null && !studio.equals("Semua Studio")) {
                sql += "AND st.nama_studio = ? ";
            }

            // FILTER TANGGAL
            if(tanggal != null) {
                sql += "AND s.tanggal_tayang = ? ";
            }

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            int index = 1;

            // SET KEYWORD
            if(keyword != null && !keyword.isEmpty()) {
                ps.setString(
                        index++,
                        "%" + keyword + "%"
                );
            }

            // SET FILM
            if(film != null && !film.equals("Semua Film")) {
                ps.setString(
                        index++,
                        film
                );
            }

            // SET STUDIO
            if(studio != null && !studio.equals("Semua Studio")) {
                ps.setString(
                        index++,
                        studio
                );
            }

            // SET TANGGAL
            if(tanggal != null) {
                ps.setDate(
                        index++,
                        new java.sql.Date(
                                tanggal.getTime()
                        )
                );
            }

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Film f = new Film();

                f.setIdFilm(
                        rs.getInt("id_film")
                );

                f.setJudul(
                        rs.getString("judul")
                );

                Studio st = new Studio();

                st.setIdStudio(
                        rs.getInt("id_studio")
                );

                st.setNamaStudio(
                        rs.getString("nama_studio")
                );

                Schedule s =
                        new Schedule();

                s.setIdJadwal(
                        rs.getInt("id_jadwal")
                );

                s.setFilm(f);

                s.setStudio(st);

                s.setTanggalTayang(
                        rs.getDate("tanggal_tayang")
                );

                s.setJamTayang(
                        rs.getTime("jam_tayang")
                );

                s.setHargaTiket(
                        rs.getDouble("harga_tiket")
                );

                list.add(s);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
    //update schedule
    public boolean updateSchedule(Schedule schedule) {

        String sql =
                "UPDATE schedules SET "
                + "id_film = ?, "
                + "id_studio = ?, "
                + "tanggal_tayang = ?, "
                + "jam_tayang = ?, "
                + "harga_tiket = ? "
                + "WHERE id_jadwal = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(
                    1,
                    schedule.getFilm().getIdFilm()
            );

            stmt.setInt(
                    2,
                    schedule.getStudio().getIdStudio()
            );

            stmt.setDate(
                    3,
                    new java.sql.Date(
                            schedule
                                    .getTanggalTayang()
                                    .getTime()
                    )
            );

            stmt.setTime(
                    4,
                    schedule.getJamTayang()
            );

            stmt.setDouble(
                    5,
                    schedule.getHargaTiket()
            );

            stmt.setInt(
                    6,
                    schedule.getIdJadwal()
            );

            stmt.executeUpdate();

            return true;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}