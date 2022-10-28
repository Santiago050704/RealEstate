package model;

public class Owner extends Person{
  private String bankAccountNumber;
  private String bankName;
  private int numberOfAparments;

  private Apartment[] ownerApartments;

  public Owner(String identificationType, String id, String name, String telephoneNumber, TelephoneType telephoneType,
      String bankAccountNumber, String bankName, int numberOfAparments) {
    super(identificationType, id, name, telephoneNumber, telephoneType);
    this.bankAccountNumber = bankAccountNumber;
    this.bankName = bankName;
    this.numberOfAparments = numberOfAparments;
    ownerApartments = new Apartment[this.numberOfAparments];
  }
  
  public Apartment[] getApartmentsOfOwner() {
    return ownerApartments;
  }
}