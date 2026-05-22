/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author regina
 */
public class Admin extends User {
    public Admin() {
    }

    public Admin(int idUser, String nama, String email,
                 String password, String role) {

        super(idUser, nama, email, password, role);
    }
}
