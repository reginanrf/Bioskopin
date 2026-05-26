/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;
import utils.Session;
import utils.ColorPalette;
import views.admin.FilmManagement.FilmManagementFrame;
import views.admin.ScheduleManagement.ScheduleManagementFrame;
import views.admin.StudioManagement.StudioManagementFrame;
import views.admin.FnBManagementPanel;
/**
 *
 * @author regina
 */
public class adminDashboard extends javax.swing.JFrame {
    
    String name = Session.nama; 
    String roles = Session.role;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(adminDashboard.class.getName());

    /**
     * Creates new form ScheduleManagementFrame
     */
    public adminDashboard() {
        initComponents();
        
        /* warna sidebar dan main content */
        sidebarpanel.setBackground(ColorPalette.SIDEBAR);
        maincontentpanel.setBackground(ColorPalette.BACKGROUND);
        
//        sidebarLogout.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                
//                int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(null, 
//                        "Apakah Anda yakin ingin keluar (Logout)?", 
//                        "Konfirmasi Logout", 
//                        javax.swing.JOptionPane.YES_NO_OPTION, 
//                        javax.swing.JOptionPane.QUESTION_MESSAGE);
//                
//                if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
//                    
//                    // Bersihkan Session
//                    utils.Session.id_user = 0;
//                    utils.Session.nama = null;
//                    utils.Session.email = null;
//                    utils.Session.role = null;
//                    
//                    // Buka Login & Tutup Dashboard
//                    login loginPage = new login(); 
//                    loginPage.setVisible(true);
//                    
//                    dispose(); // Hancurkan form ini
//                }
//            }
//        });
        
        // 1. Kelola Film
        // Ganti 'panelFilm' dengan nama variabel panel/label lu
//        panelFilm.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                new FilmsManagement().setVisible(true); // Ganti ke nama file lu
//                dispose();
//            }
//        });

        // 2. Kelola Studio
//        panelStudio.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                new StudiosManagement().setVisible(true); // Ganti ke nama file lu
//                dispose();
//            }
//        });

        // 3. Kelola Jadwal
//        panelJadwal.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                new SchedulesManagement().setVisible(true); // Ganti ke nama file lu
//                dispose();
//            }
//        });

        // 4. Kelola F&B
//        panelFnb.addMouseListener(new java.awt.event.MouseAdapter() {
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                new FnbManagement().setVisible(true); // Ganti ke nama file lu
//                dispose();
//            }
//        });
        
        // ============================================================
        
        // Tambahin setting textfield & load data yang tadi di sini juga biar rapi
        txtTotalFilm.setEnabled(false);
        txtTotalStudio.setEnabled(false);
        txtTotalJadwal.setEnabled(false);
        txtTotalFnB.setEnabled(false);
        
        loadDashboardData();
        
        updateTime();
        
        // Konsep lu: Set teks label pakai variabel di atas
        // Pakai pengecekan null buat jaga-jaga kalau langsung di-run tanpa lewat login
        if (name != null && roles != null) {
            lblNama.setText(name);
            lblRole.setText(roles);
        } else {
            lblNama.setText("Guest");
            lblRole.setText("Unknown");
        }
    }
    
