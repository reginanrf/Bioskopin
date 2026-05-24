/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author regina
 */
public class DatabaseHelper {
  private static final String URL = "jdbc:mysql://localhost:3306/db_bioskop";

  private static final String USER = "root";
  private static final String PASSWORD = "";

  private static Connection connection;

  private DatabaseHelper() {
  }

  public static Connection getConnection() {

    try {

      if (connection == null || connection.isClosed()) {

        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(URL, USER, PASSWORD);

        System.out.println("Koneksi database berhasil!");
      }

    } catch (ClassNotFoundException | SQLException e) {

      System.out.println("Koneksi database gagal!");
      e.printStackTrace();
    }

    return connection;
  }
}
