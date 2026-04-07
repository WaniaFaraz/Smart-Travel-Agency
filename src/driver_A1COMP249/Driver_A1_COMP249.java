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
import interfaces.*;

import visualization.DashboardGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
		final boolean ZERO_ACCEPTED = true; // to pass to validate menu option - to know whether a menu has a 0 option
											// or not

		SmartTravelService service = new SmartTravelService();
		
		boolean dataLoaded = false; // to keep track of whether the data has been loaded

		String mainMenu = """
				1. Client Management
				2. Trip Management
				3. Transportation Management
				4. Accommodation Management
				5. Additional Operations
				6. Generate Visualization
				7. Advanced Analytics
				8. Load All Data (output/data/*.csv)
				9. Save All Data (output/data/*.csv)
				10. Run predefined scenario
				   11. Generate Dashboard (HTML + charts)
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
				Please enter your choice here: """ + " ";
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

		String advancedAnalyticsMenu = """
				Please select an option from the menu below:
					1. Trips by destination
					2. Trips by cost range
					3. Top Clients by Spending
					4. Recent Trips
					5. Smart Sort Collections
					6. Back to main menu
				""";
		final int ADVANCED_ANALYTICS_MENU_MAX = 6;

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
		String ID; // to store the ID of clients, trips, transportations, and accommodations
		while (runMainMenu) {
			option = validateMenuOption(mainMenu, MAIN_MENU_MAX, ZERO_ACCEPTED);
			// each subMenu below uses switch to manage flow. No need for a default case
			// since the validateMenuOption method ensures that all input is valid
			System.out.println();
			// Client Management
			if (option == 1) {
				while (subMenuOption != CLIENT_MANAGEMENT_MENU_MAX) {
					// prompt user to choose a menu option and validate input
					subMenuOption = validateMenuOption(clientManagementMenu, CLIENT_MANAGEMENT_MENU_MAX,
							!ZERO_ACCEPTED);
					System.out.println();
					// variables required in more than one client management menu option
					String firstName;
					String lastName;
					String emailAddress;
					boolean clientUpdated = false; // to decide whether to print success message
					boolean printed = false; //to know whether the print method from service was executed or not
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
							service.addClient(firstName, lastName, emailAddress); //error handled in service
							break;
						case 2: // Edit a client
							Client clientToEdit = null;
							if (service.getClientCount() == 0) {
								System.out.println("There are no saved clients.\n");
								break;
							}
							System.out.println("\nHere is the list of all current clients:");
							// print all IDs in names array
							printed = service.printClients();
							if(!printed) break; //no clients - nothing to edit - exit current
							System.out.print("Enter the ID of the client you wish to edit: ");
							ID = keyboard.next();
							try {
								index = service.findClient(ID);
								clientToEdit = service.getClient(index);
							} catch (EntityNotFoundException e) {
								System.err.println("Client not found." + "\n");
								break; // client not found - exit current menu option
							}
							// print menu to edit client and prompt user to pick an attribute to edit.
							// Validate user input
							subMenuOption = validateMenuOption(clientEditMenu, CLIENT_EDIT_MENU_MAX, !ZERO_ACCEPTED);
							switch (subMenuOption) {
								case 1:// edit first name
									System.out.print("Enter new first name: ");
									firstName = keyboard.next();
									try {
										clientToEdit.setFirstName(firstName);
										clientUpdated = true;
									} catch (InvalidClientDataException e) {
										System.err.println(e.getMessage() + " Failed to edit client first name\n.");
									}
									break;
								case 2: // edit last name
									System.out.print("Enter new last name: ");
									lastName = keyboard.next();
									try {
										clientToEdit.setLastName(lastName);
										clientUpdated = true;
									} catch (InvalidClientDataException e) {
										System.err.println(e.getMessage() + " Failed to edit client last name.\n");
									}
									break;
								case 3: // edit email address
									System.out.print("Enter new email address: ");
									emailAddress = keyboard.next();
									try {
										clientToEdit.setEmailAddress(emailAddress);
										clientUpdated = true;
									} catch (InvalidClientDataException e) {
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
								System.out.println("\nClient updated successfully!\n"); // Success message
							}
							break;
						case 3: // Delete a client
							System.out.println("\nHere is the list of current clients:");
							printed = service.printClients();
							if(!printed) break; //no clients, exit
							System.out.print("Enter the ID of the client you wish to delete: ");
							ID = keyboard.next();
							service.deleteClient(ID);
							break;
						case 4: // List all clients
							System.out.println("Here is the list of clients.\n");
							printed = service.printClients();
							if(!printed) break; //no clients, exit
							break;
						case 5: // Back to Main Menu
							System.out.println("Returning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			}
			// Trip Management
			else if (option == 2) {
				// trip variables
				String destination;
				int duration;
				double basePrice;
				int indexOfClient, indexOfAccommodation, indexOfTransport; // index of client in clients array
																			// associated with trip
																			// (same for accommodation and transport)
				Client clientOfTrip; // reference to actual client taken from array. for visual simplicity
				double amountSpent; // for updating the client amountSpent attribute
				Accommodation accommodationOfTrip; // reference to accommodation from array or from trip (visual
													// simplicity)
				Transportation transportOfTrip; // reference to transportation from array or from trip (visual
												// simplicity)
				int indexOfTrip; // index in trips array
				boolean printed = false; //to know whether the list of service was printed
				while (subMenuOption != TRIP_MANAGEMENT_MENU_MAX) { // TRIP MANAGEMENT MENU
					// print menu and validate input
					subMenuOption = validateMenuOption(tripManagementMenu, TRIP_MANAGEMENT_MENU_MAX,
							!ZERO_ACCEPTED);
					switch (subMenuOption) {
						case 1:// Create a trip
							String clientID, transportID, accommodationID;
							System.out.println("Enter the following information in order to create a new trip.");
							System.out.print("Destination of trip: ");
							destination = keyboard.next();
							System.out.print("Duration of trip: ");
							duration = keyboard.nextInt();
							System.out.print("Base price of trip: ");
							basePrice = keyboard.nextDouble();
							//CLIENT SELECTION
							System.out.println("Client selection for trip:\n");
							printed = service.printClients();
							if(!printed) {
								System.out.println("Unable to proceed any further. Returning to previous menu...");
								break; //no clients, exit
							}
							System.out.print("Enter the ID of the client associated with this trip: ");
							clientID = keyboard.next();
							//ACCOMMODATION SELECTION
							System.out.println("Accommodation selection for trip:\n");
							printed = service.printAccommodations();
							if(!printed) {
								System.out.println("Unable to proceed any further. Returning to previous menu...");
								break; //no clients, exit
							}
							System.out.print("Enter the ID of the accommodation associated with this trip: ");
							accommodationID = keyboard.next();
							//TRANSPORTATION SELECTION
							System.out.println("Transportation selection for trip:\n");
							printed = service.printTransports();
							if(!printed) {
								System.out.println("Unable to proceed any further. Returning to previous menu...");
								break; //no clients, exit
							}
							System.out.print("Enter the ID of the transportation associated with this trip: ");
							transportID = keyboard.next();
							//CREATE TRIP
							service.createTrip(destination, duration, basePrice, clientID, accommodationID, transportID);
							//if trip was not created, the service will print an error message
							break;
						case 2:// Edit Trip
							boolean tripUpdated = false; // verification for success message
							System.out.println("\nHere is the list of current trips:");
							printed = service.printTrips();
							if(!printed) break; //no trips to edit, exit
							System.out.println("Enter the ID of the trip you wish to edit: ");
							ID = keyboard.next();
							try {
								indexOfTrip = service.findTrip(ID);
							} catch (EntityNotFoundException e) {
								System.err.println(e.getMessage());
								break; // trip not found - exit menu option since unable to edit trip
							}
							Trip tripToEdit = service.getTrip(indexOfTrip);
							subMenuOption = validateMenuOption(tripEditMenu, TRIP_EDIT_MENU_MAX, !ZERO_ACCEPTED); // Print trip menu and prompt user for choice. Validate input
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
									} catch (InvalidTripDataException e) {
										System.err.println(e.getMessage() + " Failed to edit trip duration.");
									}
									break;
								case 3: // edit base price
									System.out.print("Enter new base price: ");
									basePrice = keyboard.nextDouble();
									try {
										tripToEdit.setBasePrice(basePrice);
										tripUpdated = true;
									} catch (InvalidTripDataException e) {
										System.err.println(e.getMessage() + " Failed to edit trip base price.");
									}
									break;
								case 4: // edit client
									System.out.println("\nHere is the list of current clients: ");
									service.printClients();
									System.out
											.print("Enter the ID of the client you wish to associate with this trip:");
									ID = keyboard.next();
									try {
										indexOfClient = service.findClient(ID);
										clientOfTrip = service.getClient(indexOfClient);
										try {
											tripToEdit.setClient(clientOfTrip);
											tripUpdated = true;
										} catch (InvalidTripDataException e) {
											System.err.println(e.getMessage() + " Failed to edit client.");
										}
									} catch (EntityNotFoundException e) {
										System.err.println(e.getMessage() + "\n");
									}
									break;
								case 5: // edit accommodation
									System.out.println("\nHere is the list of current accommodations offered: ");
									printed = service.printAccommodations();
									if(!printed) break; //no accommodations; exit
									System.out.print("Enter the ID of the accommodation you wish to associate with this trip:");
									ID = keyboard.next();
									try {
										indexOfAccommodation = service.findAccommodation(ID);
										accommodationOfTrip = service.getAccommodation(indexOfAccommodation);
										try {
											tripToEdit.setAccommodation(accommodationOfTrip);
											tripUpdated = true;
										} catch (InvalidAccommodationDataException e) {
											System.err.println(e.getMessage() + "Failed to edit Accommodation.");
										}
									} catch (EntityNotFoundException e) {
										System.err.println(e.getMessage() + "\n");
									}
									break;
								case 6: // edit transportation
									System.out.println("\nHere is the list of current transportations offered: ");
									printed = service.printTransports();
									if(!printed) break; //no accommodations; exit
									System.out.print("Enter the ID of the transportation you wish to associate with this trip:");
									ID = keyboard.next();
									try {
										indexOfTransport = service.findTransport(ID);
										transportOfTrip = service.geTransportation(indexOfTransport);
										try {
											tripToEdit.setTransportation(transportOfTrip);
											tripUpdated = true;
										} catch (InvalidTransportDataException e) {
											System.err.println(e.getMessage() + "Failed to edit Transportation.");
										}
									} catch (EntityNotFoundException e) {
										System.err.println(e.getMessage() + "\n");
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
							System.out.println("\nHere is the list of current trips:");
							printed = service.printTrips();
							if(!printed) break; //no trips, exit							
							System.out.print("Enter the ID of the trip you wish to cancel: ");
							ID = keyboard.next();
							service.deleteTrip(ID);
							break;
						case 4:// List all trips
							service.printTrips();
							break;
						case 5:// List all trips for a specific client
							printed = service.printClients();
							if(!printed) break;
							System.out.print("\nEnter the ID of the client whose trips you wish to see: ");
							ID = keyboard.next();
							service.printTripsOfClient(ID);
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
					subMenuOption = validateMenuOption(transportationManagementMenu, TRANSPORTATION_MANAGEMENT_MENU_MAX,
							!ZERO_ACCEPTED);
					int transportChoice; // transportChoice menu option
					boolean printed; //to know whether the service printed the list
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
							transportChoice = validateMenuOption(transportationTypeMenu, TRANSPORTATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							final int FLIGHT = 0, TRAIN = 1, BUS = 2; // from above menu that was printed
							// transportChoice == 3 : RETURN TO PREVIOUS MENU
							if (transportChoice == 3)
								break;

							System.out.println("Enter the following information in order to add a transport.");
							System.out.print("Company name: ");
							discard = keyboard.nextLine();
							companyName = keyboard.nextLine();
							System.out.print("Departure city: ");
							departureCity = keyboard.nextLine();
							System.out.print("Arrival city: ");
							arrivalCity = keyboard.nextLine();
							// Initialize based on flight, train, or bus:
							// FLIGHT
							if (transportChoice == FLIGHT) {
								System.out.print("Luggage Allowance: ");
								luggageAllowance = keyboard.nextDouble();
								System.out.print("Ticket price: ");
								ticketPrice = keyboard.nextDouble();
								service.addFlight(companyName, departureCity, arrivalCity, ticketPrice, luggageAllowance);
							}
							// TRAIN
							else if (transportChoice == TRAIN) {
								System.out.print("Train type: ");
								trainType = keyboard.nextLine();
								System.out.print("Base fare: ");
								baseFare = keyboard.nextDouble();
								service.addTrain(companyName, departureCity, arrivalCity, baseFare, trainType);
							}
							// BUS
							else if (transportChoice == BUS) {
								System.out.print("Number of stops: ");
								numberOfStops = keyboard.nextInt();
								System.out.print("Bus fare: ");
								busFare = keyboard.nextDouble();
								service.addBus(companyName, departureCity, arrivalCity, busFare, numberOfStops);
							}
							break;
						case 2:// Remove a transportation option
							System.out.println("Here is the list of current transports:");
							printed = service.printTransports();
							if(!printed) break; //no transports, exit
							System.out.println("Enter the ID of the transport you would like to remove: ");
							ID = keyboard.next();
							service.deleteTransportation(ID);
							break;
						case 3:// List transportation options by type (Flight, Train, Bus)
							System.out.println("\nFlights:");
							service.printFlights();
							System.out.println("\nTrains:");
							service.printTrains();
							System.out.println("\nBuses:");
							service.printBuses();
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
					subMenuOption = validateMenuOption(accommodationManagementMenu, ACCOMMODATION_MANAGEMENT_MENU_MAX,
							!ZERO_ACCEPTED);
					int accommodationChoice; // int from accommodations type menu. same as numSavedHotelsIndex or
												// numSavedHostelsIndex
					// General accommodation variables
					String name, location;
					double pricePerNight;
					int starRating; // Hotel variable
					int numOfBeds; // Hostel variable
					boolean accommodationCreated = false; // to decide whether or not to print success message
					boolean printed = false; //to know if service printed a list
					switch (subMenuOption) {
						case 1:// Add an accommodation
							final int HOTEL = 0, HOSTEL = 1;
							System.out.println("What is the type of accommodation option you would like to add?");
							accommodationChoice = validateMenuOption(accommodationTypeMenu,
									ACCOMMODATION_TYPE_MENU_MAX,
									ZERO_ACCEPTED);
							if (accommodationChoice == 2)
								break; // option 2 is return to previous menu - so exit current menu option
							System.out.println(
									"Enter the following information in order to add an accommodation option.");
							System.out.print("Name of establishment: ");
							discard = keyboard.nextLine();
							name = keyboard.nextLine();
							System.out.print("Location: ");
							location = keyboard.nextLine();
							System.out.print("Price per night: ");
							pricePerNight = keyboard.nextDouble();
							// Specific accommodation type variables and initialization
							if (accommodationChoice == HOTEL) { // Hotel
								System.out.print("Star rating: ");
								starRating = keyboard.nextInt();
								service.addHotel(name, location, pricePerNight, starRating);
							} else if (accommodationChoice == HOSTEL) { // Hostel
								System.out.print("Number of shared beds per room: ");
								numOfBeds = keyboard.nextInt();
								service.addHostel(name, location, pricePerNight, numOfBeds);
							}
							break;
						case 2:// Remove an accommodation
							System.out.println("Here is the list of current accommodations offered");
							printed = service.printAccommodations();
							if(!printed) break; //no accommodations, exit
							System.out.print("Enter the ID of the accommodation you would like to remove: ");
							ID = keyboard.next();
							service.deleteAccommodation(ID);
							break;
						case 3:// List accommodations by type
							int hotelCount = 0, hostelCount = 0;
							// PRINT HOTELS
							System.out.println("Hotels: ");
							service.printHotels();
							// PRINT HOSTELS
							System.out.println("Hostels: ");
							service.printHostels();
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
				boolean printed = false; //to know if service printed a list
				while (subMenuOption != ADDITIONAL_OPERATIONS_MENU_MAX) {
					subMenuOption = validateMenuOption(additionalOperationsMenu, ADDITIONAL_OPERATIONS_MENU_MAX,
							!ZERO_ACCEPTED);
					switch (subMenuOption) {
						case 1:// Display the most expensive trip
							service.findMostExpensiveTrip();
							break;
						case 2:// Calculate and display the total cost of a trip
							System.out.println("Here is the list of trips");
							printed = service.printTrips();
							System.out.print("\nEnter the ID of the trip you would like to calculate the cost of: ");
							ID = keyboard.next();
							int indexOfTrip = service.findTrip(ID);
							double cost = service.calculateTripTotal(indexOfTrip);
							String formattedCost = String.format("%.2f", cost);
							System.out.println("Total cost of trip " + ID + ": $" + formattedCost);
							break;
						case 3:// Create a deep copy of the transportation array
							List<Transportation> copiedTransports = service.copyTransportationArray()
							break;
						case 4:// Create a deep copy of the accommodation array
							List<Accommodation> copieAccommodations = service.copyAccommodationArray();
							break;
						case 5:// Back to main menu
							System.out.println("\nReturning to main menu...\n");
							break;
					}
				}
				subMenuOption = 0; // reset to 0 since all other subMenuOptions use the same variable
			}
			// GENERATE VISUALIZATION
			else if (option == 6) {

			}
			//Advanced Analytics Menu (Prints all trip data)
			else if (option == 7) {
				while (subMenuOption != ADVANCED_ANALYTICS_MENU_MAX) {
					// prompt user to choose a menu option and validate input
					subMenuOption = validateMenuOption(advancedAnalyticsMenu, ADVANCED_ANALYTICS_MENU_MAX, !ZERO_ACCEPTED);
					System.out.println();
					switch(subMenuOption) {
						case 1: 

					}
				}
			}
			// Load All Data (output/data/*.csv)
			else if (option == 8) {
				// since all the arrays have been passed by reference, they should be
				// automatically updated when this method is called
				service.loadAllData("output/data/");
				dataLoaded = true;

			}
			// Save All Data (output/data/*.csv)
			else if (option == 9) {
				int response;
				boolean saveData = true;
				if (!dataLoaded) {
					saveData = false; // dont save data yet. decide
					System.out.println(
							"Data has not been loaded. All previously saved information that has not been loaded will be lost.");
					System.out.println("Are you sure you would like to save information at this stage?");
					System.out.print("1. Yes\n2. No\nChoice: ");
					response = keyboard.nextInt();
					if (response == 1) {
						saveData = true;
					} else if (response == 2) {
						System.out.println("\nData will not be saved.");
						System.out.println("Returning to main menu...\n");
					} else {
						System.out.println("\nInvalid selection. Returning to main menu...\n");
					}
				}
				if (saveData) {
					service.saveAllData("output/data/");
				}

			}
			// Run predefined scenario
			else if (option == 10) {
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
					// Client creation
					client1 = new Client("Jane", "Doe", "janedoe@gmail.com");
					client2 = new Client("John", "Doe", "johndoe@gmail.com");
					client3 = new Client("Bob", "Ross", "bobross@gmail.com");
					client4 = new Client("Bob", "Ross", "bobross@gmail.com");

					// Transportation creation
					flight1 = new Flight("Emirates Airlines", "Toronto", "Dubai", 25, 250);
					flight2 = new Flight("British Airways", "London", "Santiago", 23, 300);
					flight3 = new Flight("British Airways", "London", "Santiago", 23, 300);

					train1 = new Train("VIA Rail", "Montreal", "Toronto", 30, "Intercity");
					train2 = new Train("VIA Rail", "Montreal", "Halifax", 40, "Transcontinental");
					train3 = new Train("VIA Rail", "Montreal", "Halifax", 40, "Transcontinental");

					bus1 = new Bus("Orleans Express", "Montreal", "Ottawa", 4, 10);
					bus2 = new Bus("FlixBus", "Vancouver", "Calgary", 3, 15);
					bus3 = new Bus("FlixBus", "Vancouver", "Calgary", 3, 15);

					// Accommodation creation
					hotel1 = new Hotel("Holiday Inn", "Lisbon", 108, 3);
					hotel2 = new Hotel("Sheraton", "Istanbul City Center", 184, 5);
					hotel3 = new Hotel("Sheraton", "Istanbul City Center", 184, 5);

					hostel1 = new Hostel("Once Again Hostel", "Bangkok", 47, 2);
					hostel2 = new Hostel("The Bee Hostel", "Amsterdam City Centre", 80, 1);
					hostel3 = new Hostel("The Bee Hostel", "Amsterdam City Centre", 80, 1);

					// Trip creation
					trip1 = new Trip("Spain", 5, 150, client1, hotel1, flight1);
					trip2 = new Trip("Egypt", 3, 200, client2, hotel2, flight2);
					trip3 = new Trip("Phillipines", 3, 430, client3, hotel3, flight3);
					trip4 = new Trip("Phillipines", 3, 430, client3, hotel3, flight3);

					creationSuccessful = true;
				} catch (InvalidClientDataException e) {
					System.err.println(e.getMessage() + " Client not created.");
				} catch (InvalidTransportDataException e) {
					System.err.println(e.getMessage() + " Transport not created.");
				} catch (InvalidAccommodationDataException e) {
					System.err.println(e.getMessage() + " Accommodation not created.");
				} catch (InvalidTripDataException e) {
					System.err.println(e.getMessage() + " Trip not created.");
				} finally {
					System.out.println("Creation successful: " + creationSuccessful);
				}

				if (creationSuccessful) { // all objects have been created successfully
					// 4. Create Arrays for...
					List<Client> clientsHardcoded = new ArrayList<>(Arrays.asList(client1, client2, client3, client4));
					List<Trip> tripsHardcoded = new ArrayList<>(Arrays.asList(trip1, trip2, trip3, trip4));
					List<Transportation> transportationsHardcoded = new ArrayList<>(
							Arrays.asList(flight1, flight2, flight3, train1, train2, train3, bus1, bus2, bus3));
					List<Accommodation> accommodationsHardcoded = new ArrayList<>(
							Arrays.asList(hotel1, hotel2, hotel3, hostel1, hostel2, hostel3));

					// 2. Display all objects using toString()
					System.out.println("\n2. Display all objects using toString():");

					System.out.println("\nClients:");
					for (int i = 0; i < clientsHardcoded.size(); i++) {
						System.out.println(clientsHardcoded.get(i));
						System.out.println();
					}

					System.out.println("\nTrips:");
					for (int i = 0; i < tripsHardcoded.size(); i++) {
						System.out.println(tripsHardcoded.get(i));
						System.out.println();
					}

					System.out.println("\nTransportations:");
					for (int i = 0; i < transportationsHardcoded.size(); i++) {
						System.out.println(transportationsHardcoded.get(i));
						System.out.println();
					}

					System.out.println("\nAccommodations:");
					for (int i = 0; i < accommodationsHardcoded.size(); i++) {
						System.out.println(accommodationsHardcoded.get(i));
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
						// may throw InvalidAccommodationException if numberOfDays is too low
						System.out.println("\nTotal cost of all trips ");
						System.out.println("Trip 1: " + trip1.calculateTotalCost());
						System.out.println("Trip 2: " + trip2.calculateTotalCost());
						System.out.println("Trip 3: " + trip3.calculateTotalCost());
						System.out.println("Trip 4: " + trip4.calculateTotalCost());
					} catch (InvalidAccommodationDataException e) {
						System.err.println(e.getMessage());
					}

					// Method that displays the most expensive trip
					System.out.println();
					System.out.println();
					try {
						findMostExpensiveTrip(tripsHardcoded);
					} catch (InvalidAccommodationDataException e) {
						System.err.println(
								"Failed to calculate most expensive trip. One of the trips has an accommodation with an invalid number of days.");
					}

					// Deep copy of transportation array

					try {
						List<Transportation> transportsCopy = copyTransportationArray(transportationsHardcoded);
						transportsCopy.remove(2);
						transportsCopy.add(null);
						System.out.println("\n\nOriginal transports array:");
						printArray(transportationsHardcoded);
						System.out.println("\n\nCopied transports array - modified:");
						printArray(transportsCopy);
					} catch (InvalidTransportDataException e) {
						System.err.println(e.getMessage() + " Failed to copy array.");
					}

				}
			}
			// dashboard generator
			else if (option == 11) {
				try {
					DashboardGenerator.generateDashboard(service);
				} catch (IOException e) {
					System.err.println(e.getMessage());
				}

			}
			// Exit
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



	
////CHECK IF CAN BE REMOVED....
	public static int indexOfTripOfClient(List<Trip> trips, Client client) {
		// check the trips array to find the first trip that is associated to the given
		// client
		int index = -1; // if client not found, return -1
		for (int i = 0; i < trips.size(); i++) {
			Trip trip = trips.get(i);
			if (trip.getClient().equals(client)) {
				index = i;
				break;
			}
		}
		return index;
	}



	

	

}
