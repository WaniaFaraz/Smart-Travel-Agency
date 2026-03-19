//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------
package travelPackage;

import exceptions.InvalidAccommodationDataException;

public class Hostel extends Accommodation {

	private static final String ACCOMMODATION_TYPE = "HOSTEL";
	private static final double HOSTEL_DISCOUNT = 0.15;

	private int numOfBeds; // number of shared beds per hotel room

	// Constructors
	public Hostel(String name, String location, double pricePerNight, int numOfBeds) throws InvalidAccommodationDataException {
		super(name, location, pricePerNight);
		setPricePerNight(pricePerNight); //special condition for hostels (but not hotels) : max price $150, so validation happens in the overridden setPricePerNight()
		setNumOfBeds(numOfBeds);
		setPricePerNight(pricePerNight);
	}

	public Hostel(Hostel other) throws InvalidAccommodationDataException {
		this(other.name, other.location, other.pricePerNight, other.numOfBeds);
	}

	public Hostel() throws InvalidAccommodationDataException {
		this("no name", "no location", 1, 0);
	}

	// Accessors and Mutators
	public String getAccommodationType() {
		return ACCOMMODATION_TYPE;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		
		this.numOfBeds = numOfBeds;
	}

	@Override
	public void setPricePerNight(double pricePerNight) throws InvalidAccommodationDataException {
		//sets the price per night for hostels
		//throws InvalidAccommodationDataException if price exceeds max of $150
		if(pricePerNight <= 0) {
			throw new InvalidAccommodationDataException("Price per night must be greater than $0.");
		}
		if(pricePerNight > 150) {
			throw new InvalidAccommodationDataException("Max price for Hostel is $150.");
		}
		this.pricePerNight = pricePerNight;
	}
	

	// Other Methods
	@Override
	public Accommodation copy() throws InvalidAccommodationDataException {
		return new Hostel(this);
	}

	@Override
	public String toString() {
		String display;
		String formattedPrice = String.format("%.2f", pricePerNight);
		display = String.join(";", ACCOMMODATION_TYPE, ACCOMMODATION_ID, name, location, formattedPrice, numOfBeds+"");
		return display;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Hostel other = (Hostel) obj;
			return (super.equals((Accommodation) other) && (numOfBeds == other.numOfBeds));
		}
	}

	@Override
	public double calculateCost(int numberOfDays) throws InvalidAccommodationDataException{
		if(numberOfDays < 1) {
			throw new InvalidAccommodationDataException("Stay must be at least one day.");
		}
		double cost;
		cost = super.calculateCost(numberOfDays) * (1 - HOSTEL_DISCOUNT);
		return cost;
	}

}
