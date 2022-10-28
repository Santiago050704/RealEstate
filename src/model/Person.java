package model;

public abstract class Person {
  private String identificationType;
  private String id;
  private String name;
  private String telephoneNumber;
  private TelephoneType telephoneType;

  public Person(String identicationType, String id, String name, String telephoneNumber, TelephoneType telephoneType) {
    this.identificationType = identicationType;
    this.id = id;
    this.name = name;
    this.telephoneNumber = telephoneNumber;
    this.telephoneType = telephoneType;
  }

  public String getIdentificationType() {
    return identificationType;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}