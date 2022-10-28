package ui;

import java.net.SocketTimeoutException;
import java.util.Scanner;

import model.RealEstate;

public class Main {
  private Scanner input;
  private RealEstate realEstate;

  public Main() {
    input = new Scanner(System.in);
    realEstate = new RealEstate();
  }

  public static void main(String[] args) {
    Main main = new Main();
    int option = 0;

    do {

      option = main.getOptionShowMenu();
      main.executeOption(option);

    } while (option != 0);

    main.input.close();
  }

  public void registerBuilding() {
    String id;
    String address;

    System.out.println("Enter the name (identifier) of the building.");
    id = input.next();
    System.out.println("Enter the address where the building is located.");
    address = input.next();

    System.out.println(realEstate.addBuilding(id, address));
  }

  public void registerApartment() {
    String id;
    int numberOfRooms;
    int numberOfBathrooms;
    String hasBalcony;
    double monthlyValue;
    String buildingId;

    System.out.println("Enter the number that identifies the apartment.");
    id = input.next();
    System.out.println("Enter the number of rooms in the apartment.");
    numberOfRooms = input.nextInt();
    System.out.println("Enter the number of bathrooms in the apartment.");
    numberOfBathrooms = input.nextInt();
    System.out.println("Enter Yes if the apartment has a balcony, otherwise enter No.");
    hasBalcony = input.next();
    System.out.println("Enter the monthly rental value of the apartment.");
    monthlyValue = input.nextDouble();
    System.out.println("Enter the name (identifier) of the building where the apartment will be registered.");
    buildingId = input.next();

    System.out.println(realEstate.registerApartmentToBuilding(id, numberOfRooms, numberOfBathrooms, hasBalcony, monthlyValue, buildingId));
  }
  
  public void uiRegisterOwner() {
    String identificationType;
    String id;
    String name;
    String telephoneNumber;
    String telephoneType;
    String bankAccountNumber;
    String bankName;
    int numberOfApartments;

    System.out.println("Enter the owner's identification type: TI, CC.");
    identificationType = input.next();
    System.out.println("Enter the owner ID.");
    id = input.next();
    System.out.println("Enter the owner's name");
    name = input.next();
    System.out.println("Enter the owner's telephone number.");
    telephoneNumber = input.next();
    System.out.println("Enter the owner's telephone type: HOME, OFFICE, MOVIL, FAMILY, OTHER.");
    telephoneType = input.next();
    System.out.println("Enter the owner's bank account number.");
    bankAccountNumber = input.next();
    System.out.println("Enter the name of the bank through which the owner will receive money.");
    bankName = input.next();
    System.out.println("Enter the number of apartments to be owned by the owner.");
    numberOfApartments = input.nextInt();

    System.out.println(realEstate.registerOwner(identificationType, id, name, telephoneNumber, telephoneType,
        bankAccountNumber, bankName, numberOfApartments));
  }
  
  public void uiEstablishApartmentToAnOwner() {
    String ownerId;
    String buildingId;
    String apartmentId;

    System.out.println("Enter the owner's ID to establish the apartment.");
    ownerId = input.next();
    System.out.println("Enter the name (identifier) of the building where the apartment to be established to the owner is located.");
    buildingId = input.next();
    System.out.println("Enter the number that identifies the apartment to be established to the owner.");
    apartmentId = input.next();

    System.out.println(realEstate.establishApartmentToAnOwner(ownerId, buildingId, apartmentId));
  }

  public void uiRegisterTenant() {
    String identificationType;
    String id;
    String name;
    String telephoneNumber;
    String telephoneType;
    String buildingId;
    String apartmentId;

    System.out.println("Enter the tenant's identification type: TI, CC.");
    identificationType = input.next();
    System.out.println("Enter the tenant ID.");
    id = input.next();
    System.out.println("Enter the tenant's name");
    name = input.next();
    System.out.println("Enter the tenant's telephone number.");
    telephoneNumber = input.next();
    System.out.println("Enter the tenant's telephone type: HOME, OFFICE, MOVIL, FAMILY, OTHER.");
    telephoneType = input.next();
    System.out.println("Enter the name (identifier) of the building where the apartment to be rented is located.");
    buildingId = input.next();
    System.out.println("Enter the number that identifies the apartment to rent.");
    apartmentId = input.next();

    System.out.println(realEstate.registerTenant(identificationType, id, name, telephoneNumber, telephoneType, buildingId, apartmentId));
  }

