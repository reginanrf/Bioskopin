/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views.admin.ScheduleManagement;

import utils.ColorPalette;
import dao.FilmDAO;
import dao.ScheduleDAO;
import dao.StudioDAO;

import models.Film;
import models.Schedule;
import models.Studio;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import utils.ButtonColumn;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;

import views.admin.FilmManagement.FilmManagementFrame;
import views.admin.ScheduleManagement.ScheduleManagementFrame;
import views.admin.StudioManagement.StudioManagementFrame;
import views.admin.FnBManagementPanel;
import views.admin.adminDashboard;
import views.admin.login;
import views.admin.tampilDataPegawai;

/**
 *
 * @author regina
 */
public class ScheduleManagementFrame extends javax.swing.JFrame {
  
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ScheduleManagementFrame.class.getName());
    private DefaultTableModel tableModel;
    private ArrayList<Schedule> listSchedule;
    /**
     * Creates new form ScheduleManagementFrame
     */
    
    private void setupTable() {

            tableModel = new DefaultTableModel(
                            new Object[] {
                                "No",
                                "Film",
                                "Studio",
                                "Tanggal",
                                "Jam",
                                "Harga",
                                "Aksi"
                            },
                            0);
            tabelDaftarTayang.setModel(tableModel);
            
            new ButtonColumn(
                tabelDaftarTayang,
                6,

                // ACTION EDIT
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = Integer.parseInt(e.getActionCommand());
                        Schedule schedule = listSchedule.get(row);

                        UpdateSchedule dialog =
                                new UpdateSchedule(
                                        ScheduleManagementFrame.this,
                                        true,
                                        schedule
                                );
                        dialog.setVisible(true);
                        loadTable();
                    }
                },

                // ACTION HAPUS
                new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int row = Integer.parseInt(e.getActionCommand());
                        Schedule schedule = listSchedule.get(row);
                        DeleteSchedule dialog =
                                new DeleteSchedule(
                                        ScheduleManagementFrame.this,
                                        true,
                                        schedule
                                );

                        dialog.setVisible(true);
                        loadTable();
                    }
                });
          
            tabelDaftarTayang.setRowHeight(40);
            tabelDaftarTayang.setShowGrid(false);
            tabelDaftarTayang.setIntercellSpacing(new java.awt.Dimension(0, 0));
            tabelDaftarTayang.getColumnModel().getColumn(0).setMaxWidth(50);
            tabelDaftarTayang.getColumnModel().getColumn(0).setMinWidth(50);
        }
        
        public void loadTable() {
            ScheduleDAO dao = new ScheduleDAO();
            listSchedule = dao.getAllSchedules();
            tableModel.setRowCount(0);
            int no = 1;
            
            for(Schedule s : listSchedule) {
                Object[] row = {
                    no++,
                    s.getFilm().getJudul(),
                    s.getStudio().getNamaStudio(),
                    s.getTanggalTayang(),
                    s.getJamTayang(),
                    s.getHargaTiket(),
                    "Aksi"
                };
                tableModel.addRow(row);
            }
            updateTotal();
        }
        
        private void loadFilmFilter() {
            FilmDAO dao = new FilmDAO();
            ArrayList<Film> list = dao.getAllFilms();
            cbPilihFilm.removeAllItems();
            cbPilihFilm.addItem("Semua Film");
            
            for(Film film : list) {
                cbPilihFilm.addItem(film.getJudul());
            }
        }
        
        private void loadStudioFilter() {
            StudioDAO dao = new StudioDAO();
            ArrayList<Studio> list = dao.getAllStudios();
            cbPilihStudio.removeAllItems();
            cbPilihStudio.addItem("Semua Studio");

            for(Studio studio : list) {
                cbPilihStudio.addItem(studio.getNamaStudio());
            }
        }
        
        private void updateTotal() {
            totalJadwal.setText(
                    "Total : "
                    + tableModel.getRowCount()
                    + " Jadwal"
            );
        }
        
        private void searchSchedule() {
            String keyword = tfCariFilm.getText();
            String film = cbPilihFilm.getSelectedItem().toString();
            String studio = cbPilihStudio.getSelectedItem().toString();
            java.util.Date tanggal = jDateChooser1.getDate();
            ScheduleDAO dao = new ScheduleDAO();
            listSchedule =
                    dao.searchSchedule(
                            keyword,
                            film,
                            studio,
                            tanggal
                    );

            tableModel.setRowCount(0);
            int no = 1;
            for(Schedule s : listSchedule) {
                Object[] row = {
                    no++,
                    s.getFilm().getJudul(),
                    s.getStudio().getNamaStudio(),
                    s.getTanggalTayang(),
                    s.getJamTayang(),
                    s.getHargaTiket(),
                    "Edit | Hapus"
                };
                tableModel.addRow(row);
            }
            updateTotal();           
        }
        
        public ArrayList<Schedule> getScheduleList() {
            return listSchedule;
        }
    public ScheduleManagementFrame() {
        initComponents();
        
        /* warna sidebar dan main content */
        sidebarpanel.setBackground(ColorPalette.SIDEBAR);
        maincontentpanel.setBackground(ColorPalette.BACKGROUND);
        setupTable();
        loadTable();
        loadFilmFilter();
        loadStudioFilter();
        
        
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
        sidebarLogout = new javax.swing.JLabel();
        sidebarFnB1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        maincontentpanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panel_pencarianfilter = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        tfCariFilm = new javax.swing.JTextField();
        cbPilihFilm = new javax.swing.JComboBox<>();
        cbPilihStudio = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        buttonCari = new javax.swing.JButton();
        buttonRefresh = new javax.swing.JButton();
        buttonTambah = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelDaftarTayang = new javax.swing.JTable();
        totalJadwal = new javax.swing.JLabel();

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
        sidebarDashboard.setForeground(new java.awt.Color(239, 239, 239));
        sidebarDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/home.png"))); // NOI18N
        sidebarDashboard.setText("  Dashboard");
        sidebarDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarDashboardMouseClicked(evt);
            }
        });

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
        sidebarCalendar.setForeground(new java.awt.Color(195, 156, 0));
        sidebarCalendar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png"))); // NOI18N
        sidebarCalendar.setText("  Jadwal");

        sidebarFnB.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarFnB.setForeground(new java.awt.Color(239, 239, 239));
        sidebarFnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/food.png"))); // NOI18N
        sidebarFnB.setText("  F&B");
        sidebarFnB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarFnBMouseClicked(evt);
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

        sidebarFnB1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        sidebarFnB1.setForeground(new java.awt.Color(239, 239, 239));
        sidebarFnB1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/person.png"))); // NOI18N
        sidebarFnB1.setText("Pegawai");
        sidebarFnB1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarFnB1MouseClicked(evt);
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
                            .addComponent(sidebarCalendar)
                            .addComponent(sidebarLogout)
                            .addComponent(sidebarFnB1))))
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
                .addComponent(sidebarFnB1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addComponent(sidebarLogout)
                .addGap(40, 40, 40))
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
        maincontentpanel.setToolTipText("Schedule Management");
        maincontentpanel.setPreferredSize(new java.awt.Dimension(1126, 730));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Manajemen Jadwal");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Kelola jadwal tayang film");

        panel_pencarianfilter.setBackground(new java.awt.Color(253, 254, 255));

        jLabel6.setBackground(new java.awt.Color(91, 95, 239));
        jLabel6.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(16, 25, 53));
        jLabel6.setText("Pencarian & Film");

        tfCariFilm.setForeground(new java.awt.Color(102, 102, 102));
        tfCariFilm.setText("Cari film...");
        tfCariFilm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfCariFilmMouseClicked(evt);
            }
        });
        tfCariFilm.addActionListener(this::tfCariFilmActionPerformed);

        cbPilihFilm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPilihFilm.setToolTipText("");
        cbPilihFilm.addActionListener(this::cbPilihFilmActionPerformed);

        cbPilihStudio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbPilihStudio.setToolTipText("");

        buttonCari.setBackground(new java.awt.Color(195, 156, 0));
        buttonCari.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        buttonCari.setForeground(new java.awt.Color(255, 255, 255));
        buttonCari.setText("Cari");
        buttonCari.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonCari.addActionListener(this::buttonCariActionPerformed);

        buttonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/reset.png"))); // NOI18N
        buttonRefresh.addActionListener(this::buttonRefreshActionPerformed);

        javax.swing.GroupLayout panel_pencarianfilterLayout = new javax.swing.GroupLayout(panel_pencarianfilter);
        panel_pencarianfilter.setLayout(panel_pencarianfilterLayout);
        panel_pencarianfilterLayout.setHorizontalGroup(
            panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                        .addComponent(tfCariFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbPilihFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbPilihStudio, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(buttonCari, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_pencarianfilterLayout.setVerticalGroup(
            panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_pencarianfilterLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tfCariFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbPilihFilm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbPilihStudio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_pencarianfilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(buttonRefresh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                        .addComponent(buttonCari, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        buttonTambah.setBackground(new java.awt.Color(195, 156, 0));
        buttonTambah.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        buttonTambah.setForeground(new java.awt.Color(255, 255, 255));
        buttonTambah.setText("+ Tambah Jadwal");
        buttonTambah.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        buttonTambah.setBorderPainted(false);
        buttonTambah.setPreferredSize(new java.awt.Dimension(155, 35));
        buttonTambah.addActionListener(this::buttonTambahActionPerformed);

        jPanel4.setBackground(new java.awt.Color(253, 254, 255));

        jLabel7.setBackground(new java.awt.Color(91, 95, 239));
        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(16, 25, 53));
        jLabel7.setText("Daftar Jadwal Tayang");

        tabelDaftarTayang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, "Edit | Hapus"},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Film", "Studio", "Tanggal", "Jam", "Harga", "Aksi"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelDaftarTayang);

        totalJadwal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalJadwal.setText("Total : -");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalJadwal)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 994, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(totalJadwal)
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout maincontentpanelLayout = new javax.swing.GroupLayout(maincontentpanel);
        maincontentpanel.setLayout(maincontentpanelLayout);
        maincontentpanelLayout.setHorizontalGroup(
            maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maincontentpanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, maincontentpanelLayout.createSequentialGroup()
                        .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panel_pencarianfilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(45, 45, 45))
        );
        maincontentpanelLayout.setVerticalGroup(
            maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(maincontentpanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(maincontentpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(maincontentpanelLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5))
                    .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(panel_pencarianfilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sidebarpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(maincontentpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTambahActionPerformed
        // TODO add your handling code here:
        CreateSchedule dialog = new CreateSchedule(this,true);
        dialog.setVisible(true);
        loadTable();
    }//GEN-LAST:event_buttonTambahActionPerformed

    private void tfCariFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfCariFilmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfCariFilmActionPerformed

    private void buttonCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCariActionPerformed
        // TODO add your handling code here:
        searchSchedule();
    }//GEN-LAST:event_buttonCariActionPerformed

    private void cbPilihFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPilihFilmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPilihFilmActionPerformed

    private void buttonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshActionPerformed
        // TODO add your handling code here:
        tfCariFilm.setText("");
        cbPilihFilm.setSelectedIndex(0);
        cbPilihStudio.setSelectedIndex(0);
        jDateChooser1.setDate(null);
        loadTable();
    }//GEN-LAST:event_buttonRefreshActionPerformed

    private void tfCariFilmMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfCariFilmMouseClicked
        // TODO add your handling code here:
        tfCariFilm.setText("");
    }//GEN-LAST:event_tfCariFilmMouseClicked

    private void sidebarDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarDashboardMouseClicked
        // TODO add your handling code here:
        this.dispose();
        new adminDashboard().setVisible(true);
    }//GEN-LAST:event_sidebarDashboardMouseClicked

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

    private void sidebarFnBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarFnBMouseClicked
        // TODO add your handling code here:
        this.dispose();
        JFrame frame = new JFrame("Bioskopin Ticketing System - Preview Mode");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 2. Instantiate your View panel (No import needed since it's in views.kasir)
      FnBManagementPanel seatView = new FnBManagementPanel();

      // 3. Mount the panel into the frame window
      frame.add(seatView);

      // 4. Let the window wrap perfectly around the panel's preferred bounds
      frame.pack();

      // 5. Center the window on the screen after packing
      frame.setLocationRelativeTo(null);

      // 6. Make the window visible
      frame.setVisible(true);
        
    }//GEN-LAST:event_sidebarFnBMouseClicked

    private void sidebarFnB1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidebarFnB1MouseClicked
        // TODO add your handling code here:
        this.dispose();
        new tampilDataPegawai().setVisible(true);
    }//GEN-LAST:event_sidebarFnB1MouseClicked

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

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ScheduleManagementFrame().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCari;
    private javax.swing.JButton buttonRefresh;
    private javax.swing.JButton buttonTambah;
    private javax.swing.JComboBox<String> cbPilihFilm;
    private javax.swing.JComboBox<String> cbPilihStudio;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel maincontentpanel;
    private javax.swing.JPanel panel_pencarianfilter;
    private javax.swing.JLabel sidebarCalendar;
    private javax.swing.JLabel sidebarDashboard;
    private javax.swing.JLabel sidebarFilm;
    private javax.swing.JLabel sidebarFnB;
    private javax.swing.JLabel sidebarFnB1;
    private javax.swing.JLabel sidebarLogout;
    private javax.swing.JLabel sidebarStudio;
    private javax.swing.JPanel sidebarpanel;
    private javax.swing.JTable tabelDaftarTayang;
    private javax.swing.JTextField tfCariFilm;
    private javax.swing.JLabel totalJadwal;
    // End of variables declaration//GEN-END:variables
}
