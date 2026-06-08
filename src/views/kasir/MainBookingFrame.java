package views.kasir;

import models.BookingSession;
import controllers.BookingController;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class MainBookingFrame extends javax.swing.JFrame {

    private BookingSession currentSession;
    private BookingController bookingController;
    private CardLayout cardLayout;

    // Transaction sub-panels
    private MovieSelectionPanel moviePanel;
    private ScheduleSelectionPanel schedulePanel;
    private SeatSelectionPanel seatPanel;
    private FnBPurchasePanel fnbPanel;
    private CheckoutTicketPanel checkoutPanel;

    // Layout containers
    private javax.swing.JPanel sidebarPanel;
    private javax.swing.JPanel mainContainer;

    // Sidebar navigation components
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel stepMovie;
    private javax.swing.JLabel stepSchedule;
    private javax.swing.JLabel stepSeat;
    private javax.swing.JLabel stepFnB;
    private javax.swing.JLabel stepCheckout;
    private javax.swing.JLabel sidebarLogout;

    // Colors tracking the design template
    private final Color COLOR_INACTIVE = new java.awt.Color(239, 239, 239);
    private final Color COLOR_ACTIVE = new java.awt.Color(204, 153, 0);

    public MainBookingFrame() {
        super("Bioskopin - Sistem Transaksi Kasir");

        // Initialize core models and controllers
        bookingController = new BookingController();
        currentSession = new BookingSession();

        initComponents();
        
        // Default highlight starting with step 1
        highlightSidebarStep(stepMovie);
    }

    private void initComponents() {
        sidebarPanel = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        stepMovie = new javax.swing.JLabel();
        stepSchedule = new javax.swing.JLabel();
        stepSeat = new javax.swing.JLabel();
        stepFnB = new javax.swing.JLabel();
        stepCheckout = new javax.swing.JLabel();
        sidebarLogout = new javax.swing.JLabel();

        cardLayout = new CardLayout();
        mainContainer = new javax.swing.JPanel(cardLayout);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 730));

        // Sidebar Panel Setup
        sidebarPanel.setBackground(new java.awt.Color(16, 25, 53));
        sidebarPanel.setPreferredSize(new java.awt.Dimension(240, 730));

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo_bioskopin.png"))); 

        Font sidebarFont = new java.awt.Font("Segoe UI Semibold", 0, 16);

        stepMovie.setFont(sidebarFont);
        stepMovie.setForeground(COLOR_INACTIVE);
        stepMovie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/home.png"))); // Adjust asset names if needed
        stepMovie.setText("  1. Pilih Film");

        stepSchedule.setFont(sidebarFont);
        stepSchedule.setForeground(COLOR_INACTIVE);
        stepSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/calendar.png")));
        stepSchedule.setText("  2. Pilih Jadwal");

        stepSeat.setFont(sidebarFont);
        stepSeat.setForeground(COLOR_INACTIVE);
        stepSeat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio.png")));
        stepSeat.setText("  3. Pilih Kursi");

        stepFnB.setFont(sidebarFont);
        stepFnB.setForeground(COLOR_INACTIVE);
        stepFnB.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/food.png")));
        stepFnB.setText("  4. Menu F&B");

        stepCheckout.setFont(sidebarFont);
        stepCheckout.setForeground(COLOR_INACTIVE);
        stepCheckout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/studio-1.png")));
        stepCheckout.setText("  5. Pembayaran");

        sidebarLogout.setFont(sidebarFont);
        sidebarLogout.setForeground(COLOR_INACTIVE);
        sidebarLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logout.png")));
        sidebarLogout.setText("  Keluar POS");
        sidebarLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidebarLogoutMouseClicked(evt);
            }
        });

        // Build Sidebar Layout Layout
        javax.swing.GroupLayout sidebarPanelLayout = new javax.swing.GroupLayout(sidebarPanel);
        sidebarPanel.setLayout(sidebarPanelLayout);
        sidebarPanelLayout.setHorizontalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(sidebarPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(stepMovie)
                            .addComponent(stepSchedule)
                            .addComponent(stepSeat)
                            .addComponent(stepFnB)
                            .addComponent(stepCheckout)
                            .addComponent(sidebarLogout))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        sidebarPanelLayout.setVerticalGroup(
            sidebarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidebarPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(stepMovie)
                .addGap(18, 18, 18)
                .addComponent(stepSchedule)
                .addGap(18, 18, 18)
                .addComponent(stepSeat)
                .addGap(18, 18, 18)
                .addComponent(stepFnB)
                .addGap(18, 18, 18)
                .addComponent(stepCheckout)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)
                .addComponent(sidebarLogout)
                .addGap(40, 40, 40))
        );

        // Instantiate step panels and add to stack
        moviePanel = new MovieSelectionPanel(this);
        schedulePanel = new ScheduleSelectionPanel(this);
        seatPanel = new SeatSelectionPanel(this);
        fnbPanel = new FnBPurchasePanel(this);
        checkoutPanel = new CheckoutTicketPanel(this);

        mainContainer.add(moviePanel, "MOVIE_PANEL");
        mainContainer.add(schedulePanel, "SCHEDULE_PANEL");
        mainContainer.add(seatPanel, "SEAT_PANEL");
        mainContainer.add(fnbPanel, "FNB_PANEL");
        mainContainer.add(checkoutPanel, "CHECKOUT_PANEL");

        // Master Layout Definition
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sidebarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1120, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sidebarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 718, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }

    // Highlighting engine matching the active menu theme color
    private void highlightSidebarStep(javax.swing.JLabel activeLabel) {
        stepMovie.setForeground(COLOR_INACTIVE);
        stepSchedule.setForeground(COLOR_INACTIVE);
        stepSeat.setForeground(COLOR_INACTIVE);
        stepFnB.setForeground(COLOR_INACTIVE);
        stepCheckout.setForeground(COLOR_INACTIVE);
        
        activeLabel.setForeground(COLOR_ACTIVE);
    }

    // Global session and transaction engine getters
    public BookingSession getSession() { return currentSession; }
    public BookingController getController() { return bookingController; }

    // Enhanced navigation logic linked directly to sidebar state updates
    public void goToMovieSelection() { 
        cardLayout.show(mainContainer, "MOVIE_PANEL"); 
        moviePanel.loadMoviesFromDB(); 
        highlightSidebarStep(stepMovie);
    }
    
    public void goToScheduleSelection() { 
        cardLayout.show(mainContainer, "SCHEDULE_PANEL"); 
        highlightSidebarStep(stepSchedule);
    }
    
    public void refreshSchedulePanel() { 
        schedulePanel.loadSchedulesForSelectedMovie(); 
    }
    
    public void goToSeatSelection() { 
        cardLayout.show(mainContainer, "SEAT_PANEL"); 
        highlightSidebarStep(stepSeat);
    }
    
    public void refreshSeatPanel(int rows, int cols) { 
        seatPanel.loadSeatGrid(rows, cols); 
    }
    
    public void goToFnBSelection() { 
        cardLayout.show(mainContainer, "FNB_PANEL"); 
        fnbPanel.loadFnBFromDB(); 
        highlightSidebarStep(stepFnB);
    }
    
    public void goToCheckout() { 
        cardLayout.show(mainContainer, "CHECKOUT_PANEL"); 
        checkoutPanel.generateReceipt(); 
        highlightSidebarStep(stepCheckout);
    }
    
    public void refreshCheckoutPanel() {
        if (checkoutPanel != null) {
            checkoutPanel.generateReceipt();
        }
    }

    public void restartTransaction() {
        currentSession.resetSession();
        goToMovieSelection();
    }

    private void sidebarLogoutMouseClicked(java.awt.event.MouseEvent evt) {
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin membatalkan transaksi dan keluar dari sistem kasir?",
            "Konfirmasi Keluar",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE);

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            utils.Session.clear();
            this.dispose();
            
            // Re-open admin login frame or fallback view
            new views.admin.login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new MainBookingFrame().setVisible(true);
        });
    }
}