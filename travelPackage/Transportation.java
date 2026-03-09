//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------

package travelPackage;

abstract public class Transportation {
	private static String transportIDFF = "TR3001"; // FF stands for first five
	public final String TRANSPORT_ID; // assigned automatically
	private static int transportNumber = 0;

	private String companyName;
	private String departureCity;
	private String arrivalCity;

	// Constructors
	public Transportation(String companyName, String departureCity, String arrivalCity) {
		this.companyName = companyName;
		this.departureCity = departureCity;
		this.arrivalCity = arrivalCity;

		String transportNum = String.format("%04d", transportNumber);
		TRANSPORT_ID = transportIDFF + transportNum;
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
	public String toString() {
		String display;
		display = "TRANSPORT ID: " + TRANSPORT_ID + "\n" + companyName + ": " + departureCity + " to " + arrivalCity;
		return display;
	}

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

	public abstract Transportation copy();
}
