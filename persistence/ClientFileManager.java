/*
* ----------------------------------------------------------------------------------
* Assignment 2
* Written by Wania Faraz
*            Zahira Atmani 40350242
* ----------------------------------------------------------------------------------
* This class is responsible for handling the persistence of Client objects using CSV files.
* It provides methods for saving client data from an array to a file, loading client data from a file to an array.
*
*
*/

package persistence;

//import classes needed for input and output
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//import Client class and custom exceptions
import clientPackage.Client;
import exceptions.DuplicateEmailException;
import exceptions.InvalidClientDataException;



public class ClientFileManager {
	
	//save all valid clients from the array into a csv file
    //each client is written in a single line, separated by a semi colon ;
	public static void saveClients(Client[] clients, int clientCount, String filepath) throws IOException {
		//open file for writing
		PrintWriter pw = new PrintWriter(new FileWriter(filepath));
		//loop thru all valid clients in the array		
		for (int i = 0; i < clientCount; i++) {
			//make sure the current element is not null
			if (clients[i] != null) {
				//write one client per line
				pw.println(clients[i].getClientID() + ";" +
			               clients[i].getFirstName() + ";" +
						   clients[i].getLastName() + ";" +
			               clients[i].getEmailAddress());
			}
		}
		pw.close(); //close file after writing
	}
	
	// load clients from the file and return how many valid ones were loaded
	public static int loadClients(Client[] clients, String filepath) throws IOException {
		//open file for reading
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line;
		int count = 0;
		//read file one line at a time
		while ((line = br.readLine()) != null) {
			try {
				//split the line into fields using ; as a separator
				String[] parts = line.split(";"); //separate attribute by ;
				
				
				//need to make sure the object has 4 attributes
				if (parts.length != 4) {
					throw new InvalidClientDataException("Invalid number of fields.");
				}
				//extract client data from the split line
				String firstName = parts[1];
				String lastName = parts[2];
				String emailAddress = parts[3];

				//prevent array from overflowing
				if (count >= clients.length) {
					ErrorLogger.log("Client array is full. Remaining lines were skipped.");
					break;
				}

				//check for duplicate email before creating a new object
				for (int i = 0; i< clients.length; i++) {
					if (clients[i] != null && clients[i].getEmailAddress().equalsIgnoreCase(emailAddress)) {
						throw new DuplicateEmailException("A client with this email already exist.");
					}
				}
				//create a new client object using valid data
				Client c = new Client(firstName, lastName, emailAddress);
				//store object in the array and increment count
				clients[count] = c;
				count++;
			}
			//catch invalid client data and log it
			catch (InvalidClientDataException e) {
				ErrorLogger.log("Invalid client line: "+ line + "| Reason: "+ e.getMessage());
			}
				//catch duplicate email issue and log it
			catch (DuplicateEmailException e) {
				ErrorLogger.log("Invalid client line: " + line + "| Reason: " + e.getMessage());
			}
				//catch any unexpected error and log it
			catch (Exception e) {
				ErrorLogger.log("Unexpected error while loading client line: "+ line + "| Reason: "+ e.getMessage());
				
			}
			
		}
		br.close(); //close file after reading
		return count; //return the number of loaded clients
	}

}
