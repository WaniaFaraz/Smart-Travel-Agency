package service;

import clientPackage.Client;

import java.io.IOException;

import exceptions.DuplicateEmailException;
import exceptions.EntityNotFoundException;
import exceptions.InvalidClientDataException;
import exceptions.InvalidTripDataException;

import persistence.AccommodationFileManager;
import persistence.ClientFileManager;
import persistence.TransportFileManager;
import persistence.TripFileManager;

import travelPackage.Accommodation;
import travelPackage.Transportation;
import travelPackage.Trip;

public class SmartTravelService {

  //arrays to store all objects 
	private Client[] clients;
	private Trip[] trips;
	private Accommodation[] accommodations;
	private Transportation[] transportations;
	
	//counters for number of objects stored in each array
	private int clientCount;
	private int tripCount;
	private int accommodationCount;
	private int transportCount;
	
	//constructor 
	public SmartTravelService() {
		clients = new Client[100];
		trips = new Trip[200];
		accommodations = new Accomodation[50];
		transportations = new Transportation[50];
		
		
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

    public Accommodation[] getAccommodations() {
        return accommodations;
    }

    public Transportation[] getTransportations() {
        return transportations;
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
	
	
	//add a client 
	public void addClient(String firstName, String lastName, String emailAddress) throws InvalidClientDataException, DuplicateEmailException {
		
		//check if array is full
		if (clientCount >= clients.length) {
			System.out.println("Client array is full.");
			return;
			
			//check for duplicate email
			for (int i = 0; i< clientCount; i++) {
				if (clients[i] != null && clients[i].getEmailAddress().equalsIgnoreCase(emailAddress)) {
					throw new DuplicateEmailException("A client with this email already exist.");
				}
			}
			
			//create and store client
			clients[clientCount] = new Client(firstName, lastName, emailAddress);
			clientCount++;
		}
		
		//check if client exist
		public boolean clientExists(String clientID) {
			
			for(int i = 0; i < clientCount; i++) {
				if (clients[i] != null && clients[i].getClientID().equals(clientID)) {
					return true;
				}
				
			}
			return false;
		}
		
		//find client by ID
		public Client findClientById(String clientID) throws EntityNotFoundException {
	        for (int i = 0; i < clientCount; i++) {
	            if (clients[i] != null && clients[i].getClientID().equals(clientID)) {
	                return clients[i];
	            }
	        }

	        throw new EntityNotFoundException("Client ID not found: " + clientID);
	    }
		
		//find accommodation by ID
		public Accommodation findAccommodationById(String accommodationID)
	            throws EntityNotFoundException {

	        for (int i = 0; i < accommodationCount; i++) {
	            if (accommodations[i] != null &&
	                accommodations[i].getAccommodationID().equals(accommodationID)) {
	                return accommodations[i];
	            }
	        }

	        throw new EntityNotFoundException("Accommodation ID not found: " + accommodationID);
	    }
		
		//find transportation by ID
		public Transportation findTransportationById(String transportID)
	            throws EntityNotFoundException {

	        for (int i = 0; i < transportCount; i++) {
	            if (transportations[i] != null &&
	                transportations[i].getTransportID().equals(transportID)) {
	                return transportations[i];
	            }
	        }

	        throw new EntityNotFoundException("Transportation ID not found: " + transportID);
	    }
		
		//find trip by ID
		public Trip findTripById(String tripID) throws EntityNotFoundException {
	        for (int i = 0; i < tripCount; i++) {
	            if (trips[i] != null && trips[i].getTripID().equals(tripID)) {
	                return trips[i];
	            }
	        }

	        throw new EntityNotFoundException("Trip ID not found: " + tripID);
	    }
		
		//create Trip
		public void createTrip(String clientID, String accommodationID, String transportID, String destination, int duration, double basePrice) throws InvalidTripDataException, EntityNotFoundException {
			
			//check if array is full
			if (tripCount >= trips.length) {
				System.out.println("Trip array is full.");
				return;
			}
			
			//find the client object
			Client foundClient = findClientByID(clientID);
			
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
			trips[tripCount] = new Trip(foundClient, foundAccommodation, foundTransportation, destination, duration, basePrice);
			tripCount++;
		}
		
		//load all data method
		
		public void loadAllData(String folderPath) {
			try {
				clientCount = ClientFileManager.loadClients(clients, folderPath + "clients.csv");
				
				accommodationCount = AccommodationFileManager.loadAccommodations(accommodations, folderPath + "accommodations.csv");
				
				transportCount = TransportFileManager.loadTransportations(transportations, folderPath + "transports.csv");
				
				tripCount = TripFileManager.loadTrips(trips, folderPath + "trips.csv", clients, clientCount, accommodations, accommodationCount, transportations, transportCount);
				
				System.out.println("All data loaded successfully.");
				
			} catch (IOException e) {
				System.out.println("Error while loading data: "+ e.getMessage());
				
			}
		}
		
		//save all data method
		
		public void saveAllData(String folderPath) {
			try {
				ClientFileManager.saveClients(
	                    clients,
	                    clientCount,
	                    folderPath + "clients.csv"
	            );

	            AccommodationFileManager.saveAccommodations(
	                    accommodations,
	                    accommodationCount,
	                    folderPath + "accommodations.csv"
	            );

	            TransportFileManager.saveTransportations(
	                    transportations,
	                    transportCount,
	                    folderPath + "transports.csv"
	            );

	            TripFileManager.saveTrips(
	                    trips,
	                    tripCount,
	                    folderPath + "trips.csv"
	            );

	            System.out.println("All data saved successfully.");
				
			} catch(IOException e) {
				System.out.println("Error while saving data: "+ e.getMessage());
				
			}
		}
		//calculate total trip cost at a given index
		
		public double calculateTripTotal(int index) {
			
			//check if index is invalid
			if (index < 0 || index >= tripCount || trips[index] == null) {
				return -1;
				}
			
			return trips[index].getTotalCost();
	
		
	}


}
