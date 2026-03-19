//-----------------------------------------------------------------------------
//Assignment 1
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------

package driver_A1COMP249;

import clientPackage.*;
import travelPackage.*;
import exceptions.*;

import java.util.Scanner;


public class Driver_A1_COMP249 {

	static Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {

		String welcomeMessage = """
				Assignment by Wania Faraz and Zahira Atmani.

				Welcome to the SmartTravel booking management system.
				""";

		String initialPrompt = """
				What would you like to do today?
				1. Run through a predefined testing scenario
				2. Create and manage your own clients through a menu driven interface

				Enter your selection here:""" + " ";

		System.out.println(welcomeMessage + "\n");

		String discard; // whenever its needed to catch discarded input from keyboard
		int initialChoice;
		final int INITIAL_PROMPT_MAX = 2;
		final boolean ZERO_ACCEPTED = true;
		initialChoice = validateMenuOption(initialPrompt, INITIAL_PROMPT_MAX, !ZERO_ACCEPTED); //validates menu option - initialChoice is always a valid menu option
		System.out.println();
		System.out.println();

		// Predefined hard-coded scenario
		if (initialChoice == 1) {

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
				flight1 = new Flight("The Emirates Group", "Toronto", "Dubai", "Emirates Airlines", 25, 250);
				flight2 = new Flight("International Airlines Group", "London", "Santiago", "British Airways",23, 300);
				flight3 = new Flight("International Airlines Group", "London", "Santiago", "British Airways",23, 300);

				train1 = new Train("VIA Rail", "Montreal", "Toronto", "Intercity", "Economy", 30);
				train2 = new Train("VIA Rail", "Montreal", "Halifax", "Transcontinental", "Sleeper", 40);
				train3 = new Train("VIA Rail", "Montreal", "Halifax", "Transcontinental", "Sleeper", 40);

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
		// Menu-driven interface
		else if(initialChoice == 2){

			// length of arrays
			final int MAX_CLIENTS = 20; // arbitrary number: for names and clients
			final int MAX_TRIPS = 40; // a client may have more than one trip
			final int MAX_TRANSPORTS = 20; // arbitrary
			final int MAX_ACCOMMODATIONS = 20; // arbitrary

			// arrays
			String[] names = new String[MAX_CLIENTS]; // to easily find the index of each client (no need for getName()
														// multiple times)
			Client[] clients = new Client[MAX_TRIPS]; // clients in same order as they are in names array
			Trip[] trips = new Trip[MAX_CLIENTS]; // trips of all clients
			Transportation[] flights = new Flight[MAX_TRANSPORTS];
			Transportation[] trains = new Train[MAX_TRANSPORTS];
			Transportation[] buses = new Bus[MAX_TRANSPORTS];
			Transportation[][] transports = { flights, trains, buses }; // all transports in one array but separated
			Accommodation[] hotels = new Hotel[MAX_ACCOMMODATIONS];
			Accommodation[] hostels = new Hostel[MAX_ACCOMMODATIONS];
			Accommodation[][] accommodations = { hotels, hostels }; // all accommodations in one array, but separated

			// keep track of the number of items in each array
			int numSavedClients = 0; // for clients and names
			int numSavedTrips = 0; // number of saved trips in the trips array
			int[] numSavedTransports = new int[3];
			int numSavedFlightsIndex = 0; // index of numSavedTransports where flights are stored
			int numSavedTrainsIndex = 1; // index of numSavedTransports where trains are stored
			int numSavedBusesIndex = 2; // index of numSavedTransports where flights are stored
			int[] numSavedAccommodations = new int[2];
			int numSavedHotelsIndex = 0; // index of numSavedAccommodations where hotels are stored
			int numSavedHostelsIndex = 1; // index of numSavedAccommodations where hotels are stored

			String mainMenu = """
					1. Client Management
					2. Trip Management
					3. Transportation Management
					4. Accommodation Management
					5. Additional Operations
					6. Exit

					Please select an option from the above menu:""" + " ";
			final int MAIN_MENU_MAX = 6;

			String clientManagementMenu = """
					Client Management Menu
					\t1. Add a client
					\t2. Edit a client
					\t3. Delete a client
					\t4. List all clients
					\t5. Back to Main Menu
					Please select an option from the above menu: """;
			final int CLIENT_MANAGEMENT_MENU_MAX = 5;

			String clientEditMenu = """
					Which one would you like to edit?
					\t1. Client's first name
					\t2. Client's last name
					\t3. Client's email address
					\t4. Back to previous menu
					Please enter your choice here: """;
			final int CLIENT_EDIT_MENU_MAX = 4;

			String tripManagementMenu = """
					Trip Management Menu
					\t1. Create a trip
					\t2. Edit Trip Information
					\t3. Cancel a trip
					\t4. List all trips
					\t5. List all trips for a specific client
					\t6. Back to Main Menu

					Please select an option from the above menu: """;
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
			int index = 0; // for whenever the user is prompted for an index

			while (runMainMenu) {
				option = validateMenuOption(mainMenu, MAIN_MENU_MAX, !ZERO_ACCEPTED);
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
									clients[numSavedClients] = new Client(firstName, lastName, emailAddress);
									names[numSavedClients] = firstName + " " + lastName;
									numSavedClients++;
									System.out.println("\nClient added successfully!\n");
								} catch(InvalidClientDataException e) {
									System.err.println(e.getMessage() + " Failed to create client");
								}
								break;
							case 2: // Edit a client
								if (numSavedClients == 0) {
									System.out.println("There are no saved clients.\n");
									break;
								}
								System.out.println("\nHere is the list of current clients:");
								// print all names in names array
								printArray(clients, numSavedClients);
								System.out.print("Enter the index of the client you wish to edit: ");
								index = keyboard.nextInt();
								if (index >= numSavedClients) {
									// no client at that index. empty index in array. return to client management
									// menu
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
								}
								// print menu to edit client and prompt user to pick an attribute to edit.
								// Validate user input
								subMenuOption = validateMenuOption(clientEditMenu, CLIENT_EDIT_MENU_MAX,
										!ZERO_ACCEPTED);
								switch (subMenuOption) {
									case 1:// edit first name
										System.out.print("Enter new first name: ");
										firstName = keyboard.next();
										try{
											clients[index].setFirstName(firstName);
										}catch(InvalidClientDataException e) {
											System.err.println(e.getMessage() + " Failed to edit client first name.");
										}
										break;
									case 2: // edit last name
										System.out.print("Enter new last name: ");
										lastName = keyboard.next();
										try {
											clients[index].setLastName(lastName);
										}catch(InvalidClientDataException e) {
											System.err.println(e.getMessage() + " Failed to edit client last name.");
										}
										break;
									case 3: // edit email address
										System.out.print("Enter new email address: ");
										emailAddress = keyboard.next();
										try {
											clients[index].setEmailAddress(emailAddress);
										} catch(InvalidClientDataException e) {
											System.err.println("Failed to edit client email address.");
										}
										break;
									case 4:
										System.out.println("Returning to main menu...\n");
										// no need for default case since the validateMenuOption function ensures a
										// valid input
										break;
								}
								// Success message
								if (subMenuOption != 4) {
									System.out.println("\nClient updated successfully!\n");
									// because valid input subMenu option can only be values from 1 to 4. If 4 not
									// selected, client was edited
								}
								break;
							case 3: // Delete a client
								if (numSavedClients == 0) {
									System.out.println("There are no saved clients.\n");
									break;
								}
								System.out.println("\nHere is the list of current clients:");
								printArray(names, numSavedClients); // print all names in names array
								System.out.print("Enter the index of the client you wish to delete: ");
								index = keyboard.nextInt();
								if (index >= numSavedClients) {
									// no client at that index. empty index in array
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
								}
								// delete trips associated with that client to free up array
								for (int i = 0; i < numSavedTrips; i++) {
									if (trips[i].getClient().equals(clients[index])) {
										deleteIndexInArray(trips, numSavedTrips, i);
										numSavedTrips--; // update count of number of trips in trips array
									}
								}
								// remove client from array
								deleteIndexInArray(clients, numSavedClients, index);
								numSavedClients--;
								System.out.println("\nClient deleted successfully!\n");
								break;
							case 4: // List all clients
								if (numSavedClients == 0) {
									System.out.println("There are no saved clients.\n");
								}
								printArray(clients, numSavedClients);
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
								if (numSavedClients == 0) {
									System.out.println("There are no saved clients.\n");
									clientOfTrip = null;
								} else {
									System.out.println("Here is the list of current clients: ");
									printArray(clients, numSavedClients);
									System.out.print("Enter the index of the client associated with this trip: ");
									indexOfClient = keyboard.nextInt();
									if (indexOfClient >= numSavedClients) {
										// no client at that index. empty index in array
										System.out.println("Invalid index. Returning to previous menu...\n");
										break;
									}
									clientOfTrip = clients[indexOfClient];
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
									System.out.print("Enter the index of the " + accommodationType
											+ " you wish to add to this trip: ");
									indexOfAccommodation = keyboard.nextInt();
									if (indexOfAccommodation >= numSavedAccommodations[accommodationChoice]) {
										// no accommodation at that index. empty index in array
										System.out.println("Invalid index. Returning to previous menu...\n");
										break;
									}
									accommodationOfTrip = accommodationArray[indexOfAccommodation];
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
									System.out.print(
											"Enter the index of the " + transportType
													+ " you wish to add to this trip: ");
									indexOfTransport = keyboard.nextInt();
									if (indexOfTransport >= numSavedTransports[transportChoice]) {
										// no transportation at that index. empty index in array
										System.out.println("Invalid index. Returning to previous menu...\n");
										break;
									}
									transportOfTrip = transportArray[indexOfTransport];
								}
								try {
									trips[numSavedTrips] = new Trip(destination, duration, basePrice, clientOfTrip,
									accommodationOfTrip, transportOfTrip);
									numSavedTrips++;
									System.out.println("\nTrip added successfully!\n");
								}catch(InvalidTripDataException e) {
									System.err.println(e.getMessage() + " Failed to create Trip.");
								}catch(InvalidAccommodationDataException e) {
									System.err.println(e.getMessage() + " Failed to create Trip.");
								}catch(InvalidTransportDataException e) {
									System.err.println(e.getMessage() + " Failed to create Trip.");
								}
								break;
							case 2:// Edit Trip
								boolean tripUpdated = false; // verification for success message
								if (numSavedTrips == 0) {
									System.out.println("There are no saved trips.\n");
									break;
								}
								System.out.println("\nHere is the list of current trips:");
								printArray(trips, numSavedTrips);
								System.out.println("Enter the index of the trip you wish to edit: ");
								indexOfTrip = keyboard.nextInt();
								if (indexOfTrip >= numSavedTrips) {
									// no trip at that index. empty index in array
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
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
									case 4: // edit client //GET CLIENT BY ID!!!!
										System.out.println("\nHere is the list of current clients: ");
										printArray(names, numSavedClients);
										System.out.print(
												"Enter the index of the client you wish to associate with this trip:");
										indexOfClient = keyboard.nextInt();
										if (indexOfClient >= numSavedClients) {
											// no client at that index. empty index in array
											System.out.println("Invalid index. Returning to previous menu...\n");
											break;
										}
										try {
											tripToEdit.setClient(clients[indexOfClient]);
											tripUpdated = true;
										}catch(InvalidTripDataException e) {
											System.err.println(e.getMessage() + " Failed to edit client.");
										}
										
										break;
									case 5: // edit accommodation
										System.out.println(
												"What kind of accommodation would you like to replace the current one with? ");
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
										System.out
												.println("\nHere is the list of current " + accommodationType
														+ "s offered: ");
										printArray(accommodationArray, numSavedAccommodations[accommodationChoice]);
										System.out.print("Enter the index of the " + accommodationType
												+ " you wish to associate with this trip:");
										indexOfAccommodation = keyboard.nextInt();
										if (indexOfAccommodation >= numSavedAccommodations[accommodationChoice]) {
											// no accommodation at that index. empty index in array
											System.out.println("Invalid index. Returning to previous menu...\n");
											break;
										}
										try {
											tripToEdit.setAccommodation(accommodationArray[indexOfAccommodation]);
											tripUpdated = true;
										}catch(InvalidAccommodationDataException e) {
											System.err.println(e.getMessage() + "Failed to edit Accommodation.");
										}
										
										break;
									case 6: // edit transportation
										System.out.println(
												"What kind of transportation would you like to replace the current one with? ");
										transportChoice = validateMenuOption(transportationTypeMenu,
												TRANSPORTATION_TYPE_MENU_MAX, ZERO_ACCEPTED);
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
										System.out.print("Enter the index of the " + transportType
												+ " you wish to associate with this trip:");
										indexOfTransport = keyboard.nextInt();
										if (indexOfTransport >= numSavedTransports[transportChoice]) {
											// no transport at that index. empty index in array
											System.out.println("Invalid index. Returning to previous menu...\n");
											break;
										}
										try {
											tripToEdit.setTransportation(transportArray[indexOfTransport]);
											tripUpdated = true;
										}catch(InvalidTransportDataException e) {
											System.err.println(e.getMessage() + "Failed to edit Transportation.");
										}
										break;
									case 7:
										System.out.println("Returning to main menu...\n");
										// no need for default case since the validateMenuOption function ensures a
										// valid input
								}

								// Success message
								if (tripUpdated == true) {
									System.out.println("\nTrip updated successfully!\n");
									// because valid input subMenu option can only be values from 1 to 5. If 5 not
									// selected, trip was edited
								}

								break;
							case 3:// Cancel a trip
								if (numSavedTrips == 0) {
									System.out.println("There are no saved trips.\n");
									break;
								}
								System.out.println("\nHere is the list of current trips:");
								// print all trips in trips array
								printArray(trips, numSavedTrips);
								System.out.print("Enter the index of the trip you wish to cancel: ");
								index = keyboard.nextInt();
								if (index >= numSavedTrips) {
									// no trip at that index. empty index in array
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
								}
								deleteIndexInArray(trips, numSavedTrips, index);
								numSavedTrips--;
								System.out.println("\nTrip canceled successfully!\n");
								break;
							case 4:// List all trips
								if (numSavedTrips == 0) {
									System.out.println("There are no saved trips.\n");
								}
								printArray(trips, numSavedTrips);
								break;
							case 5:// List all trips for a specific client
								if (numSavedClients == 0) {
									System.out.println("There are no saved clients.\n");
									break;
								}
								System.out.println("Here is the list of clients: ");
								printArray(clients, numSavedClients);
								System.out.print("Enter the index of the client whose trips you wish to see: ");
								indexOfClient = keyboard.nextInt();
								if (indexOfClient >= numSavedClients) {
									// no client at that index. empty index in array
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
								}
								Client client = clients[indexOfClient]; // for ease of reading
								for (int i = 0; i < numSavedTrips; i++) {
									if (trips[i].getClient().equals(client)) {
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
						subMenuOption = validateMenuOption(transportationManagementMenu,
								TRANSPORTATION_MANAGEMENT_MENU_MAX, !ZERO_ACCEPTED);
						int transportChoice; // transportChoice menu option
						String transportType; // string of transport type chosen
						Transportation[] transportArray; // transport array to work with
						boolean transportCreated = false;
						// General transportation variables
						String companyName;
						String departureCity;
						String arrivalCity;
						// Flight variables
						String airlineName;
						double luggageAllowance;
						double ticketPrice;
						// Train variables
						String trainType;
						String seatClass;
						double baseFare;
						// Bus variables
						int numberOfStops;
						double busFare;
						// Transport management menu options
						switch (subMenuOption) {
							case 1:// Add a transportation option
								System.out
										.println("\nWhat is the type of transportation option you would like to add?");
								transportChoice = validateMenuOption(transportationTypeMenu,
										TRANSPORTATION_TYPE_MENU_MAX,
										ZERO_ACCEPTED);
								transportType = transportTypes[transportChoice];
								transportArray = transports[transportChoice];
								System.out.println(
										"Enter the following information in order to add a " + transportType
												+ " option.");
								System.out.print("Company name: ");
								discard = keyboard.nextLine();
								companyName = keyboard.nextLine();
								System.out.print("Departure city: ");
								departureCity = keyboard.nextLine();
								System.out.print("Arrival city: ");
								arrivalCity = keyboard.nextLine();

								// Initialize based on flight, train, or bus:
								// FLIGHT
								if (transportChoice == numSavedFlightsIndex) {
									System.out.print("Airline name: ");
									airlineName = keyboard.nextLine();
									System.out.print("Luggage Allowance: ");
									luggageAllowance = keyboard.nextInt();
									System.out.print("Ticket price: ");
									ticketPrice = keyboard.nextDouble();
									try{
										transportArray[numSavedTransports[transportChoice]] = new Flight(companyName, departureCity, arrivalCity, airlineName, luggageAllowance, ticketPrice);
										transportCreated = true;
									} catch(InvalidTransportDataException e) {
										System.err.println(e.getMessage() + " Failed to create Flight.");
									}
									
								}
								// TRAIN
								else if (transportChoice == numSavedTrainsIndex) {
									System.out.print("Train type: ");
									trainType = keyboard.nextLine();
									System.out.print("Seat class (economy, business, sleeper): ");
									seatClass = keyboard.next();
									System.out.print("Base fare: ");
									baseFare = keyboard.nextDouble();
									try{
										transportArray[numSavedTransports[transportChoice]] = new Train(companyName,
											departureCity, arrivalCity, trainType, seatClass, baseFare);
										transportCreated = true;
									}catch(InvalidTransportDataException e) {
										System.err.println(e.getMessage() + " Failed to create Train.");
									}
									
								}
								// BUS
								else if (transportChoice == numSavedBusesIndex) {
									System.out.print("Number of stops: ");
									numberOfStops = keyboard.nextInt();
									System.out.print("Bus fare: ");
									busFare = keyboard.nextDouble();
									try{
										transportArray[numSavedTransports[transportChoice]] = new Bus(companyName,
											departureCity, arrivalCity, numberOfStops, busFare);
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
								System.out
										.println("Enter the index of the " + transportType
												+ " you would like to remove: ");
								index = keyboard.nextInt();
								if (index >= numSavedTransports[transportChoice]) {
									// no transport at that index. empty index in array
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
								}
								deleteIndexInArray(transportArray, numSavedTransports[transportChoice], index);
								numSavedTransports[transportChoice]--;
								break;
							case 3:// List transportation options by type (Flight, Train, Bus)
								System.out.println("Flights:");
								if (numSavedTransports[numSavedFlightsIndex] == 0) {
									System.out.println("None.");
								}
								printArray(transports[numSavedFlightsIndex], numSavedTransports[numSavedFlightsIndex]);
								System.out.println("\nTrains:");
								if (numSavedTransports[numSavedTrainsIndex] == 0) {
									System.out.println("None.");
								}
								printArray(transports[numSavedTrainsIndex], numSavedTransports[numSavedTrainsIndex]);
								System.out.println("\nBuses:");
								if (numSavedTransports[numSavedBusesIndex] == 0) {
									System.out.println("None.");
								}
								printArray(transports[numSavedBusesIndex], numSavedTransports[numSavedBusesIndex]);
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
						subMenuOption = validateMenuOption(accommodationManagementMenu,
								ACCOMMODATION_MANAGEMENT_MENU_MAX, !ZERO_ACCEPTED);
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
								if (accommodationChoice == numSavedHotelsIndex) { // Hotel
									System.out.print("Star rating: ");
									starRating = keyboard.nextDouble();
									try {
										hotels[numSavedAccommodations[numSavedHotelsIndex]] = new Hotel(name, location,
											pricePerNight, starRating);
										accommodationCreated = true;
									}catch(InvalidAccommodationDataException e) {
										System.err.println(e.getMessage() + " Failed to add Hotel.");
									}
									
								} else if (accommodationChoice == numSavedHostelsIndex) { // Hostel
									System.out.print("Number of shared beds per room: ");
									numOfBeds = keyboard.nextInt();
									try {
										hostels[numSavedAccommodations[numSavedHostelsIndex]] = new Hostel(name, location,
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
								System.out.print(
										"Enter the index of the " + accommodationType + " you would like to remove: ");
								index = keyboard.nextInt();
								if (index >= numSavedAccommodations[accommodationChoice]) {
									// no item at that index. return to previous menu
									System.out.println("Invalid index. Returning to previous menu...\n");
									break;
								}
								deleteIndexInArray(accommodationArray, numSavedAccommodations[accommodationChoice],
										index);
								numSavedAccommodations[accommodationChoice]--;
								break;
							case 3:// List accommodations by type
								System.out.println("Hotels: ");
								if (numSavedAccommodations[numSavedHotelsIndex] == 0) {
									System.out.println("None.");
								} else
									printArray(hotels, numSavedAccommodations[numSavedHotelsIndex]);
								System.out.println("Hostels: ");
								if (numSavedAccommodations[numSavedHostelsIndex] == 0) {
									System.out.println("None.");
								} else
									printArray(hostels, numSavedAccommodations[numSavedHostelsIndex]);
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
								if (numSavedTrips == 0) {
									System.out.println("There are no saved trips.\n");
									break;
								}
								try {
									findMostExpensiveTrip(trips, numSavedTrips);
								}catch(InvalidAccommodationDataException e) {
									System.err.println("Failed to calculate most expensive trip. One of the trips has an accommodation with an invalid number of days.");
								}
								
								break;
							case 2:// Calculate and display the total cost of a trip
								if (numSavedTrips == 0) {
									System.out.println("There are no saved trips.\n");
									break;
								}
								System.out.println("Here is the list of trips");
								printArray(trips, numSavedTrips);
								index = validateMenuOption(
										"\nEnter the index of the trip you would like to calculate the cost of:",
										numSavedTrips - 1, ZERO_ACCEPTED);
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
				} else if (option == 6) {
					System.out.println("Thank you for using the Smart Travel Agency Booking System!");
					System.out.println("Returning to main menu...");
					System.exit(0);
				}

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

	public static int findObjectInArray(Object[] array, Object obj) {
		int index = -1;
		// can traverse the full array since null can be passed to .equals() method of
		// any of the classes being used
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(obj)) {
				return i;
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
