package model;

public class RealEstate {

  public static final int NUMBER_OF_PEOPLE = 20;
  public static final int NUMBER_OF_BUILDINGS = 5;
  public static final int TOTAL_NUMBER_OF_APARTMENTS = 50;

  private int numberOfOwnedApartments = 0;

  private Person[] people;
  private Building[] buildings;

  public RealEstate() {
    people = new Person[NUMBER_OF_PEOPLE];
    buildings = new Building[NUMBER_OF_BUILDINGS];
  }

  public Person[] getPeople() {
    return people;
  }

  public Building[] getBuildings() {
    return buildings;
  }

  public String addBuilding(String id, String address) {
    String msj = "";
    boolean wasAdded = false;
    boolean theBuildingAlreadyExists = false;
    Building building = new Building(id, address);
    for (int i = 0; i < buildings.length && !theBuildingAlreadyExists; i++) {
      if (buildings[i] != null && building.getId().equalsIgnoreCase(buildings[i].getId())) {
        theBuildingAlreadyExists = true;
      }
    }
    if (theBuildingAlreadyExists == false) {
      for (int i = 0; i < buildings.length && !wasAdded; i++) {
        if (buildings[i] == null) {
          buildings[i] = building;
          wasAdded = true;
          msj = "The building was successfully registered.";
        }
      }
      if (wasAdded == false) {
        msj = "The building could not be added. Maximum capacity reached.";
      }
    } else {
      msj = "The building identifier already exists, so the building could not be registered.";
    }
    return msj;
  }

  public String registerApartmentToBuilding(String id, int numberOfRooms, int numberOfBathrooms, String hasBalcony,
      double monthlyValue, String buildingId) {
    String msj = "";
    boolean wasAdded = false;
    boolean theApartmentAlreadyExists = false;
    boolean thereIsBalcony = false;
    int posBuilding = searchBuildingById(buildingId);
    if (posBuilding != -1) {
      for (int i = 0; i < buildings[posBuilding].getApartments().length && !theApartmentAlreadyExists; i++) {
        if (buildings[posBuilding].getApartments()[i] != null
            && id.equalsIgnoreCase(buildings[posBuilding].getApartments()[i].getId())) {
          theApartmentAlreadyExists = true;
        }
      }
      if (theApartmentAlreadyExists == false) {
        if (hasBalcony.equalsIgnoreCase("Yes")) {
          thereIsBalcony = true;
          Apartment apartment = new Apartment(id, numberOfRooms, numberOfBathrooms, thereIsBalcony, monthlyValue);
          for (int i = 0; i < buildings[posBuilding].getApartments().length && !wasAdded; i++) {
            if (buildings[posBuilding].getApartments()[i] == null) {
              buildings[posBuilding].getApartments()[i] = apartment;
              wasAdded = true;
              msj = "The apartment was successfully registered.";
            }
          }
          if (wasAdded == false) {
            msj = "The apartment could not be added. Maximum capacity reached.";
          }
        } else if (hasBalcony.equalsIgnoreCase("No")) {
          Apartment apartment = new Apartment(id, numberOfRooms, numberOfBathrooms, thereIsBalcony, monthlyValue);
          for (int i = 0; i < buildings[posBuilding].getApartments().length && !wasAdded; i++) {
            if (buildings[posBuilding].getApartments()[i] == null) {
              buildings[posBuilding].getApartments()[i] = apartment;
              wasAdded = true;
              msj = "The apartment was successfully registered";
            }
          }
          if (wasAdded == false) {
            msj = "The apartment could not be added. Maximum capacity reached.";
          }
        } else {
          msj = "The option registered for the balcony is not valid.";
        }
      } else {
        msj = "The apartment identifier already exists, so the apartment was not registered.";
      }
    } else {
      msj = "The building to which you want to register the apartment does not exist.";
    }
    return msj;
  }
  
  public String registerOwner(String identificationType, String id, String name, String telephoneNumber,
      String telephoneType, String bankAccountNumber, String bankName, int numberOfAparments) {
    String msj = "";
    boolean wasAdded = false;
    boolean theIdentificationTypeExists = confirmIdentificationType(identificationType);
    boolean theTelephoneTypeExists = confirmTelephoneType(telephoneType);
    TelephoneType typeOfTelephone;
    int posPerson = searchPersonById(id);
    if (theIdentificationTypeExists == true) {
      if (theTelephoneTypeExists == true) {
        if (posPerson == -1) {
          typeOfTelephone = TelephoneType.valueOf(telephoneType.toUpperCase());
          Person owner = new Owner(identificationType, id, name, telephoneNumber, typeOfTelephone, bankAccountNumber,
              bankName, numberOfAparments);
          if (numberOfAparments + numberOfOwnedApartments < TOTAL_NUMBER_OF_APARTMENTS) {
            for (int i = 0; i < people.length && !wasAdded; i++) {
              if (people[i] == null) {
                people[i] = owner;
                wasAdded = true;
                numberOfOwnedApartments += numberOfAparments;
                msj = "The owner was successfully registered.";
              } else {
                msj = "The maximum number of users within the company has already been reached.";
              }
            }
          } else {
            msj = "Apartments cannot be registered to the new owner because the maximum capacity has been reached.";
          }
        } else {
          msj = "The user you are trying to register already exists in the database.";
        }
      } else {
        msj = "The entered phone type does not exist.";
      }
    } else {
      msj = "The entered identification type does not exist.";
    }
    return msj;
  }
  
