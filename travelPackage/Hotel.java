//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------
package travelPackage;

import exceptions.InvalidAccommodationDataException;

public class Hotel extends Accommodation {

	private static final String ACCOMMODATION_TYPE = "HOTEL";

	private double starRating;

	// Constructors
	public Hotel(String name, String location, double pricePerNight, double starRating) throws InvalidAccommodationDataException {
		super(name, location, pricePerNight);
		setStarRating(starRating);
	}

	public Hotel(Hotel other) throws InvalidAccommodationDataException{
		this(other.getName(), other.getLocation(), other.getPricePerNight(), other.starRating);
	}

	public Hotel() throws InvalidAccommodationDataException {
		this(null, null, 0, 0);
	}

	//constructor that takes ID - for loadAccommodations
	public Hotel(String ID, String name, String location, double pricePerNight, double starRating) throws InvalidAccommodationDataException {
		super(ID, name, location, pricePerNight);
		setStarRating(starRating);
		
	}

	// Accessors and Mutators
	public String getAccommodationType() {
		return ACCOMMODATION_TYPE;
	}

	public double getStarRating() {
		return starRating;
	}

	public void setStarRating(double starRating) throws InvalidAccommodationDataException {
		if(starRating < 1 || starRating > 5) {
			throw new InvalidAccommodationDataException("Star rating must be between 1 and 5 stars.");
		}
		this.starRating = starRating;
	}

	// Other Methods
	@Override
	public Accommodation copy() throws InvalidAccommodationDataException {
		return new Hotel(this);
	}

	@Override
	public String toString() {
		String display;
		String formattedPrice = String.format("%.2f", pricePerNight);
		display = String.join(";", ACCOMMODATION_TYPE, ACCOMMODATION_ID, name, location, formattedPrice, starRating+"" );
		return display;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Hotel other = (Hotel) obj;
			return (super.equals((Accommodation) other) && (starRating == starRating));
		}
	}

	@Override
	public double calculateCost(int numberOfDays) throws InvalidAccommodationDataException {
		if(numberOfDays < 1) {
			throw new InvalidAccommodationDataException("Stay must be at least 1 day.");
		}
		double cost;
		cost = super.calculateCost(numberOfDays);
		return cost;
	}

}
