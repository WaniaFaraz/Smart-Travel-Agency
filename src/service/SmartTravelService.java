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
import persistence.*;
import travelPackage.*;

//
public class SmartTravelService {

	// Lists to store all objects
	private List<Client> clients;
	private List<Trip> trips;
	public List<Accommodation> accommodations;
	public List<Transportation> transports;

	//Lists for the recent history?Should they go here
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

	// getters
	//getters for the whole list
	public List<Client> getClients() {
		return clients;
	}

	public List<Trip> getTrips() {
		return trips;
	}

	public List<Accommodation> getAccommodations() {
		return accommodations;
	}

	public List<Transportation> getTransportations() {
		return transports;
	}

	// method: add a client to the system
	public void addClient(Client client) throws InvalidClientDataException, DuplicateEmailException {
		
		// check for duplicate email
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i) != null && clients.get(i).getEmailAddress().equalsIgnoreCase(client.getEmailAddress())) {
				throw new DuplicateEmailException("A client with this email already exist.");
			}
		}
		// create and store client
		clients.add(client);
		recentClients.addRecent(client);
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

	// method: find client by ID
	public Client findClientById(String clientID) throws EntityNotFoundException {
		// loop thru the array
		for (int i = 0; i < clients.size(); i++) {
			if (clients.get(i) != null && clients.get(i).getClientID().equals(clientID)) {
				return clients.get(i); // return the matching client
			}
		}
		throw new EntityNotFoundException("Client ID not found: " + clientID); // throw exception if not found
	}

	// method: find accommodation by ID
	public Accommodation findAccommodationById(String accommodationID) throws EntityNotFoundException {
		// loop thru the accommodation array
		for (int i = 0; i < accommodations.size(); i++) {
			if (accommodations.get(i) != null &&
					accommodations.get(i).getAccommodationID().equals(accommodationID)) {
				return accommodations.get(i); // return the accommodation if found
			}
		}
		throw new EntityNotFoundException("Accommodation ID not found: " + accommodationID); // if not found, throw the
																								// exception
	}

	// method: find transportation by ID
	public Transportation findTransportationById(String transportID) throws EntityNotFoundException {
		// loop thru the transportation array
		for (int i = 0; i < transports.size(); i++) {
			if (transports.get(i) != null &&
					transports.get(i).getTransportID().equals(transportID)) {
				return transports.get(i); // return the matching transportation object
			}
		}
		throw new EntityNotFoundException("Transportation ID not found: " + transportID); // if not found, throw the
																							// exception
	}

	// method: find trip by ID
	public Trip findTripById(String tripID) throws EntityNotFoundException {
		// loop thru the trip array
		for (int i = 0; i < trips.size(); i++) {
			if (trips.get(i) != null && trips.get(i).getTripID().equals(tripID)) {
				return trips.get(i); // return the trip object if found
			}
		}
		throw new EntityNotFoundException("Trip ID not found: " + tripID); // throw exception if trip not found
	}

	// method: create Trip to store in the system
	public void createTrip(Trip trip) throws InvalidTripDataException, EntityNotFoundException,
			InvalidAccommodationDataException, InvalidTransportDataException {
		String clientID = trip.getClient().getClientID();
		String accommodationID = trip.getAccommodation().getAccommodationID();
		String transportID = trip.getTransportation().getTransportID();

		// find the client object
		Client foundClient = findClientById(clientID); //if not found, an exception will be thrown
		// if no ID is provided, then accommodation and transporation stays null
		Accommodation foundAccommodation = null;
		Transportation foundTransportation = null;
		// find accommodation if ID is not empty
		if (accommodationID != null && !accommodationID.equals("")) {
			foundAccommodation = findAccommodationById(accommodationID);
		}
		// find transportation if ID is not empty
		if (transportID != null && !transportID.equals("")) {
			foundTransportation = findTransportationById(transportID);
		}

		// one of them must exist
		if (foundAccommodation == null && foundTransportation == null) {
			throw new InvalidTripDataException("Trip must include at least an accommodation or a transportation");
		}

	}


	// load all data method

	public void loadAllData(String folderPath) {
		// call the file manager to load clients, accommodations, transportations, trips
		// empty all arrays before loading data
		clients = new ArrayList<>();
		trips = new ArrayList<>();
		accommodations = new ArrayList<>();
		transports = new ArrayList<>();

		//DECIDE HOW TO DO THE RECENT CLIENTS, TRIPS, ...
		recentClients = new RecentList<>();
		recentTrips = new RecentList<>();
		recentAccommodations = new RecentList<>();
		recentTransports = new RecentList<>();;

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
			return trips.get(index).getTotalCost(client, accommodation, transport); // if index is valid, return the full
																				// trip cost

		} catch (InvalidAccommodationDataException e) {
			System.err.println(e.getMessage());
		}
		return -1; // if error in try block
	}

}