  public String registerTenant(String identificationType, String id, String name, String telephoneNumber,
      String telephoneType, String buildingId, String apartmentId) {
    String msj = "";
    boolean wasAdded = false;
    boolean theIdentificationTypeExists = confirmIdentificationType(identificationType);
    boolean theTelephoneTypeExists = confirmTelephoneType(telephoneType);
    TelephoneType typeOfTelephone;
    int posBulding = searchBuildingById(buildingId);
    int posApartment = searchApartmentById(buildingId, apartmentId);
    int posPerson = searchPersonById(id);
    if (theIdentificationTypeExists == true) {
      if (theTelephoneTypeExists == true) {
        if (posPerson == -1) {
          if (posBulding != -1) {
            if (posApartment != -1) {
              typeOfTelephone = TelephoneType.valueOf(telephoneType.toUpperCase());
              Person tenant = new Tenant(identificationType, id, name, telephoneNumber, typeOfTelephone, buildings[posBulding].getApartments()[posApartment]);
              boolean isAvailable = false;
              buildings[posBulding].getApartments()[posApartment].setAvailability(isAvailable);
              for (int i = 0; i < people.length && !wasAdded; i++) {
                if (people[i] == null) {
                  people[i] = tenant;
                  wasAdded = true;
                  msj = "The tenant was successfully registered.";
                } else {
                  msj = "The maximum number of users within the company has already been reached.";
                }
              }
            } else {
              msj = "The new tenant's apartment does not exist.";
            }
          } else {
            msj = "The building where the new tenant's apartment is located does not exist.";
          }
        } else {
          msj = "The user you are trying to register already exists in the database.";
        }
      } else {
        msj = "The entered phone type does not exist.";
      }
    } else {
      msj = "The entered identification type does not exist.";
    }
    return msj;
  }
  
  public String establishApartmentToAnOwner(String ownerId, String buildingId, String apartmentId) {
    String msj = "";
    boolean wasEstablished = false;
    int posBuilding = searchBuildingById(buildingId);
    int posApartment = searchApartmentById(buildingId, apartmentId);
    int posOwner = searchPersonById(ownerId);
    if (posOwner != -1) {
      if (people[posOwner] instanceof Owner) {
        if (posBuilding != -1) {
          if (posApartment != -1) {
            for (int i = 0; i < ((Owner) (people[posOwner])).getApartmentsOfOwner().length && !wasEstablished; i++) {
              if (((Owner) (people[posOwner])).getApartmentsOfOwner()[i] == null) {
                ((Owner) (people[posOwner])).getApartmentsOfOwner()[i] = buildings[posBuilding]
                  .getApartments()[posApartment];
                wasEstablished = true;
                msj = "The apartment was properly set up for the owner.";
              } else {
                msj = "The number of apartments that the owner can have has already been reached.";
              }
            }
          } else {
            msj = "The apartment you are looking for does not exist.";
          }
        } else {
          msj = "The building you are looking for does not exist.";
        }
      } else {
        msj = "The ID entered does not belong to an owner.";
      }
    } else {
      msj = "The owner sought does not exist.";
    }
    return msj;
  }
  
  public boolean confirmIdentificationType(String identificationType) {
    String[] identificationTypes = { "TI", "CC" };
    boolean wasFound = false;
    for (int i = 0; i < identificationTypes.length && !wasFound; i++) {
      if (identificationType.equalsIgnoreCase(identificationTypes[i])) {
        wasFound = true;
      }
    }
    return wasFound;
  }

  public boolean confirmTelephoneType(String telephoneType) {
    String[] telephoneTypes = { "Home", "Office", "Movil", "Family", "Other" };
    boolean wasFound = false;
    for (int i = 0; i < telephoneTypes.length && !wasFound; i++) {
      if (telephoneType.equalsIgnoreCase(telephoneTypes[i])) {
        wasFound = true;
      }
    }
    return wasFound;
  }

  public String checkQuantityOfApartmentsAvailable(String buildingId) {
    String msj = "";
    int availableApartments = 0;
    int posBuilding = searchBuildingById(buildingId);
    if (posBuilding != -1) {
      for (int i = 0; i < buildings[posBuilding].getApartments().length; i++) {
        if (buildings[posBuilding].getApartments()[i] != null && buildings[posBuilding].getApartments()[i].getAvailability() == true) {
          availableApartments++;
        }
      }
      msj = "The number of apartments available within the building " + buildingId + " is " + availableApartments;
    } else {
      msj = "The building you are looking for does not exist.";
    }
    return msj;
  }

