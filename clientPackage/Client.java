//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------

/* 
	Client class.
	Contains the first name, last name, email address of each client
	Methods: getters, setters (except client ID), equals(), toString()
 */
package clientPackage;

public class Client {
	private static String clientIDFF = "C1001"; // FF stands for first five
	private static int clientNumber = 0; // will be the last 4 digits of the client Id. incremented by 1 each time a
											// client is initialized

	public final String CLIENT_ID; // assigned automatically
	private String firstName;
	private String lastName;
	private String emailAddress;

	// Constructors
	public Client(String firstName, String lastName, String emailAddress) {
		//Constructor
		//Validate parameters
		try {
			//check names
			if(firstName == null || lastName == null) 
				throw new InvalidClientDataException("The first name and last name entered must not be an empty string.");
			if(firstName.length() > 50 || lastName.length() > 50)
				throw new InvalidClientDataException("Exceeds maximum length of 50 characters.");
			//check email address
			if(emailAddress.indexOf('@') == -1 || emailAddress.indexOf('.') == -1)
				throw new InvalidClientDataException("Invalid email address.");
			if(emailAddress.indexOf(' ') != -1)
				throw new InvalidClientDataException("Invalid email address. Cannot include spaces.");

			//no errors - create client
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailAddress = emailAddress;
			//generate ID
			String clientNum = String.format("%04d", clientNumber); // make the last digits take up 4 spaces - more possibility of IDs												
			CLIENT_ID = clientIDFF + clientNum;
			clientNumber++;
		}
		catch(InvalidClientDataException e) {
			System.err.println("Client not created due to invalid arguments.");
		}
		
	}

	public Client() {
		this(null, null, null);
	}

	public Client(Client other) {
		this(other.firstName, other.lastName, other.emailAddress);
	}

	// Accessor and Mutator Methods
	public String getClientID() {
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

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	// Other Methods
	@Override
	public String toString() {
		String display;
		display = "CLIENT ID: " + CLIENT_ID + "\n" + firstName + " " + lastName + " - " + emailAddress;
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
