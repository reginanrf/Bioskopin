/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import config.DatabaseHelper;
import models.Booking;
import models.BookingDetail;
import models.BookingFnBDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author regina
 */
public class BookingDAO {
     Connection conn = DatabaseHelper.getConnection();

    // =========================
    // INSERT BOOKING TRANSACTION
    // =========================
    public boolean insertBooking(Booking booking) {

        String bookingSql = "INSERT INTO bookings "
                + "(id_user, id_jadwal, nama_pelangan, "
                + "waktu_transaksi, total_tiket, total_fnb, "
                + "grand_total, status_pembayaran) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String detailSql = "INSERT INTO booking_details "
                + "(id_booking, nomor_kursi) "
                + "VALUES (?, ?)";

        String fnbSql = "INSERT INTO booking_fnb_details "
                + "(id_booking, id_fnb, quantity, subtotal_fnb) "
                + "VALUES (?, ?, ?, ?)";

        try {

            conn.setAutoCommit(false);

            // INSERT BOOKING
            PreparedStatement bookingStmt =
                    conn.prepareStatement(
                            bookingSql,
                            PreparedStatement.RETURN_GENERATED_KEYS
                    );

            bookingStmt.setInt(
                    1,
                    booking.getKasir().getIdUser()
            );

            bookingStmt.setInt(
                    2,
                    booking.getSchedule().getIdJadwal()
            );

            bookingStmt.setString(
                    3,
                    booking.getNamaPelanggan()
            );

            bookingStmt.setTimestamp(
                    4,
                    booking.getWaktuTransaksi()
            );

            bookingStmt.setDouble(
                    5,
                    booking.getTotalTiket()
            );

            bookingStmt.setDouble(
                    6,
                    booking.getTotalFnb()
            );

            bookingStmt.setDouble(
                    7,
                    booking.getGrandTotal()
            );

            bookingStmt.setString(
                    8,
                    booking.getStatusPembayaran()
            );

            bookingStmt.executeUpdate();

            int idBooking = 0;

            var generatedKeys =
                    bookingStmt.getGeneratedKeys();

            if (generatedKeys.next()) {

                idBooking =
                        generatedKeys.getInt(1);
            }

            // INSERT BOOKING DETAILS
            for (BookingDetail detail :
                    booking.getBookingDetails()) {

                PreparedStatement detailStmt =
                        conn.prepareStatement(detailSql);

                detailStmt.setInt(1, idBooking);

                detailStmt.setString(
                        2,
                        detail.getNomorKursi()
                );

                detailStmt.executeUpdate();
            }

            // INSERT FNB DETAILS
            for (BookingFnBDetail detailFnb :
                    booking.getBookingFnbDetails()) {

                PreparedStatement fnbStmt =
                        conn.prepareStatement(fnbSql);

                fnbStmt.setInt(1, idBooking);

                fnbStmt.setInt(
                        2,
                        detailFnb.getFnbItem().getIdFnb()
                );

                fnbStmt.setInt(
                        3,
                        detailFnb.getQuantity()
                );

                fnbStmt.setDouble(
                        4,
                        detailFnb.getSubtotalFnb()
                );

                fnbStmt.executeUpdate();
            }

            conn.commit();

            return true;

        } catch (SQLException e) {

            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.printStackTrace();

            return false;

        } finally {

            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