  public String calculateTheTotalMonthlyValueOfABuilding(String buildingId) {
    String msj = "";
    double totalMonthlyValue = 0;
    int posBuilding = searchBuildingById(buildingId);
    if (posBuilding != -1) {
      for (int i = 0; i < buildings[posBuilding].getApartments().length; i++) {
        if (buildings[posBuilding].getApartments()[i] != null && buildings[posBuilding].getApartments()[i].getAvailability() == false) {
          totalMonthlyValue += buildings[posBuilding].getApartments()[i].getMonthlyValue();
        }
      }
      msj = "The total monthly value of all rented apartments within building " + buildingId + " is " + totalMonthlyValue;
    } else {
      msj = "The building you are looking for does not exist.";
    }
    return msj;
  }

  public String checkApartmentAvailability(String buildingId, String apartmentId) {
    String msj = "";
    int posBuilding = searchBuildingById(buildingId);
    int posApartment = searchApartmentById(buildingId, apartmentId);
    if (posBuilding != -1) {
      if (posApartment != -1) {
        if (buildings[posBuilding].getApartments()[posApartment].getAvailability() == true) {
          msj = "Apartment " + apartmentId + ", which is located inside building " + buildingId + " is available.";
        } else {
          msj = "The apartment you are looking for is not available.";
        }
      } else {
        msj = "The apartment you are looking for does not exist.";
      }
    } else {
      msj = "The building you are looking for does not exist.";
    }
    return msj;
  }

  public String checkNumberOfApartmentsLeasedBySomeone(String personId) {
    String msj = "";
    int numberOfLeasedApartments = 0;
    int posPerson = searchPersonById(personId);
    if (posPerson != -1) {
      if (people[posPerson] instanceof Owner) {
        for (int i = 0; i < ((Owner) (people[posPerson])).getApartmentsOfOwner().length; i++) {
          if (((Owner) (people[posPerson])).getApartmentsOfOwner()[i] != null && ((Owner) (people[posPerson])).getApartmentsOfOwner()[i].getAvailability() == false) {
            numberOfLeasedApartments++;
          }
        }
        msj = "The number of leased apartments owned by owner " + people[posPerson].getName() + " is "
            + numberOfLeasedApartments;
      } else {
        msj = "The person sought is not an owner, so he/she cannot own apartments leased by others.";
      }
    } else {
      msj = "The searched person does not exist in the system database.";
    }
    return msj;
  }
  
  public String checkTotalRentalValueOfAnOwner(String personId) {
    String msj = "";
    double totalRentalValue = 0;
    int posPerson = searchPersonById(personId);
    if (posPerson != -1) {
      if (people[posPerson] instanceof Owner) {
        for (int i = 0; i < ((Owner) (people[posPerson])).getApartmentsOfOwner().length; i++) {
          if (((Owner) (people[posPerson])).getApartmentsOfOwner()[i] != null && ((Owner) (people[posPerson])).getApartmentsOfOwner()[i].getAvailability() == false) {
            totalRentalValue += ((Owner) (people[posPerson])).getApartmentsOfOwner()[i].getMonthlyValue() * 0.9;
          }
        }
        msj = "The amount of money that owner " + people[posPerson].getName() + " would receive from the lease is " + totalRentalValue;
      } else {
        msj = "The person sought is not an owner, so he/she cannot own apartments leased by others.";
      }
    } else {
      msj = "The searched person does not exist in the system database.";
    }
    return msj;
  }
  
  public int searchBuildingById(String buildingId) {
    int pos = -1;
    boolean wasFound = false;
    for (int i = 0; i < buildings.length && !wasFound; i++) {
      if (buildings[i] != null && buildings[i].getId().equalsIgnoreCase(buildingId)) {
        pos = i;
        wasFound = true;
      }
    }
    return pos;
  }

  public int searchApartmentById(String buildingId, String apartmentId) {
    int pos = -1;
    boolean wasFound = false;
    int posBuilding = searchBuildingById(buildingId);
    if (posBuilding != -1) {
      for (int i = 0; i < buildings[posBuilding].getApartments().length && !wasFound; i++) {
        if (buildings[posBuilding].getApartments()[i].getId().equalsIgnoreCase(apartmentId)) {
          pos = i;
          wasFound = true;
        }
      }
    }
    return pos;
  }

  public int searchPersonById(String personId) {
    int pos = -1;
    boolean wasFound = false;
    for (int i = 0; i < people.length && !wasFound; i++) {
      if (people[i] != null && people[i].getId().equalsIgnoreCase(personId)) {
        pos = i;
        wasFound = true;
      }
    }
    return pos;
  }
}