    private void loadDashboardData() {
        try {
            java.sql.Connection conn = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/db_bioskop", "root", "");
            java.sql.Statement stmt = conn.createStatement();

            // 1. Ambil Total Film dari tabel 'films'
            java.sql.ResultSet rsFilm = stmt.executeQuery("SELECT COUNT(*) AS total FROM films");
            if (rsFilm.next()) {
                txtTotalFilm.setText(rsFilm.getString("total"));
            }

            // 2. Ambil Total Studio dari tabel 'studios'
            java.sql.ResultSet rsStudio = stmt.executeQuery("SELECT COUNT(*) AS total FROM studios");
            if (rsStudio.next()) {
                txtTotalStudio.setText(rsStudio.getString("total"));
            }

            // 3. Ambil Jadwal Hari Ini dari tabel 'schedules'
            // Mencocokkan kolom 'tanggal_tayang' dengan tanggal sistem hari ini (CURDATE)
            java.sql.ResultSet rsJadwal = stmt.executeQuery("SELECT COUNT(*) AS total FROM schedules WHERE tanggal_tayang = CURDATE()");
            if (rsJadwal.next()) {
                txtTotalJadwal.setText(rsJadwal.getString("total"));
            }

            // 4. Ambil Total F&B dari tabel 'fnb_items'
            java.sql.ResultSet rsFnb = stmt.executeQuery("SELECT COUNT(*) AS total FROM fnb_items");
            if (rsFnb.next()) {
                txtTotalFnB.setText(rsFnb.getString("total"));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Gagal load data dashboard: " + e.getMessage());
        }
    }
    
    private void updateTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        
        // Format tanggal gua tambahin "EEEE" biar nampilin nama hari
        // dan Locale bahasa Indonesia biar harinya muncul "Senin", "Selasa", dst.
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd MMM yyyy", new java.util.Locale("id", "ID"));
        
        javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){
                String formattedDate = dateFormat.format(new Date());
                String formattedTime = timeFormat.format(new Date());
                
                // Pastikan nama labelnya sesuai dengan yang lu buat di NetBeans
                lblJam.setText(formattedTime);
                lblTanggal.setText(formattedDate);
            }
        });
        timer.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        sidebarpanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        sidebarDashboard = new javax.swing.JLabel();
        sidebarFilm = new javax.swing.JLabel();
        sidebarStudio = new javax.swing.JLabel();
        sidebarCalendar = new javax.swing.JLabel();
        sidebarFnB = new javax.swing.JLabel();
        sidebarReport = new javax.swing.JLabel();
        sidebarLogout = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        maincontentpanel = new javax.swing.JPanel();
        panel_pencarianfilter = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtTotalFilm = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTotalStudio = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtTotalJadwal = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtTotalFnB = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblTanggal = new javax.swing.JLabel();
        lblJam = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        sidebarpanel.setBackground(new java.awt.Color(16, 25, 53));
        sidebarpanel.setPreferredSize(new java.awt.Dimension(240, 730));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo_bioskopin.png"))); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(140, 76));

        sidebarDashboard.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarDashboard.setForeground(new java.awt.Color(195, 156, 0));
        sidebarDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/home.png"))); // NOI18N
        sidebarDashboard.setText("  Dashboard");

        sidebarFilm.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarFilm.setForeground(new java.awt.Color(239, 239, 239));
        sidebarFilm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio-1.png"))); // NOI18N
        sidebarFilm.setText("  Film");
        sidebarFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarFilmMouseClicked(evt);
            }
        });

        sidebarStudio.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarStudio.setForeground(new java.awt.Color(239, 239, 239));
        sidebarStudio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio.png"))); // NOI18N
        sidebarStudio.setText("  Studio");
        sidebarStudio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarStudioMouseClicked(evt);
            }
        });

        sidebarCalendar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarCalendar.setForeground(new java.awt.Color(239, 239, 239));
        sidebarCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png"))); // NOI18N
        sidebarCalendar.setText("  Schedule");
        sidebarCalendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarCalendarMouseClicked(evt);
            }
        });

        sidebarFnB.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarFnB.setForeground(new java.awt.Color(239, 239, 239));
        sidebarFnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/food.png"))); // NOI18N
        sidebarFnB.setText("  F&B");
        sidebarFnB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarFnBMouseClicked(evt);
            }
        });

        sidebarReport.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarReport.setForeground(new java.awt.Color(239, 239, 239));
        sidebarReport.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/person.png"))); // NOI18N
        sidebarReport.setText("  Employee");
        sidebarReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarReportMouseClicked(evt);
            }
        });

        sidebarLogout.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarLogout.setForeground(new java.awt.Color(239, 239, 239));
        sidebarLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout.png"))); // NOI18N
        sidebarLogout.setText("  Logout");
        sidebarLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarLogoutMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout sidebarpanelLayout = new javax.swing.GroupLayout(sidebarpanel);
        sidebarpanel.setLayout(sidebarpanelLayout);
        sidebarpanelLayout.setHorizontalGroup(
            sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarpanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidebarpanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sidebarDashboard)
                            .addComponent(sidebarFilm)
                            .addComponent(sidebarStudio)
                            .addComponent(sidebarFnB)
                            .addComponent(sidebarReport)
                            .addComponent(sidebarCalendar)
                            .addComponent(sidebarLogout))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarpanelLayout.setVerticalGroup(
            sidebarpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarpanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(sidebarDashboard)
                .addGap(18, 18, 18)
                .addComponent(sidebarFilm)
                .addGap(18, 18, 18)
                .addComponent(sidebarStudio)
                .addGap(18, 18, 18)
                .addComponent(sidebarCalendar)
                .addGap(18, 18, 18)
                .addComponent(sidebarFnB)
                .addGap(18, 18, 18)
                .addComponent(sidebarReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 276, Short.MAX_VALUE)
                .addComponent(sidebarLogout)
                .addGap(57, 57, 57))
        );

        jPanel2.setMinimumSize(new java.awt.Dimension(1126, 768));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        maincontentpanel.setBackground(new java.awt.Color(245, 247, 251));
        maincontentpanel.setPreferredSize(new java.awt.Dimension(1126, 730));

        panel_pencarianfilter.setBackground(new java.awt.Color(253, 254, 255));

        jPanel5.setBackground(new java.awt.Color(16, 25, 53));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio-1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel9)
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(40, 40, 40))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Total Film");

        txtTotalFilm.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel11.setText("Film tersedia saat ini");

        jLabel12.setText("Status aktif");

        jPanel6.setBackground(new java.awt.Color(16, 25, 53));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(42, 42, 42))
        );

        txtTotalStudio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Total Studio");

        txtTotalJadwal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jPanel7.setBackground(new java.awt.Color(16, 25, 53));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png"))); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel15)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(39, 39, 39))
        );

        jLabel16.setText("Jadwal tayang hari ini");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Jadwal Hari Ini");

        jLabel18.setText("Item tersedia");

        jPanel8.setBackground(new java.awt.Color(16, 25, 53));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/food.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel19)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(jLabel19)
                .addGap(39, 39, 39))
        );

        txtTotalFnB.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setText("Total Pilihan Food & Beverages");

        javax.swing.GroupLayout panel_pencarianfilterLayout = new javax.swing.GroupLayout(panel_pencarianfilter);
        panel_pencarianfilter.setLayout(panel_pencarianfilterLayout);
        panel_pencarianfilterLayout.setHorizontalGroup(
            panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalStudio, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12))
                .addGap(37, 37, 37)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTotalFnB, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel18))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panel_pencarianfilterLayout.setVerticalGroup(
            panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalFnB, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalJadwal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalStudio, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTotalFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(253, 254, 255));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel21.setText("Quick Access");

        jPanel11.setBackground(new java.awt.Color(16, 25, 53));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio-1.png"))); // NOI18N

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Kelola Film");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Kelola Data Film");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel24))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel26)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(16, 25, 53));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Kelola Jadwal Tayang");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Kelola Jadwal");

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png"))); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(152, 152, 152)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel35))
                    .addComponent(jLabel34)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel33)))
                .addContainerGap(165, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(16, 25, 53));
        jPanel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel14MouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Kelola Data Studio");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Kelola Studio");

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio.png"))); // NOI18N

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel27))
                    .addComponent(jLabel28)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel29)))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(16, 25, 53));
        jPanel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel15MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Kelola Data Pegawai");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Kelola Pegawai");

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/person.png"))); // NOI18N

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(148, 148, 148))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addGap(222, 222, 222))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(jLabel30)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel30)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(47, 47, 47)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel21)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(264, 264, 264))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Dashboard Admin");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Selamat Datang Kembali, Admin!");

        lblTanggal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblTanggal.setText("tanggal");

        lblJam.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblJam.setText("Jam real-time");

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNama.setText("Nama user");

        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblRole.setText("role");

        javax.swing.GroupLayout maincontentpanelLayout = new javax.swing.GroupLayout(maincontentpanel);
        maincontentpanel.setLayout(maincontentpanelLayout);
        maincontentpanelLayout.setHorizontalGroup(
            maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maincontentpanelLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maincontentpanelLayout.createSequentialGroup()
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblJam)
                            .addComponent(lblTanggal))
                        .addGap(9, 9, 9)
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(maincontentpanelLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(lblNama))
                            .addComponent(lblRole, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(67, 67, 67))
                    .addGroup(maincontentpanelLayout.createSequentialGroup()
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel_pencarianfilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        maincontentpanelLayout.setVerticalGroup(
            maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maincontentpanelLayout.createSequentialGroup()
                .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(maincontentpanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, maincontentpanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTanggal)
                            .addComponent(lblNama))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblJam)
                            .addComponent(lblRole))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_pencarianfilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(sidebarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maincontentpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(89, 89, 89))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sidebarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maincontentpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new FilmManagementFrame().setVisible(true);
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel14MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new StudioManagementFrame().setVisible(true);
    }//GEN-LAST:event_jPanel14MouseClicked

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ScheduleManagementFrame().setVisible(true);
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel15MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new tampilDataPegawai().setVisible(true);
    }//GEN-LAST:event_jPanel15MouseClicked

    private void sidebarFilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarFilmMouseClicked
        // TODO add your handling code here:
         this.dispose();
        new FilmManagementFrame().setVisible(true);
    }//GEN-LAST:event_sidebarFilmMouseClicked

    private void sidebarStudioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarStudioMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new StudioManagementFrame().setVisible(true);
    }//GEN-LAST:event_sidebarStudioMouseClicked

    private void sidebarCalendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarCalendarMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new ScheduleManagementFrame().setVisible(true);
    }//GEN-LAST:event_sidebarCalendarMouseClicked

    private void sidebarFnBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarFnBMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new FnBManagementPanel().setVisible(true);
    }//GEN-LAST:event_sidebarFnBMouseClicked

    private void sidebarLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarLogoutMouseClicked
        // TODO add your handling code here:
        int konfirmasi = javax.swing.JOptionPane.showConfirmDialog(null, 
                        "Apakah Anda yakin ingin keluar (Logout)?", 
                        "Konfirmasi Logout", 
                        javax.swing.JOptionPane.YES_NO_OPTION, 
                        javax.swing.JOptionPane.QUESTION_MESSAGE);
                
                if (konfirmasi == javax.swing.JOptionPane.YES_OPTION) {
                    
                    // Bersihkan Session
                    utils.Session.id_user = 0;
                    utils.Session.nama = null;
                    utils.Session.email = null;
                    utils.Session.role = null;
                    
                    // Buka Login & Tutup Dashboard
                    login loginPage = new login(); 
                    loginPage.setVisible(true);
                    
                    dispose();
                }
    }//GEN-LAST:event_sidebarLogoutMouseClicked

    private void sidebarReportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarReportMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new tampilDataPegawai().setVisible(true);
    }//GEN-LAST:event_sidebarReportMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Semaincontentreadsck and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new adminDashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel lblJam;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JPanel maincontentpanel;
    private javax.swing.JPanel panel_pencarianfilter;
    private javax.swing.JLabel sidebarCalendar;
    private javax.swing.JLabel sidebarDashboard;
    private javax.swing.JLabel sidebarFilm;
    private javax.swing.JLabel sidebarFnB;
    private javax.swing.JLabel sidebarLogout;
    private javax.swing.JLabel sidebarReport;
    private javax.swing.JLabel sidebarStudio;
    private javax.swing.JPanel sidebarpanel;
    private javax.swing.JTextField txtTotalFilm;
    private javax.swing.JTextField txtTotalFnB;
    private javax.swing.JTextField txtTotalJadwal;
    private javax.swing.JTextField txtTotalStudio;
    // End of variables declaration//GEN-END:variables
}
