/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

public class Session {
    public static int id_user;
    public static String nama;
    public static String email;
    public static String role;

    // Tambahkan metode ini untuk membersihkan session saat logout
    public static void clear() {
        id_user = 0;
        nama = null;
        email = null;
        role = null;
    }
}