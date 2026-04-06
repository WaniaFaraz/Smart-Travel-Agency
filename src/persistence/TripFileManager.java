/*
* ----------------------------------------------------------------------------------
* Assignment 2
* Written by Wania Faraz 40332781
*            Zahira Atmani 40350242
* ----------------------------------------------------------------------------------
* This class is responsible for handling the persistence of Trip objects in the Smart Travel system.
* It provides methods for saving trips, loading trips from csv file to memory. A trip is always linked to a client
* and optional accommodation and transportation.
*
* 
*
*/


package persistence;

//import classes for file reading and writing
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import exceptions.EntityNotFoundException;
import exceptions.InvalidTripDataException;

import clientPackage.Client;
import travelPackage.*;

public class TripFileManager {
	//save all valid trips into a csv file
	public static void saveTrips(List<Trip> trips, String filePath) throws IOException {

	        // open file for writing
	        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

	        // loop thru all valid trips in the array
	        for (int i = 0; i < trips.size(); i++) {

	            // make sure current element is not null
	            if (trips.get(i) != null) {
					pw.println(trips.get(i));
                  }
	        }
	        pw.close();
	        
	 }
	        //load trips from the file
	        //it will read a csv file and reconstructs the trip objects
	        
	        public static int loadTrips(List<Trip> trips, String filePath, List<Client> clients, List<Accommodation> accommodations, List<Transportation> transports) throws IOException {
	        	
	        	//open the file for reading
	        	BufferedReader br = new BufferedReader(new FileReader(filePath));

	            String line;
	            int count = 0;
	            
	            
	            //read file one line at a time
	            while ((line = br.readLine()) != null) {
	            	
	            	try {
	            		//split the line using ; as a separator
	            		String[] parts = line.split(";");
	            		//a valid trip must have 7 attributes
	            		if (parts.length != 7) {
	            			throw new InvalidTripDataException("Invalid number of fields.");
	            		}
	            		//extract trip attributes from the line
	            		String tripID = parts[0];
	                    String clientID = parts[1];
	                    String accommodationID = parts[2];
	                    String transportID = parts[3];
	                    String destination = parts[4];
	                    int durationDays = Integer.parseInt(parts[5]);
	                    double cost = Double.parseDouble(parts[6]);
	                   
	                    //find the client object
	                    Client foundClient = findClientByID(clients, clientID);
	                    
	                    //find the accommodation object, but it can be optional 
	                    Accommodation foundAccommodation = null;
	                    //if the accommodation object does exist
	                    if (!accommodationID.equals("")) {
	                    	foundAccommodation = findAccommodationByID(accommodations, accommodationID);
	                    }
	                    
	                    //find the transportation object, and it may be optional
	                    Transportation foundTransportation = null;
                        //if the transportation object does exist
	                    if (!transportID.equals("")) {
	                        foundTransportation = findTransportationByID(
	                                transports, transportID);
	                    }
	                    
	                    //if both accommodation and transportation do not exist
	                    if (foundAccommodation == null && foundTransportation == null) {
	                        throw new InvalidTripDataException(
	                                "Trip must have at least an accommodation or a transportation.");
	                    }
	                    
	                    
	                    //create a trip object
						if(foundAccommodation != null || foundTransportation != null) {
							Trip tripCreated = new Trip(
									tripID,
									destination,
									durationDays,
									cost,
									foundClient,
									foundAccommodation,
									foundTransportation
							);
							trips.add(tripCreated);	
						}
						
	                    

	                   
	                    //if trip data is invalid
	            	} catch (InvalidTripDataException e) {
	            		ErrorLogger.log("Invalid trip line: " + line + " | Reason: " + e.getMessage());
	            		//if ID doesnt exist
	            	} catch (EntityNotFoundException e) {
	                    ErrorLogger.log("Missing referenced object in trip line: " + line + " | Reason: " + e.getMessage());
	                //if parsing a number fails
	            	} catch (NumberFormatException e) {
	                    ErrorLogger.log("Number format error in trip line: " + line + " | Reason: " + e.getMessage());
	                //if any unexpected error
	            	} catch (Exception e) {
	                    ErrorLogger.log("Unexpected error while loading trip line: " + line + " | Reason: " + e.getMessage());
						e.printStackTrace();				
	                }
	            	
	            }
	            br.close();//close file after reading
	            return count; //return number of loaded trips
	            
	        }
	        //find client by ID
	        public static Client findClientByID(List<Client> clients, String clientID) throws EntityNotFoundException {
                //search for a client in the client array
	            for (int i = 0; i < clients.size(); i++) {
	                if (clients.get(i) != null && clients.get(i).getClientID().equals(clientID)) {
	                    return clients.get(i); //returns the matching client object
	                }
	            }
				//throw exception if not found
	            throw new EntityNotFoundException("Client ID not found: "+ clientID);
	        }
	        
	        //find accommodation by ID
	        public static Accommodation findAccommodationByID(List<Accommodation> accommodations, String accommodationID) throws EntityNotFoundException {
	        	//search for accomodation thru the accommodation array
	        	for (int i = 0; i < accommodations.size(); i++) {
	                if (accommodations.get(i) != null &&
	                    accommodations.get(i).getAccommodationID().equals(accommodationID)) {
	                    return accommodations.get(i); //return the accommodation object
	                }
	            }
               //throw exception if not found
	            throw new EntityNotFoundException("Accommodation ID not found: " + accommodationID);
	        }
	        //find transportation by ID
	        public static Transportation findTransportationByID(List<Transportation> transports, String transportID) throws EntityNotFoundException {
	        	//search transportation through the transportation array
	        	for (int i = 0; i < transports.size(); i++) {
	                if (transports.get(i) != null &&
	                    transports.get(i).getTransportID().equals(transportID)) {
	                    return transports.get(i);//return the matched transportation
	                }
	            }
                //throw exception if not found
	            throw new EntityNotFoundException("Transportation ID not found: " + transportID);
	        }
	        

}
