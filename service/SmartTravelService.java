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

//
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
	
	
	// method: add a client to the system
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
		public Accommodation findAccommodationById(String accommodationID)
	            throws EntityNotFoundException {
           //loop thru the accommodation array
	        for (int i = 0; i < accommodationCount; i++) {
	            if (accommodations[i] != null &&
	                accommodations[i].getAccommodationID().equals(accommodationID)) {
	                return accommodations[i]; //return the accommodation if found
	            }
	        }

	        throw new EntityNotFoundException("Accommodation ID not found: " + accommodationID); //if not found, throw the exception
	    }
		
		//method: find transportation by ID
		public Transportation findTransportationById(String transportID)
	            throws EntityNotFoundException {
            //loop thru the transportation array
	        for (int i = 0; i < transportCount; i++) {
	            if (transportations[i] != null &&
	                transportations[i].getTransportID().equals(transportID)) {
	                return transportations[i]; //return the matching transportation object
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
			tripCount++; //increment by 1
		}
		
		//load all data method
		
		public void loadAllData(String folderPath) {
			//call the file manager to load clients, accommodations, transportations, trips
			try {
				clientCount = ClientFileManager.loadClients(clients, folderPath + "clients.csv");
				
				accommodationCount = AccommodationFileManager.loadAccommodations(accommodations, folderPath + "accommodations.csv");
				
				transportCount = TransportFileManager.loadTransportations(transportations, folderPath + "transports.csv");
				
				tripCount = TripFileManager.loadTrips(trips, folderPath + "trips.csv", clients, clientCount, accommodations, accommodationCount, transportations, transportCount);
				
				System.out.println("All data loaded successfully."); //print the message once its done
				
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
                // call upon manager file to save data into the csv files for accomodations
	            AccommodationFileManager.saveAccommodations(
	                    accommodations,
	                    accommodationCount,
	                    folderPath + "accommodations.csv"
	            );
                // call upon manager file to save data into the csv files for transportations
	            TransportFileManager.saveTransportations(
	                    transportations,
	                    transportCount,
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
			
			return trips[index].getTotalCost(); //if index is valid, return the full trip cost
	
		
	}


}
