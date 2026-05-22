/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseHelper;
import models.Studio;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author regina
 */
public class StudioDAO {
    Connection conn = DatabaseHelper.getConnection();

    // INSERT
    public boolean insertStudio(Studio studio) {

        String sql = "INSERT INTO studios "
                + "(nama_studio, jumlah_baris, jumlah_kolom) "
                + "VALUES (?, ?, ?)";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, studio.getNamaStudio());
            stmt.setInt(2, studio.getJumlahBaris());
            stmt.setInt(3, studio.getJumlahKolom());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // GET ALL
    public ArrayList<Studio> getAllStudios() {

        ArrayList<Studio> listStudio = new ArrayList<>();

        String sql = "SELECT * FROM studios";

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                Studio studio = new Studio();

                studio.setIdStudio(rs.getInt("id_studio"));
                studio.setNamaStudio(rs.getString("nama_studio"));
                studio.setJumlahBaris(rs.getInt("jumlah_baris"));
                studio.setJumlahKolom(rs.getInt("jumlah_kolom"));

                listStudio.add(studio);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listStudio;
    }

    // UPDATE
    public boolean updateStudio(Studio studio) {

        String sql = "UPDATE studios SET "
                + "nama_studio = ?, "
                + "jumlah_baris = ?, "
                + "jumlah_kolom = ? "
                + "WHERE id_studio = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, studio.getNamaStudio());
            stmt.setInt(2, studio.getJumlahBaris());
            stmt.setInt(3, studio.getJumlahKolom());
            stmt.setInt(4, studio.getIdStudio());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteStudio(int idStudio) {

        String sql = "DELETE FROM studios WHERE id_studio = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idStudio);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
