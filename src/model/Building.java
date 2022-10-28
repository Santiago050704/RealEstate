package model;

public class Building {
  private String id;
  private String address;

  public static final int NUMBER_OF_APARTMENTS = 10;

  private Apartment[] apartments;

  public Building(String id, String address) {
    this.id = id;
    this.address = address;
    apartments = new Apartment[NUMBER_OF_APARTMENTS];
  }

  public String getId() {
    return id;
  }

  public String getAddress() {
    return address;
  }

  public Apartment[] getApartments() {
    return apartments;
  }
}