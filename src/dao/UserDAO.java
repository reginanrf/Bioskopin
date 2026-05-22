/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import config.DatabaseHelper;
import models.Admin;
import models.Kasir;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author regina
 */
public class UserDAO {
    Connection conn = DatabaseHelper.getConnection();

    // =========================
    // LOGIN
    // =========================
    public User login(String email, String password) {

        String sql = "SELECT * FROM users "
                + "WHERE email = ? AND password = ?";

        try {

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, email);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                String role = rs.getString("role");

                User user;

                if (role.equalsIgnoreCase("Admin")) {
                    user = new Admin();
                } else {
                    user = new Kasir();
                }

                user.setIdUser(rs.getInt("id_user"));
                user.setNama(rs.getString("nama"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(role);

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
