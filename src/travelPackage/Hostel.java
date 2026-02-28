//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------
package travelPackage;

public class Hostel extends Accommodation {

	private static String accommodationType = "Hostel";
	private static final double HOSTEL_DISCOUNT = 0.15;

	private int numOfBeds; // number of shared beds per hotel room

	// Constructors
	public Hostel(String name, String location, double pricePerNight, int numOfBeds) {
		super(name, location, pricePerNight);
		this.numOfBeds = numOfBeds;
	}

	public Hostel(Hostel other) {
		this(other.getName(), other.getLocation(), other.getPricePerNight(), other.numOfBeds);
	}

	public Hostel() {
		this(null, null, 0, 0);
	}

	// Accessors and Mutators
	public String getAccommodationType() {
		return accommodationType;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

	// Other Methods
	@Override
	public Accommodation copy() {
		return new Hostel(this);
	}

	@Override
	public String toString() {
		String display;
		display = super.toString() + "\n" + numOfBeds + " beds";
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
	public double calculateCost(int numberOfDays) {
		double cost;
		cost = super.calculateCost(numberOfDays) * (1 - HOSTEL_DISCOUNT);
		return cost;
	}

}
