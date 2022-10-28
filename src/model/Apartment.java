package model;

public class Apartment {
  private String id;
  private int numberOfRooms;
  private int numberOfBathrooms;
  private boolean hasBalcony;
  private double monthlyValue;
  private boolean isAvailable;

  public Apartment(String id, int numberOfRooms, int numberOfBathrooms, boolean hasBalcony, double monthlyValue) {
    this.id = id;
    this.numberOfRooms = numberOfRooms;
    this.numberOfBathrooms = numberOfBathrooms;
    this.hasBalcony = hasBalcony;
    this.monthlyValue = monthlyValue;
    isAvailable = true;
  }

  public String getId() {
    return id;
  }

  public int getNumberOfRooms() {
    return numberOfRooms;
  }

  public int getNumberOfBathrooms() {
    return numberOfBathrooms;
  }

  public boolean getHasBalcony() {
    return hasBalcony;
  }

  public double getMonthlyValue() {
    return monthlyValue;
  }

  public boolean getAvailability() {
    return isAvailable;
  }

  public void setAvailability(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}