package dao;

import config.DatabaseHelper;
import models.Studio;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudioDAO {

    private static final Logger logger = Logger.getLogger(StudioDAO.class.getName());

    // Koneksi diambil dari DatabaseHelper (Singleton)
    private final Connection conn = DatabaseHelper.getConnection();

   
    // CREATE — Tambah data studio baru

    public boolean insertStudio(Studio studio) {
        String sql = "INSERT INTO studios (nama_studio, jumlah_baris, jumlah_kolom) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studio.getNamaStudio());
            stmt.setInt(2, studio.getJumlahBaris());
            stmt.setInt(3, studio.getJumlahKolom());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal INSERT studio: " + studio.getNamaStudio(), e);
            return false;
        }
    }

    // READ — Ambil semua data studio

    public ArrayList<Studio> getAllStudios() {
        ArrayList<Studio> listStudio = new ArrayList<>();
        String sql = "SELECT id_studio, nama_studio, jumlah_baris, jumlah_kolom FROM studios ORDER BY id_studio ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Studio studio = new Studio(
                    rs.getInt("id_studio"),
                    rs.getString("nama_studio"),
                    rs.getInt("jumlah_baris"),
                    rs.getInt("jumlah_kolom")
                );
                listStudio.add(studio);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengambil semua data studio.", e);
        }
        return listStudio;
    }
    
    public Studio getStudioById(int idStudio) {
        String sql = "SELECT id_studio, nama_studio, jumlah_baris, jumlah_kolom FROM studios WHERE id_studio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idStudio);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Studio(
                        rs.getInt("id_studio"),
                        rs.getString("nama_studio"),
                        rs.getInt("jumlah_baris"),
                        rs.getInt("jumlah_kolom")
                    );
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengambil studio dengan ID: " + idStudio, e);
        }
        return null;
    }

    public boolean isStudioTableEmpty() {
        String sql = "SELECT COUNT(*) AS total FROM studios";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("total") == 0;
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal mengecek jumlah data studio.", e);
        }
        return true; 
    }

    // UPDATE — Perbarui data studio yang sudah ada

    public boolean updateStudio(Studio studio) {
        String sql = "UPDATE studios SET nama_studio = ?, jumlah_baris = ?, jumlah_kolom = ? WHERE id_studio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studio.getNamaStudio());
            stmt.setInt(2, studio.getJumlahBaris());
            stmt.setInt(3, studio.getJumlahKolom());
            stmt.setInt(4, studio.getIdStudio());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal UPDATE studio dengan ID: " + studio.getIdStudio(), e);
            return false;
        }
    }

    // DELETE — Hapus data studio
    
    public boolean deleteStudio(int idStudio) {
        String sql = "DELETE FROM studios WHERE id_studio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idStudio);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Gagal DELETE studio dengan ID: " + idStudio, e);
            return false;
        }
    }
}
