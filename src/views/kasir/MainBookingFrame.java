package views.kasir;

import models.BookingSession;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 * @author riikq
 */
public class MainBookingFrame extends javax.swing.JFrame {

  private BookingSession currentSession;
  private CardLayout cardLayout;
  private JPanel mainContainer;

  // The individual Wizard Panels
  private MovieSelectionPanel moviePanel;
  private ScheduleSelectionPanel schedulePanel;
  private SeatSelectionPanel seatPanel;
  private FnBPurchasePanel fnbPanel;
  private CheckoutTicketPanel checkoutPanel;

  public MainBookingFrame() {
    super("Bioskopin - Transaksi Kasir");

    // 1. Initialize Session and Layout
    currentSession = new BookingSession();
    cardLayout = new CardLayout();
    mainContainer = new JPanel(cardLayout);

    // 2. Instantiate the panels
    moviePanel = new MovieSelectionPanel(this);
    schedulePanel = new ScheduleSelectionPanel(this);
    seatPanel = new SeatSelectionPanel(this);
    fnbPanel = new FnBPurchasePanel(this);
    checkoutPanel = new CheckoutTicketPanel(this);

    // 3. Add panels to the CardLayout stack
    mainContainer.add(moviePanel, "MOVIE_PANEL");
    mainContainer.add(schedulePanel, "SCHEDULE_PANEL");
    mainContainer.add(seatPanel, "SEAT_PANEL");
    mainContainer.add(fnbPanel, "FNB_PANEL");
    mainContainer.add(checkoutPanel, "CHECKOUT_PANEL");

    // 4. Mount container to the window
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    this.setContentPane(mainContainer);
    this.setSize(900, 700);
    this.setLocationRelativeTo(null);
  }

  public BookingSession getSession() {
    return currentSession;
  }

  // --- NAVIGATION LOGIC ---
  public void goToMovieSelection() {
    cardLayout.show(mainContainer, "MOVIE_PANEL");
  }

  public void goToScheduleSelection() {
    cardLayout.show(mainContainer, "SCHEDULE_PANEL");
  }

  public void refreshSchedulePanel() {
    schedulePanel.loadSchedulesForSelectedMovie();
  }

  public void goToSeatSelection() {
    cardLayout.show(mainContainer, "SEAT_PANEL");
  }

  public void refreshSeatPanel(int capacity) {
    seatPanel.loadSeatGrid(capacity);
  }

  public void goToFnBSelection() {
    cardLayout.show(mainContainer, "FNB_PANEL");
    fnbPanel.refreshPanel();
  }

  public void goToCheckout() {
    cardLayout.show(mainContainer, "CHECKOUT_PANEL");
  }

  public void refreshCheckoutPanel() {
    checkoutPanel.generateReceipt();
  }

  public void restartTransaction() {
    currentSession.resetSession(); // Clear all data
    goToMovieSelection(); // Back to Step 1
  }

  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    }

    java.awt.EventQueue.invokeLater(() -> {
      new MainBookingFrame().setVisible(true);
    });
  }
}
