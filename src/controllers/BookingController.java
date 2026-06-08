package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import models.BookingSession;

public class BookingController {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db_bioskop";
        String user = "root";
        String password = ""; // Sesuaikan dengan password MySQL local Anda
        return DriverManager.getConnection(url, user, password);
    }

    // Langkah 1: Ambil Semua Data Film
    public ResultSet getAvailableFilms() throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT id_film, judul, genre, durasi_menit FROM films";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery(); 
    }

    // Langkah 2: Ambil Jadwal Berdasarkan id_film beserta data nama_studio
    public ResultSet getSchedulesByFilm(int idFilm) throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT s.id_jadwal, st.nama_studio, s.tanggal_tayang, s.jam_tayang, " +
                     "st.jumlah_baris, st.jumlah_kolom, s.harga_tiket " +
                     "FROM schedules s " +
                     "JOIN studios st ON s.id_studio = st.id_studio " +
                     "WHERE s.id_film = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, idFilm);
        return ps.executeQuery();
    }

    // Langkah 3: Ambil daftar kursi yang sudah dipesan agar tidak duplikat
    public ArrayList<String> getBookedSeats(int idJadwal) throws SQLException {
        ArrayList<String> bookedSeats = new ArrayList<>();
        String sql = "SELECT bd.nomor_kursi FROM booking_details bd " +
                     "JOIN bookings b ON bd.id_booking = b.id_booking " +
                     "WHERE b.id_jadwal = ? AND b.status_pembayaran != 'Batal'";
        
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idJadwal);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookedSeats.add(rs.getString("nomor_kursi"));
                }
            }
        }
        return bookedSeats;
    }

    // Langkah 4: Ambil Semua Data Food & Beverage
    public ResultSet getAllFnBItems() throws SQLException {
        Connection conn = getConnection();
        String sql = "SELECT id_fnb, nama_fnb, kategori, harga, stok FROM fnb_items";
        PreparedStatement ps = conn.prepareStatement(sql);
        return ps.executeQuery();
    }

    // Langkah 5: Simpan Transaksi Utuh (Multi-Tabel ACID Transaction)
    public boolean saveTransaction(BookingSession session) {
        Connection conn = null;
        PreparedStatement psBooking = null;
        PreparedStatement psDetailSeat = null;
        PreparedStatement psDetailFnb = null;
        PreparedStatement psUpdateStok = null;
        
        String sqlBooking = "INSERT INTO bookings (id_user, id_jadwal, nama_pelangan, waktu_transaksi, total_tiket, total_fnb, grand_total, status_pembayaran) " +
                            "VALUES (?, ?, ?, NOW(), ?, ?, ?, 'Lunas')";
        String sqlDetailSeat = "INSERT INTO booking_details (id_booking, nomor_kursi) VALUES (?, ?)";
        String sqlDetailFnb = "INSERT INTO booking_fnb_details (id_booking, id_fnb, quantity, subtotal_fnb) VALUES (?, ?, ?, ?)";
        String sqlUpdateStok = "UPDATE fnb_items SET stok = stok - ? WHERE id_fnb = ?";

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Mengaktifkan mode transaksi data

            // 1. Simpan Data ke Tabel bookings
            psBooking = conn.prepareStatement(sqlBooking, Statement.RETURN_GENERATED_KEYS);
            psBooking.setInt(1, session.getIdUserKasir());
            psBooking.setInt(2, session.getIdJadwal());
            psBooking.setString(3, session.getNamaPelanggan());
            psBooking.setDouble(4, session.getTotalTiketCost());
            psBooking.setDouble(5, session.getTotalFnbCost());
            psBooking.setDouble(6, session.getGrandTotal());
            psBooking.executeUpdate();

            // Ambil ID Generated Auto Increment dari id_booking
            ResultSet rsKeys = psBooking.getGeneratedKeys();
            int idBookingGenerated = 0;
            if (rsKeys.next()) {
                idBookingGenerated = rsKeys.getInt(1);
            } else {
                throw new SQLException("Gagal mendapatkan ID Booking.");
            }

            // 2. Simpan Kursi ke booking_details
            psDetailSeat = conn.prepareStatement(sqlDetailSeat);
            for (String seat : session.getSelectedSeats()) {
                psDetailSeat.setInt(1, idBookingGenerated);
                psDetailSeat.setString(2, seat);
                psDetailSeat.addBatch();
            }
            psDetailSeat.executeBatch();

            // 3. Simpan F&B ke booking_fnb_details & Potong Stok harian
            if (!session.getFnbCart().isEmpty()) {
                psDetailFnb = conn.prepareStatement(sqlDetailFnb);
                psUpdateStok = conn.prepareStatement(sqlUpdateStok);
                
                HashMap<Integer, Integer> cart = session.getFnbCart();
                HashMap<Integer, Double> prices = session.getFnbPrices();
                
                for (int idFnb : cart.keySet()) {
                    int qty = cart.get(idFnb);
                    double subtotal = qty * prices.get(idFnb);

                    // Insert detail fnb
                    psDetailFnb.setInt(1, idBookingGenerated);
                    psDetailFnb.setInt(2, idFnb);
                    psDetailFnb.setInt(3, qty);
                    psDetailFnb.setDouble(4, subtotal);
                    psDetailFnb.addBatch();

                    // Kurangi stok barang
                    psUpdateStok.setInt(1, qty);
                    psUpdateStok.setInt(2, idFnb);
                    psUpdateStok.addBatch();
                }
                psDetailFnb.executeBatch();
                psUpdateStok.executeBatch();
            }

            conn.commit(); // Eksekusi sukses bersama
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            }
            e.printStackTrace();
            return false;
        } finally {
            // Tutup semua resource stream database
            try {
                if (psBooking != null) psBooking.close();
                if (psDetailSeat != null) psDetailSeat.close();
                if (psDetailFnb != null) psDetailFnb.close();
                if (psUpdateStok != null) psUpdateStok.close();
                if (conn != null) conn.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}