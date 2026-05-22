/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseHelper;
import models.FnBItem;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author regina
 */
public class FnBItemDAO {
    Connection conn = DatabaseHelper.getConnection();

    // INSERT
    public boolean insertFnbItem(FnBItem item) {

        String sql = "INSERT INTO fnb_items "
                + "(nama_fnb, kategori, harga, stok) "
                + "VALUES (?, ?, ?, ?)";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, item.getNamaFnb());
            stmt.setString(2, item.getKategori());
            stmt.setDouble(3, item.getHarga());
            stmt.setInt(4, item.getStok());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // GET ALL
    public ArrayList<FnBItem> getAllFnbItems() {

        ArrayList<FnBItem> list = new ArrayList<>();

        String sql = "SELECT * FROM fnb_items";

        try {

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                FnBItem item = new FnBItem();

                item.setIdFnb(rs.getInt("id_fnb"));
                item.setNamaFnb(rs.getString("nama_fnb"));
                item.setKategori(rs.getString("kategori"));
                item.setHarga(rs.getDouble("harga"));
                item.setStok(rs.getInt("stok"));

                list.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE
    public boolean updateFnbItem(FnBItem item) {

        String sql = "UPDATE fnb_items SET "
                + "nama_fnb = ?, "
                + "kategori = ?, "
                + "harga = ?, "
                + "stok = ? "
                + "WHERE id_fnb = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, item.getNamaFnb());
            stmt.setString(2, item.getKategori());
            stmt.setDouble(3, item.getHarga());
            stmt.setInt(4, item.getStok());
            stmt.setInt(5, item.getIdFnb());

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    // DELETE
    public boolean deleteFnbItem(int idFnb) {

        String sql = "DELETE FROM fnb_items WHERE id_fnb = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idFnb);

            stmt.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }
}
