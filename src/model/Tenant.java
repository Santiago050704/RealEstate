package model;

public class Tenant extends Person {
  
  private Apartment apartment = null;

  public Tenant(String identificationType, String id, String name, String telephoneNumber, TelephoneType telephoneType, Apartment apartment) {
    super(identificationType, id, name, telephoneNumber, telephoneType);
    this.apartment = apartment;
  }
}