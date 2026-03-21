//-----------------------------------------------------------------------------
//Assignment 1
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------

package driver_A1COMP249;

import clientPackage.*;
import travelPackage.*;
import exceptions.*;
import service.SmartTravelService;

import java.util.Arrays;
import java.util.Scanner;


public class Driver_A1_COMP249 {

	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {

		String welcomeMessage = """
				\n
				Assignment by Wania Faraz and Zahira Atmani.

				Welcome to the SmartTravel booking management system.
				""";

		System.out.println(welcomeMessage + "\n");

		String discard; // whenever its needed to catch discarded input from keyboard
		final boolean ZERO_ACCEPTED = true; // to pass to validate menu option - to know whether a menu has a 0 option or not

		SmartTravelService service = new SmartTravelService();
		// arrays
		//Creation of arrays
		// Will be filled with data from service package in menu option 9: Load all data, using service methods
		Client[] clients = service.getClients(); // clients in same order as they are in names array
		Trip[] trips = service.getTrips(); // trips of all clients
		Transportation[] flights = service.flights;
		Transportation[] trains = service.trains;
		Transportation[] buses = service.buses;
		Transportation[][] transports = {flights, trains, buses}; // all transports in one array but separated: flights, trains, buses
		Accommodation[] hotels = service.hotels;
		Accommodation[] hostels = service.hostels;
		Accommodation[][] accommodations = {hotels, hostels}; // all accommodations in one array, but separated

		//to store when retrieved from SmartTravelService
		int numClients = 0;
		int numTrips = 0;

		// keep track of the number of items in each array
		int[] numSavedTransports = new int[3];
		int[] numSavedAccommodations = new int[2];
		int FLIGHTS_INDEX = 0; // index of numSavedTransports where flights are stored
		int TRAINS_INDEX = 1; // index of numSavedTransports where trains are stored
		int BUSES_INDEX = 2; // index of numSavedTransports where flights are stored
		int HOTELS_INDEX = 0; // index of numSavedAccommodations where hotels are stored
		int HOSTELS_INDEX = 1; // index of numSavedAccommodations where hotels are stored

		boolean dataLoaded = false; //to keep track of whether the data has been loaded

		String mainMenu = """
				1. Client Management
				2. Trip Management
				3. Transportation Management
				4. Accommodation Management
				5. Additional Operations
				6. List All Data Summary
				7. Load All Data (output/data/*.csv)
				8. Save All Data (output/data/*.csv)
				9. Run predefined scenario
				10. Generate Dashboard <-- HTML + charts
				0. Exit

				Please select an option from the above menu:""" + " ";
		final int MAIN_MENU_MAX = 10;

		String clientManagementMenu = """
				Client Management Menu
				\t1. Add a client
				\t2. Edit a client
				\t3. Delete a client
				\t4. List all clients
				\t5. Back to Main Menu
				Please select an option from the above menu: """ + " ";
		final int CLIENT_MANAGEMENT_MENU_MAX = 5;

		String clientEditMenu = """
				Which one would you like to edit?
				\t1. Client's first name
				\t2. Client's last name
				\t3. Client's email address
				\t4. Back to previous menu
				Please enter your choice here: """ +" ";
		final int CLIENT_EDIT_MENU_MAX = 4;

		String tripManagementMenu = """
				Trip Management Menu
				\t1. Create a trip
				\t2. Edit Trip Information
				\t3. Cancel a trip
				\t4. List all trips
				\t5. List all trips for a specific client
				\t6. Back to Main Menu

				Please select an option from the above menu:""" + " ";
		final int TRIP_MANAGEMENT_MENU_MAX = 6;

		String tripEditMenu = """
				Which one would you like to edit?
					1. Trip's destination
					2. Trip's destination
					3. Trip's base price
					4. Trip's client
					5. Trip's accommodation
					6. Trip's transportation
					7. Back to previous menu
				Please enter your choice here:""" + " ";
		final int TRIP_EDIT_MENU_MAX = 7;

		String transportationManagementMenu = """
				Transportation Management Menu
					1. Add a transportation option
					2. Remove a transportation option
					3. List transportation options by type (Flight, Train, Bus)
					4. Back to Main Menu

				Please select an option from the above menu: """ + " ";
		final int TRANSPORTATION_MANAGEMENT_MENU_MAX = 4;

		String transportationTypeMenu = """
				Please select an option from the menu below:
					0. Flight
					1. Train
					2. Bus
					3. Back to previous menu
				Your choice:""" + " ";
		final int TRANSPORTATION_TYPE_MENU_MAX = 3;
		String[] transportTypes = { "Flight", "Train", "Bus" }; // the order of options in the menu and strings in
																// the array match the order of indices of
																// numSavedTransports array

		String accommodationManagementMenu = """
				Accommodation Management Menu
					1. Add an accommodation
					2. Remove an accommodation
					3. List accommodations by type (Hotel, Hostel)
					4. Back to Main Menu

				Please select an option from the above menu:""" + " ";
		final int ACCOMMODATION_MANAGEMENT_MENU_MAX = 4;

		String accommodationTypeMenu = """
				Please select an option from the menu below:
					0. Hotel
					1. Hostel
					2. Back to previous menu
				Your choice:""" + " ";
		final int ACCOMMODATION_TYPE_MENU_MAX = 2;
		String[] accommodationTypes = { "Hotel", "Hostel" };// the order of options in the menu and strings in the
															// array match the order of indices of
															// numSavedAccommodations array

		String additionalOperationsMenu = """
				Additional Operations
					1. Display the most expensive trip
					2. Calculate and display the total cost of a trip
					3. Create a deep copy of the transportation array
					4. Create a deep copy of the accommodation array
					5. Back to Main Menu

				Please select an option from the above menu:""" + " ";
		final int ADDITIONAL_OPERATIONS_MENU_MAX = 5;

		boolean runMainMenu = true;
		int option = 0;
		int subMenuOption = 0;
		int index = 0; // to store an index whenever necessary
		String ID; //to store the ID of clients, trips, transportations, and accommodations
		while (runMainMenu) {
			option = validateMenuOption(mainMenu, MAIN_MENU_MAX, ZERO_ACCEPTED);
			// each subMenu below uses switch to manage flow. No need for a default case
			// since the validateMenuOption method ensures that all input is valid
			System.out.println();
			if (option == 1) { // CLIENT MANAGEMENT
				while (subMenuOption != CLIENT_MANAGEMENT_MENU_MAX) {
					// prompt user to choose a menu option and validate input
					subMenuOption = validateMenuOption(clientManagementMenu, CLIENT_MANAGEMENT_MENU_MAX,
							!ZERO_ACCEPTED);
					System.out.println();
					// variables required in more than one client management menu option
					String firstName;
					String lastName;
					String emailAddress;
					boolean clientUpdated = false; //to decide whether to print success message
					// Client management subMenu
					switch (subMenuOption) {
						case 1: // Add a client
							System.out.println("Enter the following information in order to add a new client.");
							System.out.print("Client's first name: ");
							firstName = keyboard.next();
							System.out.print("Client's last name: ");
							lastName = keyboard.next();
							System.out.print("Clients's email address: ");
							emailAddress = keyboard.next();
							try {
								Client client = new Client(firstName, lastName, emailAddress);
								service.addClient(client);
								numClients++;
								System.out.println("\nClient added successfully!\n");
							} catch(InvalidClientDataException e) {
								System.err.println("\n" + e.getMessage() + " Failed to create client.\n");
							}
							break;
						case 2: // Edit a client
							if (numClients == 0) {
								System.out.println("There are no saved clients.\n");
								break;
							}
							System.out.println("\nHere is the list of all current clients:");
							// print all IDs in names array
							printArray(clients, numClients);
							System.out.print("Enter the ID of the client you wish to edit: ");
							ID = keyboard.next();
							try {
								index = findObjectByID(clients, ID);
							}catch(EntityNotFoundException e) {
								System.err.println(e.getMessage() + "\n");
								break; //client not found - exit current menu option
							}
														
							// print menu to edit client and prompt user to pick an attribute to edit.
							// Validate user input
							subMenuOption = validateMenuOption(clientEditMenu, CLIENT_EDIT_MENU_MAX, !ZERO_ACCEPTED);
							switch (subMenuOption) {
								case 1:// edit first name
									System.out.print("Enter new first name: ");
									firstName = keyboard.next();
									try{
										clients[index].setFirstName(firstName);
										clientUpdated = true;
									}catch(InvalidClientDataException e) {
										System.err.println(e.getMessage() + " Failed to edit client first name\n.");
									}
									break;
								case 2: // edit last name
									System.out.print("Enter new last name: ");
									lastName = keyboard.next();
									try {
										clients[index].setLastName(lastName);
										clientUpdated = true;
									}catch(InvalidClientDataException e) {
										System.err.println(e.getMessage() + " Failed to edit client last name.\n");
									}
									break;
								case 3: // edit email address
									System.out.print("Enter new email address: ");
									emailAddress = keyboard.next();
									try {
										clients[index].setEmailAddress(emailAddress);
										clientUpdated = true;
									} catch(InvalidClientDataException e) {
										System.err.println("Failed to edit client email address.\n");
									}
									break;
								case 4:
									System.out.println("Returning to main menu...\n");
									// no need for default case since the validateMenuOption function ensures a valid input
									break;
							}
							// Success message - after all cases have been executed
							if (clientUpdated == true) {
								System.out.println("\nClient updated successfully!\n"); //Success message
							}
							break;
						case 3: // Delete a client
							if (numClients == 0) {
								System.out.println("There are no saved clients.\n");
								break;
							}
							System.out.println("\nHere is the list of current clients:");
							printArray(clients, numClients); // print all clients in clients array
							System.out.print("Enter the ID of the client you wish to delete: ");
							ID = keyboard.next();
							try {
								index = findObjectByID(clients, ID); //returns the index of the client that has the ID in the clients array
								// delete trips associated with that client to free up array
								for (int i = 0; i < numTrips; i++) {
									if (trips[i].getClient().equals(clients[index])) {
										deleteIndexInArray(trips, numTrips, i);
										numTrips--;
									}
								}
								// remove client from array
								deleteIndexInArray(clients, numClients, index);
								numClients--;
								System.out.println("\nClient deleted successfully!\n");
							}catch(EntityNotFoundException e) {
								System.err.println(e.getMessage());
							}
							break;								
						case 4: // List all clients
							if (numClients == 0) {
								System.out.println("There are no saved clients.\n");
							}
							else {
								printArray(clients, numClients);
							}								
							break;
						case 5: // Back to Main Menu
							System.out.println("Returning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			} else if (option == 2) {// TRIP_MANAGEMENT
				// trip variables
				String destination;
				int duration;
				double basePrice;
				int indexOfClient, indexOfAccommodation, indexOfTransport; // index of client in clients array associated with trip 
																			// (same for accommodation and transport)
				Client clientOfTrip; // reference to actual client taken from array. for visual simplicity
				int amountSpent; //for updating the client amountSpent attribute
				Accommodation accommodationOfTrip; // reference to accommodation from array or from trip (visual
													// simplicity)
				int accommodationChoice; // int selection from accommodation type menu
				String accommodationType; // String from accommodationsType array
				Accommodation[] accommodationArray; // one of the accommodation arrays to work with
				Transportation transportOfTrip; // reference to transportation from array or from trip (visual
												// simplicity)
				int transportChoice; // int selection from transportType menu
				String transportType; // String selection from transportsType array
				Transportation[] transportArray; // one of the transport arrays to work with
				int indexOfTrip; // index in trips array
				while (subMenuOption != TRIP_MANAGEMENT_MENU_MAX) { // TRIP MANAGEMENT MENU
					// print menu and validate input
					subMenuOption = validateMenuOption(tripManagementMenu, TRIP_MANAGEMENT_MENU_MAX,
							!ZERO_ACCEPTED);
					switch (subMenuOption) {
						case 1:// Create a trip
							System.out.println("Enter the following information in order to create a new trip.");
							System.out.print("Destination of trip: ");
							destination = keyboard.next();
							System.out.print("Duration of trip: ");
							duration = keyboard.nextInt();
							System.out.print("Base price of trip: ");
							basePrice = keyboard.nextDouble();
							if (numClients == 0) {
								System.out.println("There are no saved clients.\n");
								clientOfTrip = null;
							} else {
								System.out.println("Here is the list of current clients: ");
								printArray(clients, numClients);
								System.out.print("Enter the ID of the client associated with this trip: ");
								ID = keyboard.next();
								try {
									indexOfClient = findObjectByID(clients, ID);
									clientOfTrip = clients[indexOfClient];
								}catch(EntityNotFoundException e) {
									System.err.println(e.getMessage() + "\n");
									break; //client not found - trip cannot be created - exit menu option
								}
							}
							System.out.println("\nWhat type of accommodation you would like to add?");
							accommodationChoice = validateMenuOption(accommodationTypeMenu,
									ACCOMMODATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							if (accommodationChoice == ACCOMMODATION_TYPE_MENU_MAX)
								break; // last option is return to main menu
							accommodationType = accommodationTypes[accommodationChoice];
							accommodationArray = accommodations[accommodationChoice];
							if (numSavedAccommodations[accommodationChoice] == 0) {
								System.out.println("There are no saved " + accommodationType + "s.");
								accommodationOfTrip = null;
							} else {
								System.out.println("Here is the list of offered " + accommodationType + "s: ");
								printArray(accommodationArray, numSavedAccommodations[accommodationChoice]);
								System.out.print("Enter the ID of the " + accommodationType
										+ " you wish to add to this trip: ");
								ID = keyboard.next();
								try {
									indexOfAccommodation = findObjectByID(accommodationArray, ID);
									accommodationOfTrip = accommodationArray[indexOfAccommodation];
								}catch(EntityNotFoundException e) {
									System.err.println(e.getMessage() + "\n");
									break; //accommodation not found - exit current menu option
								}
							}
							System.out.println("What type of transportation you would like to add?");
							transportChoice = validateMenuOption(transportationTypeMenu,
									TRANSPORTATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							if (transportChoice == TRANSPORTATION_TYPE_MENU_MAX)
								break; // last option is to return to main menu
							transportType = transportTypes[transportChoice];
							transportArray = transports[transportChoice];
							if (numSavedTransports[transportChoice] == 0) {
								System.out.println("There are no saved " + transportType + "s.");
								transportOfTrip = null;
							} else {
								System.out.println("Here is the list of offered transportations: ");
								System.out.print("Enter the ID of the " + transportType + " you wish to add to this trip: ");
								ID = keyboard.next();
								try {
									indexOfTransport = findObjectByID(transportArray, ID);
									transportOfTrip = transportArray[indexOfTransport];
									try {
										trips[numTrips] = new Trip(destination, duration, basePrice, clientOfTrip,
										accommodationOfTrip, transportOfTrip);
										numTrips++;
										System.out.println("\nTrip added successfully!\n");
									}catch(InvalidTripDataException e) {
										System.err.println(e.getMessage() + " Failed to create Trip.");
									}catch(InvalidAccommodationDataException e) {
										System.err.println(e.getMessage() + " Failed to create Trip.");
									}catch(InvalidTransportDataException e) {
										System.err.println(e.getMessage() + " Failed to create Trip.");
									}
								}catch(EntityNotFoundException e) {
									System.err.println(e.getMessage() + "\n");
								}
							}
							break;
						case 2:// Edit Trip
							boolean tripUpdated = false; // verification for success message
							if (numTrips == 0) {
								System.out.println("There are no saved trips.\n");
								break;
							}
							System.out.println("\nHere is the list of current trips:");
							printIDs(trips, numTrips);
							System.out.println("Enter the ID of the trip you wish to edit: ");
							ID = keyboard.next();
							try {
								indexOfTrip = findObjectByID(trips, ID);
							}catch(EntityNotFoundException e) {
								System.err.println(e.getMessage());
								break; //trip not found - exit menu option since unable to edit trip
							}
							Trip tripToEdit = trips[indexOfTrip];
							subMenuOption = validateMenuOption(tripEditMenu, TRIP_EDIT_MENU_MAX, !ZERO_ACCEPTED); // Print trip menu and prompt user for choice. Validate input
							tripUpdated = false;
							switch (subMenuOption) {
								case 1:// edit destination
									System.out.print("Enter new destination: ");
									destination = keyboard.next();
									tripToEdit.setDestination(destination);
									tripUpdated = true;
									break;
								case 2: // edit duration
									System.out.print("Enter new duration: ");
									duration = keyboard.nextInt();
									try {
										tripToEdit.setDuration(duration);
										tripUpdated = true;
									}catch(InvalidTripDataException e) {
										System.err.println(e.getMessage() + " Failed to edit trip duration.");
									}
									break;
								case 3: // edit base price
									System.out.print("Enter new base price: ");
									basePrice = keyboard.nextDouble();
									try {
										tripToEdit.setBasePrice(basePrice);
										tripUpdated = true;
									}catch(InvalidTripDataException e) {
										System.err.println(e.getMessage() + " Failed to edit trip base price.");
									}
									break;
								case 4: // edit client
									System.out.println("\nHere is the list of current clients: ");
									printArray(clients, numClients);
									System.out.print("Enter the ID of the client you wish to associate with this trip:");
									ID = keyboard.next();
									try {
										indexOfClient = findObjectByID(clients, ID);
										try {
											tripToEdit.setClient(clients[indexOfClient]);
											tripUpdated = true;
										}catch(InvalidTripDataException e) {
											System.err.println(e.getMessage() + " Failed to edit client.");
										}
									}catch(EntityNotFoundException e) {
										System.err.println(e.getMessage() + "\n");
									}
									break;
								case 5: // edit accommodation
									System.out.println("What kind of accommodation would you like to replace the current one with? ");
									accommodationChoice = validateMenuOption(accommodationTypeMenu,
											ACCOMMODATION_TYPE_MENU_MAX, ZERO_ACCEPTED);
									if (accommodationChoice == ACCOMMODATION_TYPE_MENU_MAX)
										break; // last option is return to main menu
									accommodationType = accommodationTypes[accommodationChoice];
									accommodationArray = accommodations[accommodationChoice];
									if (numSavedAccommodations[accommodationChoice] == 0) {
										System.out.println("There are no saved " + accommodationType + "s.\n");
										break;
									}
									System.out.println("\nHere is the list of current " + accommodationType + "s offered: ");
									printArray(accommodationArray, numSavedAccommodations[accommodationChoice]);
									System.out.print("Enter the ID of the " + accommodationType + " you wish to associate with this trip:");
									ID = keyboard.next();
									try {
										indexOfAccommodation = findObjectByID(accommodationArray, ID);
										try {
											tripToEdit.setAccommodation(accommodationArray[indexOfAccommodation]);
											tripUpdated = true;
										}catch(InvalidAccommodationDataException e) {
											System.err.println(e.getMessage() + "Failed to edit Accommodation.");
										}
									}catch(EntityNotFoundException e) {
										System.err.println(e.getMessage() + "\n");
									}
									break;
								case 6: // edit transportation
									System.out.println("What kind of transportation would you like to replace the current one with? ");
									transportChoice = validateMenuOption(transportationTypeMenu, TRANSPORTATION_TYPE_MENU_MAX, ZERO_ACCEPTED);
									if (transportChoice == TRANSPORTATION_TYPE_MENU_MAX)
										break; // last option is return to main menu
									transportType = transportTypes[transportChoice];
									transportArray = transports[transportChoice];
									if (numSavedTransports[transportChoice] == 0) {
										System.out.println("There are no saved " + transportType + "s. \n");
										break;
									}
									System.out.println(
											"\nHere is the list of current " + transportType + "s offered: ");
									printArray(transportArray, numSavedTransports[transportChoice]);
									System.out.print("Enter the ID of the " + transportType + " you wish to associate with this trip:");
									ID = keyboard.next();
									try {
										indexOfTransport = findObjectByID(transportArray, ID);
										try {
											tripToEdit.setTransportation(transportArray[indexOfTransport]);
											tripUpdated = true;
										}catch(InvalidTransportDataException e) {
											System.err.println(e.getMessage() + "Failed to edit Transportation.");
										}
									}catch(EntityNotFoundException e) {
										System.err.println(e.getMessage() + "\n");
									}
									break;
								case 7:
									System.out.println("Returning to main menu...\n");
									// no need for default case since the validateMenuOption function ensures a valid input
							}
							// Success message
							if (tripUpdated == true) {
								System.out.println("\nTrip updated successfully!\n");
								// because valid input subMenu option can only be values from 1 to 5. If 5 not
								// selected, trip was edited
							}

							break;
						case 3:// Cancel a trip
							if (numTrips == 0) {
								System.out.println("There are no saved trips.\n");
								break;
							}
							System.out.println("\nHere is the list of current trips:");
							// print all trips in trips array
							printArray(trips, numTrips);
							System.out.print("Enter the ID of the trip you wish to cancel: ");
							ID = keyboard.next();
							try {
								index = findObjectByID(trips, ID);
								deleteIndexInArray(trips, numTrips, index);
								numTrips--;
								System.out.println("\nTrip canceled successfully!\n");
							}catch(EntityNotFoundException e) {
								System.err.println(e.getMessage() + "\n");
							}
							break;
						case 4:// List all trips
							if (numTrips == 0) {
								System.out.println("There are no saved trips.\n");
							}
							printArray(trips, numTrips);
							break;
						case 5:// List all trips for a specific client
							if (numClients == 0) {
								System.out.println("There are no saved clients.\n");
								break;
							}
							System.out.println("Here is the list of clients: ");
							printArray(clients, numClients);
							System.out.print("Enter the ID of the client whose trips you wish to see: ");
							ID = keyboard.next();
							for (int i = 0; i < numTrips; i++) {
									if (trips[i].getClient().CLIENT_ID.equals(ID)) {
										System.out.println(trips[i]);
										System.out.println();
									}
							}
							break;
						case 6:
							System.out.println("Returning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			}
			// Transportation Management
			else if (option == 3) {
				while (subMenuOption != TRANSPORTATION_MANAGEMENT_MENU_MAX) {
					subMenuOption = validateMenuOption(transportationManagementMenu, TRANSPORTATION_MANAGEMENT_MENU_MAX, !ZERO_ACCEPTED);
					int transportChoice; // transportChoice menu option
					String transportType; // string of transport type chosen
					Transportation[] transportArray; // transport array to work with
					boolean transportCreated = false;
					// General transportation variables
					String companyName;
					String departureCity;
					String arrivalCity;
					// Flight variables
					double luggageAllowance;
					double ticketPrice;
					// Train variables
					String trainType;
					double baseFare;
					// Bus variables
					int numberOfStops;
					double busFare;
					// Transport management menu options
					switch (subMenuOption) {
						case 1:// Add a transportation option
							System.out.println("\nWhat is the type of transportation option you would like to add?");
							transportChoice = validateMenuOption(transportationTypeMenu, TRANSPORTATION_TYPE_MENU_MAX, ZERO_ACCEPTED);
							transportType = transportTypes[transportChoice];
							transportArray = transports[transportChoice];
							System.out.println("Enter the following information in order to add a " + transportType + " option.");
							System.out.print("Company name: ");
							discard = keyboard.nextLine();
							companyName = keyboard.nextLine();
							System.out.print("Departure city: ");
							departureCity = keyboard.nextLine();
							System.out.print("Arrival city: ");
							arrivalCity = keyboard.nextLine();
							// Initialize based on flight, train, or bus:
							// FLIGHT
							if (transportChoice == FLIGHTS_INDEX) {
								System.out.print("Luggage Allowance: ");
								luggageAllowance = keyboard.nextDouble();
								System.out.print("Ticket price: ");
								ticketPrice = keyboard.nextDouble();
								try{
									transportArray[numSavedTransports[transportChoice]] = new Flight(companyName, departureCity, arrivalCity, luggageAllowance, ticketPrice);
									transportCreated = true;
								} catch(InvalidTransportDataException e) {
									System.err.println(e.getMessage() + " Failed to create Flight.");
								}	
							}
							// TRAIN
							else if (transportChoice == TRAINS_INDEX) {
								System.out.print("Train type: ");
								trainType = keyboard.nextLine();
								System.out.print("Base fare: ");
								baseFare = keyboard.nextDouble();
								transportArray[numSavedTransports[transportChoice]] = new Train(companyName,
										departureCity, arrivalCity, baseFare, trainType);
								transportCreated = true;
							}
							// BUS
							else if (transportChoice == BUSES_INDEX) {
								System.out.print("Number of stops: ");
								numberOfStops = keyboard.nextInt();
								System.out.print("Bus fare: ");
								busFare = keyboard.nextDouble();
								try{
									transportArray[numSavedTransports[transportChoice]] = new Bus(companyName,
										departureCity, arrivalCity, busFare, numberOfStops);
									transportCreated = true;
								}catch(InvalidTransportDataException e) {
									System.err.println(e.getMessage() + " Failed to create Bus.");
								}
							}
							if(transportCreated == true) {
								numSavedTransports[transportChoice]++;
								System.out.println("\nTransport option added successfully!\n");
							}
							break;
						case 2:// Remove a transportation option
							System.out
									.println("What is the type of transportation option you would like to remove?");
							transportChoice = validateMenuOption(transportationTypeMenu,
									TRANSPORTATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							transportType = transportTypes[transportChoice];
							transportArray = transports[transportChoice];
							if (numSavedTransports[transportChoice] == 0) {
								System.out.println("There are no saved " + transportType + "s.\n");
								break;
							}
							System.out.println("Here is the list of current " + transportType + "s:");
							printArray(transportArray, numSavedTransports[transportChoice]);
							System.out.println("Enter the ID of the " + transportType + " you would like to remove: ");
							ID = keyboard.next();
							try {
								index = findObjectByID(transportArray, ID);
								deleteIndexInArray(transportArray, numSavedTransports[transportChoice], index);
								numSavedTransports[transportChoice]--;
							}catch(EntityNotFoundException e) {
								System.err.println(e.getMessage() + "\n");
							}
							break;
						case 3:// List transportation options by type (Flight, Train, Bus)
							System.out.println("Flights:");
							if (numSavedTransports[FLIGHTS_INDEX] == 0) {
								System.out.println("None.");
							}
							printArray(transports[FLIGHTS_INDEX], numSavedTransports[FLIGHTS_INDEX]);
							System.out.println("\nTrains:");
							if (numSavedTransports[TRAINS_INDEX] == 0) {
								System.out.println("None.");
							}
							printArray(transports[TRAINS_INDEX], numSavedTransports[TRAINS_INDEX]);
							System.out.println("\nBuses:");
							if (numSavedTransports[BUSES_INDEX] == 0) {
								System.out.println("None.");
							}
							printArray(transports[BUSES_INDEX], numSavedTransports[BUSES_INDEX]);
							System.out.println();
							break;
						case 4:// Back to Main Menu
							System.out.println("Returning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			}
			// Accommodation Management
			else if (option == 4) {
				while (subMenuOption != ACCOMMODATION_MANAGEMENT_MENU_MAX) {
					subMenuOption = validateMenuOption(accommodationManagementMenu, ACCOMMODATION_MANAGEMENT_MENU_MAX, !ZERO_ACCEPTED);
					int accommodationChoice; // int from accommodations type menu. same as numSavedHotelsIndex or
												// numSavedHostelsIndex
					String accommodationType;
					Accommodation[] accommodationArray; // accommodation array that will be used (Hotels or hostels)
					// General accommodation variables
					String name, location;
					double pricePerNight;
					double starRating; // Hotel variable
					int numOfBeds; // Hostel variable
					boolean accommodationCreated = false; //to decide whether or not to print success message
					switch (subMenuOption) {
						case 1:// Add an accommodation
							System.out.println("What is the type of accommodation option you would like to add?");
							accommodationChoice = validateMenuOption(accommodationTypeMenu,
									ACCOMMODATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							if(accommodationChoice == 2) {
								break; //option 2 is return to previous menu - so exit current menu option
							}
							accommodationType = accommodationTypes[accommodationChoice];
							accommodationArray = accommodations[accommodationChoice];
							System.out.println(
									"Enter the following information in order to add a " + accommodationType
											+ " option.");
							System.out.print("Name of establishment: ");
							discard = keyboard.nextLine();
							name = keyboard.nextLine();
							System.out.print("Location: ");
							location = keyboard.nextLine();
							System.out.print("Price per night: ");
							pricePerNight = keyboard.nextDouble();
							// Specific accommodation type variables and initialization
							if (accommodationChoice == HOTELS_INDEX) { // Hotel
								System.out.print("Star rating: ");
								starRating = keyboard.nextDouble();
								try {
									hotels[numSavedAccommodations[HOTELS_INDEX]] = new Hotel(name, location,
										pricePerNight, starRating);
									accommodationCreated = true;
								}catch(InvalidAccommodationDataException e) {
									System.err.println(e.getMessage() + " Failed to add Hotel.");
								}
								
							} else if (accommodationChoice == HOSTELS_INDEX) { // Hostel
								System.out.print("Number of shared beds per room: ");
								numOfBeds = keyboard.nextInt();
								try {
									hostels[numSavedAccommodations[HOSTELS_INDEX]] = new Hostel(name, location,
										pricePerNight, numOfBeds);
									accommodationCreated = true;
								}catch(InvalidAccommodationDataException e) {
									System.err.println("Failed to create Hostel.");
								}
							}
							if(accommodationCreated) {
								numSavedAccommodations[accommodationChoice]++;
								System.out.println("Accommodation added successfully!\n");
							}
							break;
						case 2:// Remove an accommodation
							System.out.println("What type of accommodation option you would like to remove?");
							accommodationChoice = validateMenuOption(accommodationTypeMenu,
									ACCOMMODATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							accommodationType = accommodationTypes[accommodationChoice];
							accommodationArray = accommodations[accommodationChoice];
							if (numSavedAccommodations[accommodationChoice] == 0) {
								System.out.println("There are no saved " + accommodationType + "s.\n");
								break;
							}
							System.out.println("Here is the list of current " + accommodationType + "s offered");
							printArray(accommodationArray, numSavedAccommodations[accommodationChoice]);
							System.out.print("Enter the ID of the " + accommodationType + " you would like to remove: ");
							ID = keyboard.next();
							try {
								index = findObjectByID(accommodationArray, ID);
								deleteIndexInArray(accommodationArray, numSavedAccommodations[accommodationChoice], index);
								numSavedAccommodations[accommodationChoice]--;
								System.out.println(accommodationType + " deleted successfully!");
							}catch(EntityNotFoundException e) {
								System.err.println(e.getMessage() + "\n");
							}
							break;
						case 3:// List accommodations by type
							System.out.println("Hotels: ");
							if (numSavedAccommodations[HOTELS_INDEX] == 0) {
								System.out.println("None.");
							} else
								printArray(hotels, numSavedAccommodations[HOTELS_INDEX]);
							System.out.println("Hostels: ");
							if (numSavedAccommodations[HOSTELS_INDEX] == 0) {
								System.out.println("None.");
							} else
								printArray(hostels, numSavedAccommodations[HOSTELS_INDEX]);
							break;
						case 4:// Back to main menu
							System.out.println("Returning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			}
			// Additional Operations
			else if (option == 5) {
				while (subMenuOption != ADDITIONAL_OPERATIONS_MENU_MAX) {
					subMenuOption = validateMenuOption(additionalOperationsMenu, ADDITIONAL_OPERATIONS_MENU_MAX,
							!ZERO_ACCEPTED);
					switch (subMenuOption) {
						case 1:// Display the most expensive trip
							if (numTrips == 0) {
								System.out.println("There are no saved trips.\n");
								break;
							}
							try {
								findMostExpensiveTrip(trips, numTrips);
							}catch(InvalidAccommodationDataException e) {
								System.err.println("Failed to calculate most expensive trip. One of the trips has an accommodation with an invalid number of days.");
							}
							
							break;
						case 2:// Calculate and display the total cost of a trip
							if (numTrips == 0) {
								System.out.println("There are no saved trips.\n");
								break;
							}
							System.out.println("Here is the list of trips");
							printArray(trips, numTrips);
							index = validateMenuOption(
									"\nEnter the index of the trip you would like to calculate the cost of:",
									numTrips - 1, ZERO_ACCEPTED);
							Trip trip = trips[index];
							try {
								double cost = trip.calculateTotalCost();
								String display = String.format("\nThe trip you selected costs $%.2f.", cost);
								System.out.println(display);
							}catch(InvalidAccommodationDataException e) {
								System.err.println(e.getMessage() + "Failed to calculate trip cost.");
							}
							break;
						case 3:// Create a deep copy of the transportation array
							try {
								Transportation[] flightsCopy = copyTransportationArray(flights);
								Transportation[] trainsCopy = copyTransportationArray(trains);
								Transportation[] busesCopy = copyTransportationArray(buses);
								Transportation[][] copytransports = { flightsCopy, trainsCopy, busesCopy };
								System.out.println("\nDeep copy created successfully!\n");
							} catch(InvalidTransportDataException e) {
								System.err.println(e.getMessage() + " Failed to copy array.");
							}
							break;
						case 4:// Create a deep copy of the accommodation array
							try{
								Accommodation[] hotelsCopy = copyAccommodationArray(hotels);
								Accommodation[] hostelsCopy = copyAccommodationArray(hostels);
								Accommodation[][] accommodationsCopy = { hotelsCopy, hostelsCopy };
								System.out.println("\nDeep copy created successfully!\n");
							}catch(InvalidAccommodationDataException e) {
								System.err.println(e.getMessage() + " Failed to copy array.");
							}
							break;
						case 5:// Back to main menu
							System.out.println("\nReturning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			}
			//List All Data Summary (Prints all trip data)
			else if(option == 6) {
				int count = 0;
				for(int i = 0; i<trips.length; i++) {
					Trip trip = trips[i];
					if(trip != null) {
						Client client = trip.getClient();
						Accommodation accommodation = trip.getAccommodation();
						Transportation transport = trip.getTransportation();
						System.out.println("Trip " + (i+1));
						System.out.println("-------------------------");
						System.out.println(trip);
						System.out.println("Client: " + client);
						System.out.println("Accommodation: " + accommodation);
						System.out.println("Transportation: " + transport);
						System.out.println("\n\n");
						count++;
					}
				}
				if(count == 0) { //no trips were printed
					System.out.println("\nThere are no saved trips.\n");
				}
			}
			//Load All Data (output/data/*.csv)
			else if(option == 7) {
				//since all the arrays have been passed by reference, they should be automatically updated when this method is called
				service.loadAllData("output/data/");
				//get counts for flights, trains, and buses from service
				numClients = service.getClientCount();
				numTrips = service.getTripCount();
				numSavedTransports[FLIGHTS_INDEX] = service.getFlightCount();
				numSavedTransports[TRAINS_INDEX] = service.getTrainCount();
				numSavedTransports[BUSES_INDEX] = service.getBusCount();
				numSavedAccommodations[HOTELS_INDEX] = service.getHotelCount();
				numSavedAccommodations[HOSTELS_INDEX] = service.getHostelCount();
				dataLoaded = true;

			}
			//Save All Data (output/data/*.csv)
			else if(option == 8) {
				int response;
				boolean saveData = true;
				if(!dataLoaded) {
					saveData = false; //dont save data yet. decide
					System.out.println("Data has not been loaded. All previously saved information that has not been loaded will be lost.");
					System.out.println("Are you sure you would like to save information at this stage?");
					System.out.print("1. Yes\n2. No\nChoice: ");
					response = keyboard.nextInt();
					if(response == 1) {
						saveData = true;
					}
					else if(response == 2) {
						System.out.println("\nData will not be saved.");
						System.out.println("Returning to main menu...\n");
					}
					else {
						System.out.println("\nInvalid selection. Returning to main menu...\n");
					}
				}
				if(saveData) {
					service.saveAllData("output/data/");
				}
				
			}
			//Run predefined scenario
			else if(option == 9) {
				// 1. Create
				boolean creationSuccessful = false;
				Client client1 = null, client2 = null, client3 = null, client4 = null;
				Transportation flight1 = null, flight2 = null, flight3 = null;
				Transportation train1 = null, train2 = null, train3 = null;
				Transportation bus1 = null, bus2 = null, bus3 = null;
				Accommodation hotel1 = null, hotel2 = null, hotel3 = null;
				Accommodation hostel1 = null, hostel2 = null, hostel3 = null;
				Trip trip1 = null, trip2 = null, trip3 = null, trip4 = null;
				
				try {
					//Client creation
					client1 = new Client("Jane", "Doe", "janedoe@gmail.com");
					client2 = new Client("John", "Doe", "johndoe@gmail.com");
					client3 = new Client("Bob", "Ross", "bobross@gmail.com");
					client4 = new Client("Bob", "Ross", "bobross@gmail.com");

					//Transportation creation
					flight1 = new Flight("Emirates Airlines", "Toronto", "Dubai", 25, 250);
					flight2 = new Flight("British Airways", "London", "Santiago", 23, 300);
					flight3 = new Flight("British Airways", "London", "Santiago",23, 300);

					train1 = new Train("VIA Rail", "Montreal", "Toronto", 30, "Intercity");
					train2 = new Train("VIA Rail", "Montreal", "Halifax", 40, "Transcontinental");
					train3 = new Train("VIA Rail", "Montreal", "Halifax", 40, "Transcontinental");

					bus1 = new Bus("Orleans Express", "Montreal", "Ottawa", 4, 10);
					bus2 = new Bus("FlixBus", "Vancouver", "Calgary", 3, 15);
					bus3 = new Bus("FlixBus", "Vancouver", "Calgary", 3, 15);

					//Accommodation creation
					hotel1 = new Hotel("Holiday Inn", "Lisbon", 108, 3);
					hotel2 = new Hotel("Sheraton", "Istanbul City Center", 184, 5);
					hotel3 = new Hotel("Sheraton", "Istanbul City Center", 184, 5);

					hostel1 = new Hostel("Once Again Hostel", "Bangkok", 47, 2);
					hostel2 = new Hostel("The Bee Hostel", "Amsterdam City Centre", 80, 1);
					hostel3 = new Hostel("The Bee Hostel", "Amsterdam City Centre", 80, 1);

					//Trip creation
					trip1 = new Trip("Spain", 5, 150, client1, hotel1, flight1);
					trip2 = new Trip("Egypt", 3, 200, client2, hotel2, flight2);
					trip3 = new Trip("Phillipines", 3, 430, client3, hotel3, flight3);
					trip4 = new Trip("Phillipines", 3, 430, client3, hotel3, flight3);

					creationSuccessful = true;
				}catch(InvalidClientDataException e) {
					System.err.println(e.getMessage() + " Client not created.");
				}catch(InvalidTransportDataException e) {
					System.err.println(e.getMessage() + " Transport not created.");
				}catch(InvalidAccommodationDataException e) {
					System.err.println(e.getMessage() + " Accommodation not created.");
				}catch(InvalidTripDataException e) {
					System.err.println(e.getMessage() + " Trip not created.");
				}finally {
					System.out.println("Creation successful: " + creationSuccessful);
				}

				if(creationSuccessful) { //all objects have been created successfully
					// 4. Create Arrays for...
					Client[] clientsHardcoded = {client1, client2, client3, client4 };
					Trip[] tripsHardcoded = { trip1, trip2, trip3, trip4 };
					Transportation[] transportationsHardcoded = { flight1, flight2, flight3, train1, train2, train3, bus1, bus2,
							bus3 };
					Accommodation[] accommodationsHardcoded = { hotel1, hotel2, hotel3, hostel1, hostel2, hostel3 };

					// 2. Display all objects using toString()
					System.out.println("\n2. Display all objects using toString():");

					System.out.println("\nClients:");
					for (int i = 0; i < clientsHardcoded.length; i++) {
						System.out.println(clientsHardcoded[i]);
						System.out.println();
					}

					System.out.println("\nTrips:");
					for (int i = 0; i < tripsHardcoded.length; i++) {
						System.out.println(tripsHardcoded[i]);
						System.out.println();
					}

					System.out.println("\nTransportations:");
					for (int i = 0; i < transportationsHardcoded.length; i++) {
						System.out.println(transportationsHardcoded[i]);
						System.out.println();
					}

					System.out.println("\nAccommodations:");
					for (int i = 0; i < accommodationsHardcoded.length; i++) {
						System.out.println(accommodationsHardcoded[i]);
						System.out.println();
					}

					// 3. Display the equals method
					System.out.println("\n\nDisplay the equals methods");
					System.out.println("Objects from different classes: (should return false)");
					System.out.println("train1 and bus1: " + train1.equals(bus1));
					System.out.println("train1 and hotel1: " + train1.equals(hotel1));
					System.out.println("train1 and client1: " + train1.equals(client1));

					System.out.println("\nObjects of the same class with different attributes: (should return false)");
					System.out.println("Clients: " + client1.equals(client2));
					System.out.println("Trips: " + trip1.equals(trip2));
					System.out.println("Flights: " + flight1.equals(flight2));
					System.out.println("Trains: " + train1.equals(train2));
					System.out.println("Buses: " + bus1.equals(bus2));
					System.out.println("Hotels: " + hotel1.equals(hotel2));
					System.out.println("Hostel: " + hostel1.equals(hostel2));

					System.out.println("\nObjects of the same class with same attributes: (should return true)");
					System.out.println("Clients: " + client3.equals(client4));
					System.out.println("Trips: " + trip3.equals(trip4));
					System.out.println("Flights: " + flight2.equals(flight3));
					System.out.println("Trains: " + train2.equals(train3));
					System.out.println("Buses: " + bus2.equals(bus3));
					System.out.println("Hotels: " + hotel2.equals(hotel3));
					System.out.println("Hostels: " + hostel2.equals(hostel3));

					// Total cost of multiple trips:
					try {
						//may throw InvalidAccommodationException if numberOfDays is too low
						System.out.println("\nTotal cost of all trips ");
						System.out.println("Trip 1: " + trip1.calculateTotalCost());
						System.out.println("Trip 2: " + trip2.calculateTotalCost());
						System.out.println("Trip 3: " + trip3.calculateTotalCost());
						System.out.println("Trip 4: " + trip4.calculateTotalCost());
					}catch(InvalidAccommodationDataException e) {
						System.err.println(e.getMessage());
					}
					

					// Method that displays the most expensive trip
					System.out.println();
					System.out.println();
					try {
						findMostExpensiveTrip(tripsHardcoded, tripsHardcoded.length);
					}catch(InvalidAccommodationDataException e) {
						System.err.println("Failed to calculate most expensive trip. One of the trips has an accommodation with an invalid number of days.");
					}

					// Deep copy of transportation array

					try {
						Transportation[] transportsCopy = copyTransportationArray(transportationsHardcoded);
						transportsCopy[0] = null;
						transportsCopy[1] = null;
						System.out.println("\n\nOriginal transports array:");
						printArray(transportationsHardcoded, transportationsHardcoded.length);
						System.out.println("\n\nCopied transports array - modified:");
						printArray(transportsCopy, transportsCopy.length);
					}catch(InvalidTransportDataException e) {
						System.err.println(e.getMessage() + " Failed to copy array.");
					}
					
				}
			}
			//Generate Dashboard
			else if(option == 10) {

			}
			//Exit
			else if (option == 0) {
				System.out.println("Thank you for using the Smart Travel Agency Booking System!");
				System.out.println("Exiting...");
				System.exit(0);
			}

		}
	}

	public static int validateMenuOption(String menu, int maxMenuOption, boolean zeroAccepted) {
		int option;
		String discard;
		String errorMsg = "Please choose a number from 1 to " + maxMenuOption + ".\n";
		while (true) { // will keep looping until option is returned
			System.out.print(menu);
			if (keyboard.hasNextInt()) {
				option = keyboard.nextInt();
				if (option < 0) {
					System.out.println("Input must be a positive number. " + errorMsg);
				} else if (zeroAccepted && option >= 0 && option <= maxMenuOption) {
					return option;
				} else if (!zeroAccepted && option > 0 && option <= maxMenuOption) {
					return option;
				} else {
					System.out.println("Invalid option. " + errorMsg);
				}
			} else {
				discard = keyboard.next();
				System.out.println("Invalid option. " + errorMsg);
				;
			}
		}
	}

	public static void printArray(Object[] array, int afterLastIndex) {
		int i = 0;
		int length = afterLastIndex - 1; // location of last object in array. since partially filled arrays will be
											// passed
		for (i = 0; i <= length; i++) {
			System.out.println(i + ". " + array[i]);
			System.out.println();
		}
	}

	//printIDs method - multiple overloaded method to print IDs depending on type of object
	public static void printIDs(Client[] array, int afterLastIndex) {
		//prints the IDs of all client objects in the array
		int i = 0;
		int length = afterLastIndex-1; //location of the last object in the array - recall: partially filled arrays will be passed to this method
		for(i=0; i<=length; i++) {
			System.out.println(array[i].CLIENT_ID);
		}
	}

	public static void printIDs(Transportation[] array, int afterLastIndex) {
		//prints the IDs of all transportation objects in the array
		int i = 0;
		int length = afterLastIndex-1; //location of the last object in the array - recall: partially filled arrays will be passed to this method
		for(i=0; i<=length; i++) {
			System.out.println(array[i].TRANSPORT_ID);
		}	
	}

	public static void printIDs(Accommodation[] array, int afterLastIndex) {
		//prints the IDs of all accommodation objects in the array
		int i = 0;
		int length = afterLastIndex-1; //location of the last object in the array - recall: partially filled arrays will be passed to this method
		for(i=0; i<=length; i++) {
			System.out.println(array[i].ACCOMMODATION_ID);
		}	
	}

	public static void printIDs(Trip[] array, int afterLastIndex) {
		//prints the IDs of all trip objects in the array
		int i = 0;
		int length = afterLastIndex-1; //location of the last object in the array - recall: partially filled arrays will be passed to this method
		for(i=0; i<=length; i++) {
			System.out.println(array[i].TRIP_ID);
		}	
	}

	//find findObjectByID methods (overloaded - one for client, trip, accommodation, transportation) 
	public static int findObjectByID(Client[] array, String ID) throws EntityNotFoundException{
		int index = -1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				break;
			}
			if (array[i].CLIENT_ID.equals(ID)) {
				return i;
			}
		}
		if(index == -1) {
			throw new EntityNotFoundException("Client not found.");
		}
		return index;
	}

	public static int findObjectByID(Trip[] array, String ID) throws EntityNotFoundException {
		int index = -1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				break;
			}
			if (array[i].TRIP_ID.equals(ID)) {
				return i;
			}
		}
		if(index == -1) {
			throw new EntityNotFoundException("Trip not found.");
		}
		return index;
	}

	public static int findObjectByID(Transportation[] array, String ID) throws EntityNotFoundException {
		int index = -1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				break;
			}
			if (array[i].TRANSPORT_ID.equals(ID)) {
				return i;
			}
		}
		if(index == -1) {
			throw new EntityNotFoundException("Transport not found.");
		}
		return index;
	}

	public static int findObjectByID(Accommodation[] array, String ID) throws EntityNotFoundException {
		int index = -1;
		for (int i = 0; i < array.length; i++) {
			if(array[i] == null) {
				break;
			}
			if (array[i].ACCOMMODATION_ID.equals(ID)) {
				return i;
			}
		}
		if(index == -1) {
			throw new EntityNotFoundException("Accommodation not found.");
		}
		return index;
	}

	public static void deleteIndexInArray(Object[] array, int afterLastIndex, int index) {
		// afterLastIndex is the variable that keeps track of the number of items in the
		// array
		int i;
		int lastIndex = afterLastIndex - 1;
		for (i = index; i < lastIndex; i++) {
			array[i] = array[i + 1];
		}
		array[i] = null; // the object at this position has been moved to the previous position. removing
							// repetition
	}

	public static int indexOfTripOfClient(Trip[] trips, int numSavedTrips, Client client) {
		// check the trips array to find the first trip that is associated to the given
		// client
		int index = -1; // if client not found, return -1
		for (int i = 0; i < numSavedTrips; i++) {
			if (trips[i].getClient().equals(client)) {
				index = i;
				break;
			}
		}
		return index;
	}


	public static void findMostExpensiveTrip(Trip[] trips, int numSavedTrips) throws InvalidAccommodationDataException {
		Trip mostExpensiveTrip = trips[0];
		double maxCost = mostExpensiveTrip.calculateTotalCost(); // if this part is reached, then there is at least one
																	// trip in the trips array (because of where it is
																	// called in the main method)
		Trip currentTrip;
		double currentCost;
		for (int i = 0; i < numSavedTrips; i++) {
			currentTrip = trips[i];
			currentCost = currentTrip.calculateTotalCost();
			if (maxCost < currentCost) {
				maxCost = currentCost;
				mostExpensiveTrip = currentTrip;
			}
		}
		System.out.println("The most expensive trip is:\n" + mostExpensiveTrip);
		String string = String.format("%nThe total cost of this trip is $%.2f.", maxCost);
		System.out.println(string);
	}

	public static Transportation[] copyTransportationArray(Transportation[] original) throws InvalidTransportDataException {
		Transportation[] copy = new Transportation[original.length];
		for (int i = 0; i < copy.length; i++) {
			if (original[i] == null) {
				copy[i] = null;
			} else if (original[i].getClass() == (new Flight()).getClass()) {
				copy[i] = new Flight((Flight) original[i]);
			} else if (original[i].getClass() == (new Train()).getClass()) {
				copy[i] = new Train((Train) original[i]);
			} else if (original[i].getClass() == (new Bus()).getClass()) {
				copy[i] = new Bus((Bus) original[i]);
			}
		}
		return copy;
	}

	public static Accommodation[] copyAccommodationArray(Accommodation[] original) throws InvalidAccommodationDataException {
		Accommodation[] copy = new Accommodation[original.length];
		for (int i = 0; i < copy.length; i++) {
			if (original[i] == null) {
				copy[i] = null;
			} else if (original[i].getClass() == (new Hotel()).getClass()) {
				copy[i] = new Hotel((Hotel) original[i]);
			} else if (original[i].getClass() == (new Hostel()).getClass()) {
				copy[i] = new Hostel((Hostel) original[i]);
			}
		}
		return copy;
	}

}
