/*
* -----------------------------------------------------------------------------------
* Assignment 2
* Written by Wania Faraz 40332781
*            Zahira Atmani 40350242
* -----------------------------------------------------------------------------------
* The purpose of this class is to act as a main control for the Smart Travel App.
* It manages all the data, including clients, trips, accommodations and transportations.
* It's also responsible for: 
*               1) Storing data: maintain arrays and counters for all items
*               2) Business logic: adds clients (with validation) and trips following the required rules
*               3) Search: find items by ID and handle missing info with exceptions
*               4) Persistence: loads and save data by using the Manager files
*               5) Calculations: computes the total trip cost
*/

package service;

// import all the required packages, exceptions and persistence files 
import clientPackage.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import exceptions.*;
import interfaces.Identifiable;
import interfaces.Printable;
import persistence.*;
import travelPackage.*;

//
public class SmartTravelService {

	// Lists to store all objects
	private List<Client> clients;
	private List<Trip> trips;
	public List<Accommodation> accommodations;
	public List<Transportation> transports;

	// Lists for the recent history?Should they go here...
	private RecentList<Client> recentClients;
	private RecentList<Trip> recentTrips;
	private RecentList<Accommodation> recentAccommodations;
	private RecentList<Transportation> recentTransports;

	// constructor
	public SmartTravelService() {
		clients = new ArrayList<>();
		trips = new ArrayList<>();
		accommodations = new ArrayList<>();
		transports = new ArrayList<>();
	}

	// GETTERS
	// GET COUNTS
	public int getClientCount() {
		return clients.size();
	}

	public int getTripCount() {
		return trips.size();
	}

	public int getAccommodationCount() {
		return accommodations.size();
	}

	public int getTransportationCount() {
		return transports.size();
	}

	// GET OBJECT FROM INDEX
	public Client getClient(int i) {
		return clients.get(i);
	}

	public Trip getTrip(int i) {
		return trips.get(i);
	}

	public Accommodation getAccommodation(int i) {
		return accommodations.get(i);
	}

	public Transportation geTransportation(int i) {
		return transports.get(i);
	}

