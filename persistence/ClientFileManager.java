package persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import clientPackage.Client;
import exceptions.DuplicateEmailException;
import exceptions.InvalidClientDataException;

public class ClientFileManager {
	
	public static void saveClients(Client[] clients, int clientCount, String filepath) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(filepath));
				
		for (int i = 0; i < clientCount; i++) {
			if (clients[i] != null) {
				pw.println(clients[i].getClientID() + ";" +
			               clients[i].getFirstName() + ";" +
						   clients[i].getLastName() + ";" +
			               clients[i].getEmailAddress());
			}
		}
		pw.close();
	}
	
	// load clients from the file and return how many valid ones were loaded
	public static int loadClients(Client[] clients, String filepath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		String line;
		int count = 0;
		
		while ((line = br.readLine()) != null) {
			try {
				String[] parts = line.split(";"); //separate attribute by ;
				
				
				//need to make sure the object has 4 attributes
				if (parts.length != 4) {
					throw new InvalidClientDataException("Invalid number of fields.");
				}
				String firstName = parts[1];
				String lastName = parts[2];
				String emailAddress = parts[3];
				
				if (count >= clients.length) {
					ErrorLogger.log("Client array is full. Remaining lines were skipped.");
					break;
				}

				//check for duplicate email
				for (int i = 0; i< clients.length; i++) {
					if (clients[i] != null && clients[i].getEmailAddress().equalsIgnoreCase(emailAddress)) {
						throw new DuplicateEmailException("A client with this email already exist.");
					}
				}
				Client c = new Client(firstName, lastName, emailAddress);
				clients[count] = c;
				count++;
			}
			catch (InvalidClientDataException e) {
				ErrorLogger.log("Invalid client line: "+ line + "| Reason: "+ e.getMessage());
			}
			catch (DuplicateEmailException e) {
				ErrorLogger.log("Invalid client line: " + line + "| Reason: " + e.getMessage());
			}
			catch (Exception e) {
				ErrorLogger.log("Unexpected error while loading client line: "+ line + "| Reason: "+ e.getMessage());
				
			}
			
		}
		br.close();
		return count;
	}

}
