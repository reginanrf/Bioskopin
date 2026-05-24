package views.kasir;

import javax.swing.*;
import views.admin.FnBManagementPanel; // Keep this if you plan to switch previews later

/**
 * @author riikq
 */
public class TestRunner {
  public static void main(String[] args) {
    // Run the GUI creation on the Event Dispatch Thread (EDT) for thread safety
    SwingUtilities.invokeLater(() -> {
      try {
        // Set native system look and feel so it matches your OS layout style
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (Exception e) {
        // Fallback gracefully if system skin fails to load
      }

      // 1. Create the outer window frame (The App Window)
      JFrame frame = new JFrame("Bioskopin Ticketing System - Preview Mode");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      // 2. Instantiate your View panel (No import needed since it's in views.kasir)
      SeatSelectionPanel seatView = new SeatSelectionPanel();

      // 3. Mount the panel into the frame window
      frame.add(seatView);

      // 4. Let the window wrap perfectly around the panel's preferred bounds
      frame.pack();

      // 5. Center the window on the screen after packing
      frame.setLocationRelativeTo(null);

      // 6. Make the window visible
      frame.setVisible(true);
    });
  }
}
