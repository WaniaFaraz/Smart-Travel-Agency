//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------

package travelPackage;

abstract public class Accommodation {
	private static String accommodationIDFF = "A4001"; // FF stands for first five
	public final String ACCOMMODATION_ID;
	private static int accommodationNumber = 0;

	private String name;
	private String location;
	private double pricePerNight;

	// Constructors
	public Accommodation(String name, String location, double pricePerNight) {
		this.name = name;
		this.location = location;
		this.pricePerNight = pricePerNight;

		String accommodationNum = String.format("%04d", accommodationNumber);
		ACCOMMODATION_ID = accommodationIDFF + accommodationNum;
		accommodationNumber++;
	}

	public Accommodation(Accommodation other) {
		this(other.name, other.location, other.pricePerNight);
	}

	public Accommodation() {
		this(null, null, 0);
	}

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

	public void setPricePerNight(double pricePerNight) {
		this.pricePerNight = pricePerNight;
	}

	// Other Methods
	public abstract Accommodation copy(); // required because cannot use copy constructor of this class since abstract

	@Override
	public String toString() {
		String display;
		String formattedPricePerNight = String.format("%.2f", pricePerNight);
		display = "ACCOMMODATION ID: " + ACCOMMODATION_ID + "\n" + name + "\n" + "Location: " + location
				+ "\nPrice per night: $" + formattedPricePerNight;
		return display;
	}

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

	public double calculateCost(int numberOfDays) {
		double cost;
		cost = pricePerNight * numberOfDays;
		return cost;
	}

}
