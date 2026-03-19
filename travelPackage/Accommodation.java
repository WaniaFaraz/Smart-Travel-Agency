//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------

package travelPackage;

import exceptions.InvalidAccommodationDataException;

abstract public class Accommodation {
	private static String accommodationIDF = "A"; // F stands for first
	public final String ACCOMMODATION_ID;
	private static int accommodationNumber = 4001;

	protected String name;
	protected String location;
	protected double pricePerNight;
	
	// Constructors
	public Accommodation(String name, String location, double pricePerNight) throws InvalidAccommodationDataException {
		setName(name);
		setLocation(location);
		setPricePerNight(pricePerNight);
		//ID generation
		ACCOMMODATION_ID = accommodationIDF + accommodationNumber;
		accommodationNumber++;
	}

	public Accommodation(Accommodation other) throws InvalidAccommodationDataException {
		this(other.name, other.location, other.pricePerNight);
	}

	public Accommodation() throws InvalidAccommodationDataException {
		this("no name", "No location", 10);
	}

	//Accessors and Mutators
	public String getAccommodationID() {
		return ACCOMMODATION_ID;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public double getPricePerNight() {
		return pricePerNight;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setPricePerNight(double pricePerNight) throws InvalidAccommodationDataException {
		if(pricePerNight <= 0) {
			throw new InvalidAccommodationDataException("Price per night must be greater than $0.");
		}
		this.pricePerNight = pricePerNight;
	}

	// Other Methods
	public abstract Accommodation copy() throws InvalidAccommodationDataException; // required because cannot use copy constructor of this class since abstract

	@Override
	public abstract String toString(); //this object cannot be initialized, so it does not need a toString() method. Its children need to define one

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Accommodation other = (Accommodation) obj;
			return (name.equalsIgnoreCase(other.name) && location.equalsIgnoreCase(other.location)
					&& (pricePerNight == other.pricePerNight));
		}
	}

	public double calculateCost(int numberOfDays) throws InvalidAccommodationDataException{
		if(numberOfDays < 1) {
			throw new InvalidAccommodationDataException("Stay must be at least 1 day.");
		}
		double cost;
		cost = pricePerNight * numberOfDays;
		return cost;
	}

}