  public void uiCheckQuantityOfApartmentsAvailable() {
    String buildingId;

    System.out.println("Enter the name (identifier) of the building to which you want to know the number of apartments available.");
    buildingId = input.next();

    System.out.println(realEstate.checkQuantityOfApartmentsAvailable(buildingId));
  }
  
  public void uiCalculateTheTotalMonthlyValueOfABuilding() {
    String buildingId;

    System.out
        .println("Enter the name (identifier) of the building for which you want to know the total monthly value.");
    buildingId = input.next();

    System.out.println(realEstate.calculateTheTotalMonthlyValueOfABuilding(buildingId));
  }
  
  public void uiCheckApartmentAvailability() {
    String buildingId;
    String apartmentId;

    System.out.println(
        "Enter the name (identifier) of the building where the apartment you want to know its availability is located.");
    buildingId = input.next();
    System.out.println("Enter the number that identifies the apartment you want to know its availability.");
    apartmentId = input.next();

    System.out.println(realEstate.checkApartmentAvailability(buildingId, apartmentId));
  }
  
  public void uiCheckNumberOfApartmentsLeasedBySomeone() {
    String personId;

    System.out.println("Enter the ID of the person you want to know the number of owned apartments leased by others.");
    personId = input.next();

    System.out.println(realEstate.checkNumberOfApartmentsLeasedBySomeone(personId));
  }

  public void uiCheckTotalRentalValueOfAnOwner() {
    String personId;

    System.out.println("Enter the ID of the owner for whom you want to know the total rental value.");
    personId = input.next();

    System.out.println(realEstate.checkTotalRentalValueOfAnOwner(personId));
  }

  public int getOptionShowMenu() {
    int option = 0;
    System.out.println("<<<<< Welcome to RealEstate >>>>>");
    System.out.println(
    "(0) Exit. \n" +
    "(1) Register building. \n" +
    "(2) Register apartment. \n" +
    "(3) Register owner. \n" +
    "(4) Establish a person as the owner of an apartment. \n" +
    "(5) Register tenant. \n" +
    "(6) Ask how many apartments are available. \n" +
    "(7) Consult total monthly value of all the apartments in a building. \n" +
    "(8) Check availability of an apartment. \n" +
    "(9) Consult the number of owned apartments that an owner has leased. \n" +
    "(10) Consult the total rental value received by an owner.");
    option = input.nextInt();
    return option;
  }
  
  public void executeOption(int option){

    switch (option) {
      case 0:
        System.out.println("Exit program.");
        break;
        
      case 1:
        System.out.println("<<< Register building >>>");
        registerBuilding();
				break; 

      case 2:
        System.out.println("<<< Register apartment >>>");
        registerApartment();
				break; 

      case 3:
        System.out.println("<<< Register owner >>>");
        uiRegisterOwner();
        break;
        
      case 4:
        System.out.println("<<< Establish a person as the owner of an apartment >>>");
        uiEstablishApartmentToAnOwner();
        break;

      case 5:
        System.out.println("<<< Register tenant >>>");
        uiRegisterTenant();
				break; 

      case 6:
        System.out.println("<<< Ask how many apartments are available >>>");
        uiCheckQuantityOfApartmentsAvailable();
        break;

      case 7:
        System.out.println("<<< Consult total monthly value of all the apartments in a building >>>");
        uiCalculateTheTotalMonthlyValueOfABuilding();
        break;

      case 8:
        System.out.println("<<< Check availability of an apartment >>>");
        uiCheckApartmentAvailability();
        break;

      case 9:
        System.out.println("<<< Consult the number of owned apartments that an owner has leased >>>");
        uiCheckNumberOfApartmentsLeasedBySomeone();
        break;

      case 10:
        System.out.println("<<< Consult the total rental value received by an owner >>>");
        uiCheckTotalRentalValueOfAnOwner();
        break;

			default: 
				System.out.println("Invalid Option");
				break; 
		}
	}
}