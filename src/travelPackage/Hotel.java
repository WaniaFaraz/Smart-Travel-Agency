//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------
package travelPackage;

public class Hotel extends Accommodation {

	private static String accommodationType = "Hotel";
	private static final double HOTEL_SERVICE_SURCHARGE = 0.1;

	private double starRating;

	// Constructors
	public Hotel(String name, String location, double pricePerNight, double starRating) {
		super(name, location, pricePerNight);
		this.starRating = starRating;
	}

	public Hotel(Hotel other) {
		this(other.getName(), other.getLocation(), other.getPricePerNight(), other.starRating);
	}

	public Hotel() {
		this(null, null, 0, 0);
	}

	// Accessors and Mutators
	public String getAccommodationType() {
		return accommodationType;
	}

	public double getStarRating() {
		return starRating;
	}

	public void setStarRating(double starRating) {
		this.starRating = starRating;
	}

	// Other Methods
	@Override
	public Accommodation copy() {
		return new Hotel(this);
	}

	@Override
	public String toString() {
		String display;
		display = super.toString() + "\n" + starRating + " stars";
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
	public double calculateCost(int numberOfDays) {
		double cost;
		cost = super.calculateCost(numberOfDays) * (1 + HOTEL_SERVICE_SURCHARGE);
		return cost;
	}

}
