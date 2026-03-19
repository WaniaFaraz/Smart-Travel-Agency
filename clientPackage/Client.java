//-----------------------------------------------------------------------------
//Assignment 1
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------

/* 
	Client class.
	Contains the first name, last name, email address of each client
	Methods: getters, setters (except client ID), equals(), toString()
 */
package clientPackage;

import exceptions.InvalidClientDataException;

public class Client {
	private static String clientIDF = "C"; // F stands for first
	private static int clientNumber = 1001; // will be the last 4 digits of the client Id. incremented by 1 each time a
											// client is initialized

	public final String CLIENT_ID; // assigned automatically
	private String firstName;
	private String lastName;
	private String emailAddress;

	// Constructors
	public Client(String firstName, String lastName, String emailAddress) throws InvalidClientDataException {
		// Constructor
		// using the set methods, since they will throw an exception if the arguments are invalid - object will not be created
		setFirstName(firstName);
		setLastName(lastName);
		setEmailAddress(emailAddress);
		// generate ID
		String clientNum = String.format("%04d", clientNumber); // make the last digits take up 4 spaces - more
		// possibility of IDs
		CLIENT_ID = clientIDF + clientNum;
		clientNumber++;
	}

	public Client() throws InvalidClientDataException {
		this("No first name", "No last name", "example@gmail.com");
	}

	public Client(Client other) throws InvalidClientDataException {
		this(other.firstName, other.lastName, other.emailAddress); // should never throw an exception because copying a
																	// valid client only
	}

	// Accessor and Mutator Methods
	public void setFirstName(String firstName) throws InvalidClientDataException {
		// validate first name
		if (firstName == null) {
			throw new InvalidClientDataException("The first name entered must not be an empty string.");
		} else if (firstName.length() > 50) {
			throw new InvalidClientDataException("Exceeds maximum length of 50 characters.");
		} else {
			// no errors - set first name
			this.firstName = firstName;
		}

	}

	public void setLastName(String lastName) throws InvalidClientDataException {
		// validate last name
		if (lastName == null) {
			throw new InvalidClientDataException("The first name entered must not be an empty string.");
		} else if (lastName.length() > 50) {
			throw new InvalidClientDataException("Exceeds maximum length of 50 characters.");
		} else {
			// no errors - set last name
			this.lastName = lastName;
		}
	}

	public void setEmailAddress(String emailAddress) throws InvalidClientDataException {
		// check email address
		if (emailAddress.indexOf('@') == -1 || emailAddress.indexOf('.') == -1) {
			throw new InvalidClientDataException("Invalid email address.");
		}
		if (emailAddress.indexOf(' ') != -1) {
			throw new InvalidClientDataException("Invalid email address. Cannot include spaces.");
		}
		if(emailAddress.length() > 100) {
			throw new InvalidClientDataException("Invalid email address. Exceeds max 100 characters.");
		}
		else {
			this.emailAddress = emailAddress;
		}
	}

	public String getClientID() {
		//simple method name so that getID() can be used for all objects without thinking of class
		return CLIENT_ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	// Other Methods
	@Override
	public String toString() {
		String display;
		display = String.join(";", CLIENT_ID, firstName, lastName, emailAddress);
		return display;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Client other = (Client) obj;
			return (firstName.equalsIgnoreCase(other.firstName) && lastName.equalsIgnoreCase(other.lastName)
					&& this.emailAddress.equals(other.emailAddress));

		}

	}
}
