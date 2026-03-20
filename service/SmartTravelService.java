/*
* -----------------------------------------------------------------------------------
* Assignment 2
* Written by Wania Faraz 
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

import exceptions.*;

import persistence.AccommodationFileManager;
import persistence.ClientFileManager;
import persistence.TransportFileManager;
import persistence.TripFileManager;

import travelPackage.*;

//
public class SmartTravelService {

  //arrays to store all objects 
	private Client[] clients;
	private Trip[] trips;
	public Accommodation[] hotels;
	public Accommodation[] hostels;
	public Accommodation[][] accommodations = {hotels, hostels};
	public Accommodation[] unsortedAccommodations;
	public Transportation[] flights;
	public Transportation[] trains;
	public Transportation[] buses;
	public Transportation[][] transportations = {flights, trains, buses};
	public Transportation[] unsortedTransports;
	
	//counters for number of objects stored in each array
	private int clientCount;
	private int tripCount;
	private int accommodationCount;
	private int transportCount;
	private int flightCount;
	private int trainCount;
	private int busCount;
	private int hotelCount;
	private int hostelCount;
		
	//constructor 
	public SmartTravelService() {
		clients = new Client[100];
		trips = new Trip[200];
		flights = new Flight[50];
		trains = new Train[50];
		buses = new Bus[50];
		hotels = new Hostel[50];
		hostels = new Hostel[50];
		
		clientCount = 0;
		tripCount = 0;
		accommodationCount = 0;
		transportCount = 0;
	}
	//getters 
	public Client[] getClients() {
        return clients;
    }

    public Trip[] getTrips() {
        return trips;
    }

    public Accommodation[][] getAccommodations() {
        return accommodations;
    }

	public Accommodation[] getHotels() {
		return hotels;
	}

	public Accommodation[] getHostels() {
		return hostels;
	}

	public Transportation[][] getTransportations() {
			return transportations;
		}

	public Transportation[] getFlights() {
		return flights;
	}

	public Transportation[] getTrains() {
		return trains;
	}

	public Transportation[] getBuses() {
		return buses;
	}

    public int getClientCount() {
        return clientCount;
    }

    public int getTripCount() {
        return tripCount;
    }

    public int getAccommodationCount() {
        return accommodationCount;
    }

    public int getTransportCount() {
        return transportCount;
    }

	public void setClientCount(int clientCount) {
		//for when a client is removed, and is decremented in the driver
		this.clientCount = clientCount;
	}

	public void setTripCount(int tripCount) {
		//for when trip count has been changed and needs updating
		this.tripCount = tripCount;
	}
	
	
	// method: add a client to the system
	public void addClient(Client client) throws InvalidClientDataException, DuplicateEmailException {
		//check if array is full
		if (clientCount >= clients.length) {
			System.out.println("Client array is full.");
			return;
		}	
			//check for duplicate email
			for (int i = 0; i< clients.length; i++) {
				if (clients[i] != null && clients[i].getEmailAddress().equalsIgnoreCase(client.getEmailAddress())) {
					throw new DuplicateEmailException("A client with this email already exist.");
				}
			}
			//create and store client
			clients[clientCount] = client;
			clientCount++; //increment by 1
	}
	
	//method: check if client exist
	public boolean clientExists(String clientID) {
		//loop thru the client array
		for(int i = 0; i < clientCount; i++) {
			if (clients[i] != null && clients[i].getClientID().equals(clientID)) {
				return true; //return true if found
			}	
		}
		return false; //return false if client doesnt exist
	}
	
	//method: find client by ID
	public Client findClientById(String clientID) throws EntityNotFoundException {
		//loop thru the array
		for (int i = 0; i < clientCount; i++) {
			if (clients[i] != null && clients[i].getClientID().equals(clientID)) {
				return clients[i]; //return the matching client
			}
		}
		throw new EntityNotFoundException("Client ID not found: " + clientID); //throw exception if not found
	}
		
	//method: find accommodation by ID
	public Accommodation findAccommodationById(String accommodationID) throws EntityNotFoundException {
		//loop thru the accommodation array
		for (int i = 0; i < accommodationCount; i++) {
			if (accommodations[i] != null &&
				unsortedAccommodations[i].getAccommodationID().equals(accommodationID)) {
				return unsortedAccommodations[i]; //return the accommodation if found
			}
		}
		throw new EntityNotFoundException("Accommodation ID not found: " + accommodationID); //if not found, throw the exception
	}
		
	//method: find transportation by ID
	public Transportation findTransportationById(String transportID) throws EntityNotFoundException {
		//loop thru the transportation array
		for (int i = 0; i < transportCount; i++) {
			if (transportations[i] != null &&
				unsortedTransports[i].getTransportID().equals(transportID)) {
				return unsortedTransports[i]; //return the matching transportation object
			}
		}
		throw new EntityNotFoundException("Transportation ID not found: " + transportID); //if not found, throw the exception
	}
		
	//method: find trip by ID
	public Trip findTripById(String tripID) throws EntityNotFoundException {
		//loop thru the trip array
		for (int i = 0; i < tripCount; i++) {
			if (trips[i] != null && trips[i].getTripID().equals(tripID)) {
				return trips[i]; //return the trip object if found
			}
		}
		throw new EntityNotFoundException("Trip ID not found: " + tripID); //throw exception if trip not found
	}
	
	//method: create Trip to store in the system
	public void createTrip(String clientID, String accommodationID, String transportID, String destination, int duration, double basePrice) throws InvalidTripDataException, EntityNotFoundException, InvalidAccommodationDataException, InvalidTransportDataException {
		//check if array is full
		if (tripCount >= trips.length) {
			System.out.println("Trip array is full.");
			return;
		}
		//find the client object
		Client foundClient = findClientById(clientID);
		//if no ID is provided, then accommodation and transporation stays null
		Accommodation foundAccommodation = null;
		Transportation foundTransportation = null;
		//find accommodation if ID is not empty
		if (accommodationID != null && !accommodationID.equals("")) {
			foundAccommodation = findAccommodationById(accommodationID);
		}
		//find transportation if ID is not empty
		if (transportID != null && !transportID.equals("")) {
			foundTransportation = findTransportationById(transportID);
		}
		
		//one of them must exist
		if (foundAccommodation == null && foundTransportation == null) {
			throw new InvalidTripDataException("Trip must include at least an accommodation or a transportation");
		}
		
		//create and store trip
		trips[tripCount] = new Trip(destination, duration, basePrice, foundClient, foundAccommodation, foundTransportation );
		tripCount++; //increment by 1
	}
		
	//load all data method
	
	public void loadAllData(String folderPath) {
		//call the file manager to load clients, accommodations, transportations, trips
		try {
			clientCount = ClientFileManager.loadClients(clients, folderPath + "clients.csv");
			
			accommodationCount = AccommodationFileManager.loadAccommodations(unsortedAccommodations, folderPath + "accommodations.csv");
			
			transportCount = TransportFileManager.loadTransportations(unsortedTransports, folderPath + "transports.csv");
			
			tripCount = TripFileManager.loadTrips(trips, folderPath + "trips.csv", clients, clientCount, unsortedAccommodations, accommodationCount, unsortedTransports, transportCount);
			
			System.out.println("All data loaded successfully."); //print the message once its done

			sortAccommodations(); //sort unsortedAccommodations into hotels and hostels
			sortTransportations(); //sort unsortedTransportations into hotels and hostels
			
		} catch (IOException e) { //
			System.out.println("Error while loading data: "+ e.getMessage());
			
		}

	}

	//save all data method
	
	public void saveAllData(String folderPath) {

		// call upon manager file to save data into the csv files for clients
		try {
			ClientFileManager.saveClients(
					clients,
					clientCount,
					folderPath + "clients.csv"
			);
			// call upon manager file to save data into the csv files for hotels and hostels
			AccommodationFileManager.saveAccommodations(
					hotels,
					hotelCount,
					folderPath + "accommodations.csv"
			);
			AccommodationFileManager.saveAccommodations(
					hostels,
					hostelCount,
					folderPath + "accommodations.csv"
			);
			// call upon manager file to save data into the csv files for flights, trains and buses
			TransportFileManager.saveTransportations(
					flights,
					flightCount,
					folderPath + "transports.csv"
			);
			TransportFileManager.saveTransportations(
					trains,
					trainCount,
					folderPath + "transports.csv"
			);
			TransportFileManager.saveTransportations(
					buses,
					busCount,
					folderPath + "transports.csv"
			);
			//// call upon manager file to save data into the csv files for trips
			TripFileManager.saveTrips(
					trips,
					tripCount,
					folderPath + "trips.csv"
			);

			System.out.println("All data saved successfully."); //print message once its done
			
		} catch(IOException e) {
			System.out.println("Error while saving data: "+ e.getMessage()); //print a message if there's an error
			
		}
	}
	//calculate total trip cost at a given index
	
	public double calculateTripTotal(int index) {
		
		//check if index is invalid
		if (index < 0 || index >= tripCount || trips[index] == null) {
			return -1; //returns -1 if index is not valid
		}
		//get client, accommodation and transport of the trip
		Client client = trips[index].getClient();
		Transportation transport = trips[index].getTransportation();
		Accommodation accommodation = trips[index].getAccommodation();
		try {
			return trips[index].getTotalCost(client, accommodation, transport); //if index is valid, return the full trip cost

		}catch(InvalidAccommodationDataException e) {
			System.err.println(e.getMessage());
		}
		return -1; //if error in try block	
	}

	private void sortAccommodations() {
		//sort the accommodations from the unsortedAccommodations array into the hotels and hostels arrays
		//will be called at the end of the loadAllData() method
		//accommodations array will be automatically updated - (contains hotels and hostels arrays)
		hotelCount = 0; //refresh all counts to 0 before incrementing
		hostelCount = 0;
		accommodationCount = 0;
		for(int i = 0; i < unsortedAccommodations.length; i++) {
			
			Accommodation acc = unsortedAccommodations[i];
			if(acc == null) continue;
			else if (acc.getAccommodationType().equals("HOTEL")) {
				hotels[hotelCount] = acc;
				hostelCount++;
			}
			else if(acc.getAccommodationType().equals("HOSTEL")) {
				hostels[hostelCount] = acc;
				hostelCount++;
			}
			accommodationCount++;
		}
	}

	private void sortTransportations() {
		//sort the unsortedTransportations array into the flights, trains, and buses arrays
		//method is called at the end of the loadAllData() method
		//transportations array will be automatically updated - (contains flights, trains, and buses arrays)
		transportCount = 0; //refresh to 0 before starting to count
		flightCount = 0;
		trainCount = 0;
		busCount = 0;
		for(int i = 0; i < unsortedTransports.length; i++) {
			Transportation transport = unsortedTransports[i];
			if(transport == null) continue;
			else if(transport.getTransportType().equals("FLIGHT")) {
				flights[flightCount] = transport;
				flightCount++;
			}
			else if(transport.getTransportType().equals("TRAIN")) {
				trains[trainCount] = transport;
				trainCount++;
			}
			else if(transport.getTransportType().equals("BUS")){
				buses[busCount] = transport;
				busCount++;
			}
			transportCount++;
		}
	}


}
