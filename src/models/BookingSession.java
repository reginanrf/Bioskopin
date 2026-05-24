package models;

import java.util.ArrayList;

public class BookingSession {
  // Movie & Schedule Details
  private int idMovie;
  private String movieTitle;
  private String scheduleTime;
  private String studioName;
  private int moviePrice;

  // Seat Details
  private ArrayList<String> selectedSeats = new ArrayList<>();

  // FnB Details (Can be a list of custom FnB objects, keeping it simple for now
  // as Strings/Prices)
  private ArrayList<String> fnbItems = new ArrayList<>();
  private int fnbTotalCost = 0;

  // Getters and Setters (You can generate these in NetBeans: Right Click ->
  // Insert Code -> Getter and Setter)

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public String getScheduleTime() {
    return scheduleTime;
  }

  public void setScheduleTime(String scheduleTime) {
    this.scheduleTime = scheduleTime;
  }

  public String getStudioName() {
    return studioName;
  }

  public void setStudioName(String studioName) {
    this.studioName = studioName;
  }

  public int getMoviePrice() {
    return moviePrice;
  }

  public void setMoviePrice(int moviePrice) {
    this.moviePrice = moviePrice;
  }

  public ArrayList<String> getSelectedSeats() {
    return selectedSeats;
  }

  public void setSelectedSeats(ArrayList<String> selectedSeats) {
    this.selectedSeats = selectedSeats;
  }

  public void addFnBItem(String itemName, int price) {
    this.fnbItems.add(itemName);
    this.fnbTotalCost += price;
  }

  public ArrayList<String> getFnbItems() {
    return fnbItems;
  }

  public int getFnbTotalCost() {
    return fnbTotalCost;
  }

  public int getGrandTotal() {
    return (selectedSeats.size() * moviePrice) + fnbTotalCost;
  }

  // Clear session for the next customer
  public void resetSession() {
    movieTitle = "";
    scheduleTime = "";
    studioName = "";
    moviePrice = 0;
    selectedSeats.clear();
    fnbItems.clear();
    fnbTotalCost = 0;
  }
}
