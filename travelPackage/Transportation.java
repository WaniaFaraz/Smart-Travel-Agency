//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------

package travelPackage;

import exceptions.InvalidTransportDataException;

abstract public class Transportation {
	private static String transportIDF = "TR"; // F stands for first
	public final String TRANSPORT_ID; // assigned automatically
	private static int transportNumber = 3001;

	protected String companyName;
	protected String departureCity;
	protected String arrivalCity;

	// Constructors
	public Transportation(String companyName, String departureCity, String arrivalCity) {
		this.companyName = companyName;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;

		TRANSPORT_ID = transportIDF + transportNumber;
		transportNumber++;
	}

	public Transportation(Transportation other) {
		this(other.companyName, other.departureCity, other.arrivalCity);
	}

	public Transportation() {
		this(null, null, null);
	}

	// Accessor and Mutator Methods
	public String getTransportID() {
		return TRANSPORT_ID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public String getArrivalCity() {
		return arrivalCity;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}

	// Other Methods
	public abstract double calculateCost(int numberOfDays); // required because cannot use copy constructor of this
															// class since abstract

	@Override
	public abstract String toString(); //Transportation cannot be intitialized. no need for toString()

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (this.getClass() != obj.getClass()) {
			return false;
		} else {
			Transportation other = (Transportation) obj;
			return (companyName.equalsIgnoreCase(other.companyName)
					&& departureCity.equalsIgnoreCase(other.departureCity)
					&& arrivalCity.equalsIgnoreCase(other.arrivalCity));

		}
	}

	public abstract Transportation copy() throws InvalidTransportDataException;
}
