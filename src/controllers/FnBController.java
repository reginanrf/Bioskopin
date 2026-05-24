package controllers;

import config.DatabaseHelper;
import models.FnBItem;
import java.sql.*;
import java.util.ArrayList;

public class FnBController {

  // Equivalent to an Index method: returns all items
  public ArrayList<FnBItem> getAllItems() {
    ArrayList<FnBItem> list = new ArrayList<>();
    String query = "SELECT * FROM fnb_items";

    try (Connection conn = DatabaseHelper.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {

      while (rs.next()) {
        list.add(new FnBItem(
            rs.getInt("id_fnb"),
            rs.getString("nama_fnb"),
            rs.getString("kategori"),
            rs.getDouble("harga"),
            rs.getInt("stok")));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return list;
  }

  // Equivalent to a Store method
  public boolean store(String name, String category, double price, int stock) {
    String query = "INSERT INTO fnb_items (nama_fnb, kategori, harga, stok) VALUES (?, ?, ?, ?)";
    try (Connection conn = DatabaseHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
      ps.setString(1, name);
      ps.setString(2, category);
      ps.setDouble(3, price);
      ps.setInt(4, stock);
      return ps.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  // Equivalent to a Destroy method
  public boolean destroy(int id) {
    String query = "DELETE FROM fnb_items WHERE id_fnb = ?";
    try (Connection conn = DatabaseHelper.getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
      ps.setInt(1, id);
      return ps.executeUpdate() > 0;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public boolean update(int id, String name, String category, double price, int stock) {
    String query = "UPDATE fnb_items SET nama_fnb = ?, kategori = ?, harga = ?, stok = ? WHERE id_fnb = ?";
    try (Connection conn = config.DatabaseHelper.getConnection(); // Make sure this matches your helper name!
        PreparedStatement ps = conn.prepareStatement(query)) {

      ps.setString(1, name);
      ps.setString(2, category);
      ps.setDouble(3, price);
      ps.setInt(4, stock);
      ps.setInt(5, id);

      return ps.executeUpdate() > 0;
    } catch (java.sql.SQLException e) {
      e.printStackTrace();
      return false;
    }
  }
}