	// ADD METHODS - CREATES THE OBJECT AND ADDS IT TO THE APPROPRIATE ARRAY
	// method: add a client to the system
	public void addClient(String firstName, String lastName, String emailAddress)
			throws InvalidClientDataException, DuplicateEmailException {
		// check for duplicate email
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i) != null && clients.get(i).getEmail().equalsIgnoreCase(emailAddress)) {
				throw new DuplicateEmailException("A client with this email already exist.");
			}
		}
		// create and store client
		Client client = new Client(firstName, lastName, emailAddress);
		clients.add(client);
		recentClients.addRecent(client);
	}

	// method: create Trip to store in the system
	public void createTrip(String destination, int duration, double basePrice,
			String clientId, String accommodationId, String transportId) {
		//FINDS THE CLIENT, ACCOMMODATION, AND TRANSPORTATION BY ID
		//TRIP NOT CREATED IF: (CLIENT NOT FOUND) OR (TRANSPORT AND ACCOMMODATION NOT FOUND)
		//IF TRIP IS NOT CREATED, EXEPTIONS HANDLED AND ERROR MESSAGE PRINTED
		int clientIndex, transportIndex, accommodationIndex;
		// find the client object
		try {
			clientIndex = findObjectByID(clients, clientId);
			Client foundClient = clients.get(clientIndex); // if not found, an exception will be thrown
			// if no ID is provided, then accommodation and transportation stays null
			Accommodation foundAccommodation = null;
			Transportation foundTransportation = null;
			//find accommodation
			try {
				if (accommodationId != null && !accommodationId.equals("")) {
					accommodationIndex = findObjectByID(accommodations, accommodationId);
					foundAccommodation = accommodations.get(accommodationIndex);
				}
			}catch (EntityNotFoundException e) {
				foundAccommodation = null;
				System.err.println(e.getMessage() + " Setting accommodation to null.\n");
			}
			try {
				if (transportId != null && !transportId.equals("")) {
					transportIndex = findObjectByID(transports, transportId);
					foundTransportation = transports.get(transportIndex);
				}
			}catch (EntityNotFoundException e) {
				foundTransportation = null;
				System.err.println(e.getMessage() + " Setting transportation to null.\n");
			}
			//if no transport and no accommodation, throw exception
			if (foundAccommodation == null && foundTransportation == null) {
				throw new InvalidTripDataException("Trip must include at least an accommodation or a transportation");
			}
			// If at least a transportation or accommodation exists, create trip
			try {
				Trip trip = new Trip(destination, duration, basePrice, foundClient, foundAccommodation, foundTransportation);
				trips.add(trip);
				recentTrips.addRecent(trip);
			}catch(InvalidTripDataException e) {
				System.err.println(e.getMessage() + " Failed to create trip. \n");
			}catch(InvalidAccommodationDataException e) {
				System.err.println(e.getMessage() + " Failed to create trip.\n");
			}catch(InvalidTransportDataException e) {
				System.err.println(e.getMessage() + " Failed to create trip.\n");
			}
		}catch (EntityNotFoundException e) {
			System.err.println(e.getMessage() + " Failed to create trip. \n");
		}catch(InvalidTripDataException e) {
			System.err.println(e.getMessage() + " Failed to create trip.\n");
		}
	}
	// Create and add accommodation
	public void addHotel(String name, String location, double pricePerNight, int starRating) {
		try {
			Hotel hotel = new Hotel(name, location, pricePerNight, starRating);
			accommodations.add(hotel);
			recentAccommodations.addRecent(hotel);
			System.out.println("Hotel added succesfully!\n");
		} catch (InvalidAccommodationDataException e) {
			System.err.println(e.getMessage() + " Failed to create hotel.\n");
		}
	}

	public void addHostel(String name, String location, double pricePerNight, int numOfBeds) {
		try {
			Hostel hostel = new Hostel(name, location, pricePerNight, numOfBeds);
			accommodations.add(hostel);
			recentAccommodations.addRecent(hostel);
			System.out.println("Hostel added succesfully!\n");
		} catch (InvalidAccommodationDataException e) {
			System.err.println(e.getMessage() + " Failed to create hostel.\n");
		}
	}

	// Create and add transportation
	public void addFlight(String companyName, String departureCity, String arrivalCity, double ticketPrice,
			double luggageAllowance) {
		try {
			Flight flight = new Flight(companyName, departureCity, arrivalCity, ticketPrice, luggageAllowance);
			transports.add(flight);
			recentTransports.addRecent(flight);
			System.out.println("Flight added successfully!\n");
		} catch (InvalidTransportDataException e) {
			System.err.println(e.getMessage() + " Failed to create flight.\n");
		}
	}

	public void addTrain(String companyName, String departureCity, String arrivalCity, double baseFare,
			String trainType) {
		// creating a train does not throw an exception
		Train train = new Train(companyName, departureCity, arrivalCity, baseFare, trainType);
		transports.add(train);
		recentTransports.addRecent(train);
		System.out.println("Train added successfully!\n");
	}

	public void addBus(String companyName, String departureCity, String arrivalCity, double busFareint,
			int numberOfStops) {
		try {
			Bus bus = new Bus(companyName, departureCity, arrivalCity, busFareint, numberOfStops);
			transports.add(bus);
			recentTransports.addRecent(bus);
		} catch (InvalidTransportDataException e) {
			System.err.println(e.getMessage() + " Failed to create bus. \n");
		}
	}

	// method: check if client exist
	public boolean clientExists(String clientID) {
		// loop thru the client array
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i) != null && clients.get(i).getClientID().equals(clientID)) {
				return true; // return true if found
			}
		}
		return false; // return false if client doesnt exist
	}

	// FIND OBJECT AND RETURN INDEX
	public static <T extends Identifiable> int findObjectByID(List<T> list, String ID) throws EntityNotFoundException {
		int index = 0;
		for (Identifiable item : list) {
			if (item.getId().equals(ID))
				return index;
			index++;
		}
		throw new EntityNotFoundException("Entity not found."); // item has not been found since loop is over and
																	// nothing was returned
	}

	// load all data method

	public void loadAllData(String folderPath) {
		// call the file manager to load clients, accommodations, transportations, trips
		// empty all arrays before loading data
		clients = new ArrayList<>();
		trips = new ArrayList<>();
		accommodations = new ArrayList<>();
		transports = new ArrayList<>();

		// DECIDE HOW TO DO THE RECENT CLIENTS, TRIPS, ...
		recentClients = new RecentList<>();
		recentTrips = new RecentList<>();
		recentAccommodations = new RecentList<>();
		recentTransports = new RecentList<>();
		try {
			ClientFileManager.loadClients(clients, folderPath + "clients.csv");
			AccommodationFileManager.loadAccommodations(accommodations, folderPath + "accommodations.csv");
			TransportFileManager.loadTransportations(transports, folderPath + "transports.csv");
			TripFileManager.loadTrips(trips, folderPath + "trips.csv", clients, accommodations, transports);

			System.out.println("All data loaded successfully.\n"); // print the message once its done

		} catch (IOException e) { //
			System.out.println("Error while loading data: " + e.getMessage());
		}
	}

	// save all data method
	public void saveAllData(String folderPath) {
		// call upon manager file to save data into the csv files for clients
		try {
			ClientFileManager.saveClients(
					clients,
					folderPath + "clients.csv");
			// call upon manager file to save data into the csv files for hotels and hostels
			AccommodationFileManager.saveAccommodations(
					accommodations,
					folderPath + "accommodations.csv");
			// call upon manager file to save data into the csv files for flights, trains
			// and buses
			TransportFileManager.saveTransportations(
					transports,
					folderPath + "transports.csv");
			//// call upon manager file to save data into the csv files for trips
			TripFileManager.saveTrips(
					trips,
					folderPath + "trips.csv");
			System.out.println("All data saved successfully."); // print message once its done
		} catch (IOException e) {
			System.out.println("Error while saving data: " + e.getMessage()); // print a message if there's an error
		}
	}

	// calculate total trip cost at a given index
	public double calculateTripTotal(int index) {
		// check if index is invalid
		if (index < 0 || index >= trips.size() || trips.get(index) == null) {
			return -1; // returns -1 if index is not valid
		}
		// get client, accommodation and transport of the trip
		Client client = trips.get(index).getClient();
		Transportation transport = trips.get(index).getTransportation();
		Accommodation accommodation = trips.get(index).getAccommodation();
		try {
			return trips.get(index).getTotalCost(client, accommodation, transport); // if index is valid, return the
																					// full
			// trip cost

		} catch (InvalidAccommodationDataException e) {
			System.err.println(e.getMessage());
		}
		return -1; // if error in try block
	}

	//print list method - to be used to print any array
	private static <T extends Printable> void printList(List<T> list) {
		int count = 1;
		for (Printable item : list) {
			System.out.println(count + ". " + item);
			count++;
			System.out.println();
		}
	}
	//print list methods
	public void printClients() {
		printList(clients);
	}
	public void printTrips() {
		printList(trips);
	}
	public void printAccommodations() {
		printList(accommodations);
	}
	public void printTransports() {
		printList(transports);
	}
}
