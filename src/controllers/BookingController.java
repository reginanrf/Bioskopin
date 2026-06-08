package controllers;

import config.DatabaseHelper;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import models.BookingSession;

public class BookingController {
    
    // Mengambil koneksi terupdate dari DatabaseHelper secara dinamis
    private Connection getConnection() {
        try {
            return DatabaseHelper.getConnection();
        } catch (Exception e) {
            System.err.println("[ERROR] Gagal mengambil koneksi: " + e.getMessage());
            return null;
        }
    }

    /**
     * 1. Mengambil semua film dari tabel 'films'
     */
    public ResultSet getAvailableFilms() {
        String query = "SELECT id_film, judul, genre, durasi_menit FROM films ORDER BY judul ASC";
        try {
            Connection conn = getConnection();
            if (conn == null) return null;
            
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error getAvailableFilms: " + e.getMessage());
            return null;
        }
    }

    /**
     * 2. SINKRON: Mengambil jadwal berdasarkan tabel 'schedules' & 'studios'
     * Menggunakan 'AS' agar nama kolom yang diterima JPanel tidak berubah.
     */
    public ResultSet getSchedulesByFilm(int idFilm) {
        String query = "SELECT j.id_jadwal, s.nama_studio, " +
                       "j.tanggal_tayang, j.tanggal_tayang AS tanggal, " + // Menyediakan 'tanggal_tayang' DAN 'tanggal'
                       "j.jam_tayang, " +
                       "s.jumlah_baris, s.jumlah_baris AS baris, " +       // Menyediakan 'jumlah_baris' DAN 'baris'
                       "s.jumlah_kolom, s.jumlah_kolom AS kolom, " +       // Menyediakan 'jumlah_kolom' DAN 'kolom'
                       "j.harga_tiket, j.harga_tiket AS harga " +          // Menyediakan 'harga_tiket' DAN 'harga'
                       "FROM schedules j " +
                       "JOIN studios s ON j.id_studio = s.id_studio " +
                       "WHERE j.id_film = ? ORDER BY j.tanggal_tayang, j.jam_tayang ASC";
        try {
            Connection conn = getConnection();
            if (conn == null) return null;

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, idFilm);
            return ps.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error getSchedulesByFilm: " + e.getMessage());
            return null;
        }
    }

    /**
     * 3. SINKRON: Mengambil kursi terpesan lewat relasi 'booking_details' & 'bookings'
     */
    public ArrayList<String> getBookedSeats(int idJadwal) {
        ArrayList<String> bookedSeats = new ArrayList<>();
        String query = "SELECT bd.nomor_kursi " +
                       "FROM booking_details bd " +
                       "JOIN bookings b ON bd.id_booking = b.id_booking " +
                       "WHERE b.id_jadwal = ?";
        
        Connection conn = getConnection();
        if (conn == null) return bookedSeats;

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, idJadwal);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookedSeats.add(rs.getString("nomor_kursi"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getBookedSeats: " + e.getMessage());
        }
        return bookedSeats;
    }

    /**
     * 4. SINKRON: Mengambil menu dari 'fnb_items'
     */
    public ResultSet getAllFnBItems() {
        // KITA SEDIAKAN KEDUA NAMA AGAR TIDAK ERROR DI GUI
        String query = "SELECT id_fnb, nama_fnb, nama_fnb AS nama_item, kategori, harga, stok FROM fnb_items WHERE stok > 0 ORDER BY nama_fnb ASC";
        try {
            Connection conn = getConnection();
            if (conn == null) return null;

            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error getAllFnBItems: " + e.getMessage());
            return null;
        }
    }

    /**
     * 5. SINKRON: Menyimpan Transaksi ke 'bookings', 'booking_details', & 'booking_fnb_details'
     */
    public boolean saveTransaction(BookingSession session) {
        Connection conn = getConnection();
        if (conn == null) return false;

        PreparedStatement psTransaksi = null;
        PreparedStatement psTiket = null;
        PreparedStatement psDetailFnb = null;
        PreparedStatement psUpdateStok = null;
        PreparedStatement psGetHargaFnb = null;
        ResultSet generatedKeys = null;

        try {
            conn.setAutoCommit(false);

            // 1. Insert ke tabel induk 'bookings' (Perhatikan typo kolom: nama_pelangan)
            String queryTransaksi = "INSERT INTO bookings (id_user, id_jadwal, nama_pelangan, waktu_transaksi, total_tiket, total_fnb, grand_total, status_pembayaran) " +
                                    "VALUES (?, ?, ?, NOW(), ?, ?, ?, ?)";
            psTransaksi = conn.prepareStatement(queryTransaksi, Statement.RETURN_GENERATED_KEYS);
            
            // CATATAN: Karena id_user NOT NULL, kita pasang default 1 (asumsi user/kasir pertama). 
            // Jika kamu punya session user login, silakan ganti angka 1 ini dengan ID user asli.
            psTransaksi.setInt(1, 1); 
            psTransaksi.setInt(2, session.getIdJadwal());
            psTransaksi.setString(3, session.getNamaPelanggan());
            psTransaksi.setDouble(4, session.getTotalTiketCost());
            psTransaksi.setDouble(5, session.getTotalFnbCost());
            psTransaksi.setDouble(6, session.getTotalTiketCost() + session.getTotalFnbCost());
            psTransaksi.setString(7, "Lunas"); // Status Pembayaran
            psTransaksi.executeUpdate();

            generatedKeys = psTransaksi.getGeneratedKeys();
            int idBooking = 0;
            if (generatedKeys.next()) {
                idBooking = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Gagal mendapatkan ID Booking.");
            }

            // 2. Insert data kursi ke 'booking_details'
            String queryTiket = "INSERT INTO booking_details (id_booking, nomor_kursi) VALUES (?, ?)";
            psTiket = conn.prepareStatement(queryTiket);
            for (String seat : session.getSelectedSeats()) {
                psTiket.setInt(1, idBooking);
                psTiket.setString(2, seat);
                psTiket.addBatch();
            }
            psTiket.executeBatch();

            // 3. Insert belanjaan makanan ke 'booking_fnb_details' dan update stok 'fnb_items'
            if (!session.getFnbCart().isEmpty()) {
                String queryDetailFnb = "INSERT INTO booking_fnb_details (id_booking, id_fnb, quantity, subtotal_fnb) VALUES (?, ?, ?, ?)";
                String queryUpdateStok = "UPDATE fnb_items SET stok = stok - ? WHERE id_fnb = ?";
                String queryHarga = "SELECT harga FROM fnb_items WHERE id_fnb = ?";
                
                psDetailFnb = conn.prepareStatement(queryDetailFnb);
                psUpdateStok = conn.prepareStatement(queryUpdateStok);
                psGetHargaFnb = conn.prepareStatement(queryHarga);

                for (Map.Entry<Integer, Integer> entry : session.getFnbCart().entrySet()) {
                    int idFnb = entry.getKey();
                    int qty = entry.getValue();
                    
                    double hargaItem = 0;
                    psGetHargaFnb.setInt(1, idFnb);
                    try (ResultSet rsH = psGetHargaFnb.executeQuery()) {
                        if (rsH.next()) {
                            hargaItem = rsH.getDouble("harga");
                        }
                    }
                    
                    double subtotal = hargaItem * qty;

                    psDetailFnb.setInt(1, idBooking);
                    psDetailFnb.setInt(2, idFnb);
                    psDetailFnb.setInt(3, qty);
                    psDetailFnb.setDouble(4, subtotal);
                    psDetailFnb.addBatch();

                    psUpdateStok.setInt(1, qty);
                    psUpdateStok.setInt(2, idFnb);
                    psUpdateStok.addBatch();
                }
                psDetailFnb.executeBatch();
                psUpdateStok.executeBatch();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("[ROLLBACK] Transaksi gagal: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (generatedKeys != null) generatedKeys.close();
                if (psTransaksi != null) psTransaksi.close();
                if (psTiket != null) psTiket.close();
                if (psDetailFnb != null) psDetailFnb.close();
                if (psUpdateStok != null) psUpdateStok.close();
                if (psGetHargaFnb != null) psGetHargaFnb.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}