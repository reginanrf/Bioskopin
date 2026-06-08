package views.kasir;

import models.BookingSession;
import controllers.BookingController;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class MainBookingFrame extends javax.swing.JFrame {

  private BookingSession currentSession;
  private BookingController bookingController;
  private CardLayout cardLayout;
  private JPanel mainContainer;

  private MovieSelectionPanel moviePanel;
  private ScheduleSelectionPanel schedulePanel;
  private SeatSelectionPanel seatPanel;
  private FnBPurchasePanel fnbPanel;
  private CheckoutTicketPanel checkoutPanel;

  public MainBookingFrame() {
    super("Bioskopin - Transaksi Kasir");

    // Initialize Controller & Session Model
    bookingController = new BookingController();
    currentSession = new BookingSession();
    
    cardLayout = new CardLayout();
    mainContainer = new JPanel(cardLayout);

    // Instantiate panels
    moviePanel = new MovieSelectionPanel(this);
    schedulePanel = new ScheduleSelectionPanel(this);
    seatPanel = new SeatSelectionPanel(this);
    fnbPanel = new FnBPurchasePanel(this);
    checkoutPanel = new CheckoutTicketPanel(this);

    // Add to Stack layout
    mainContainer.add(moviePanel, "MOVIE_PANEL");
    mainContainer.add(schedulePanel, "SCHEDULE_PANEL");
    mainContainer.add(seatPanel, "SEAT_PANEL");
    mainContainer.add(fnbPanel, "FNB_PANEL");
    mainContainer.add(checkoutPanel, "CHECKOUT_PANEL");

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setContentPane(mainContainer);
    this.setSize(950, 720);
    this.setLocationRelativeTo(null);
  }

  public BookingSession getSession() { return currentSession; }
  public BookingController getController() { return bookingController; }

  // Navigation Logic
  public void goToMovieSelection() { cardLayout.show(mainContainer, "MOVIE_PANEL"); moviePanel.loadMoviesFromDB(); }
  public void goToScheduleSelection() { cardLayout.show(mainContainer, "SCHEDULE_PANEL"); }
  public void refreshSchedulePanel() { schedulePanel.loadSchedulesForSelectedMovie(); }
  public void goToSeatSelection() { cardLayout.show(mainContainer, "SEAT_PANEL"); }
  public void refreshSeatPanel(int rows, int cols) { seatPanel.loadSeatGrid(rows, cols); }
  public void goToFnBSelection() { cardLayout.show(mainContainer, "FNB_PANEL"); fnbPanel.loadFnBFromDB(); }
  public void goToCheckout() { cardLayout.show(mainContainer, "CHECKOUT_PANEL"); }
  public void refreshCheckoutPanel() { checkoutPanel.generateReceipt(); }

  public void restartTransaction() {
    currentSession.resetSession();
    goToMovieSelection();
  }

  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {}
    java.awt.EventQueue.invokeLater(() -> {
      new MainBookingFrame().setVisible(true);
    });
  }
}