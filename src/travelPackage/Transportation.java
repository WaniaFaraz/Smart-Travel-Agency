//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------

package travelPackage;

import interfaces.Identifiable;
import interfaces.Printable;
import interfaces.CsvPersistable;

import exceptions.InvalidTransportDataException;

abstract public class Transportation implements Identifiable, CsvPersistable, Comparable<Transportation>, Printable {
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
		this("no company name", "no departure city", "no arrival city");
	}

	//constructor that takes ID for loadAccommodations - so new ID is not generated
	public Transportation(String ID, String companyName, String departureCity, String arrivalCity) {
		TRANSPORT_ID = ID;
		this.companyName = companyName;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;
	}

	// Accessor and Mutator Methods
	public abstract String getTransportType();
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
	//return the id with identifiable interface
	@Override
	public String getId(){
		return getTransportID();
	}
	public abstract double getBasePrice();

	//method to sort transportation object based on base price
	@Override
	public int compareTo(Transportation other){
		return Double.compare(other.getBasePrice(), this.getBasePrice());
	}

	//abstract method for toCsvRow
	@Override
	public abstract String toCsvRow();

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
