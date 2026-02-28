//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------
package travelPackage;

public class Bus extends Transportation {

	private static String transportationType = "Bus";
	private static final double BASE_BUS_FARE = 15;
	private static final double SURCHARGE_PER_STOP = 3.50;

	private String busCompany;
	private int numberOfStops;

	// Constructors
	public Bus(String companyName, String departureCity, String arrivalCity, String busCompany, int numberOfStops) {
		super(companyName, departureCity, arrivalCity);
		this.busCompany = busCompany;
		this.numberOfStops = numberOfStops;
	}

	public Bus(Bus other) {
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(), other.busCompany,
				other.numberOfStops);
	}

	public Bus() {
		this(null, null, null, null, 0);
	}

	// Accessor and Mutator Methods
	public String getTransportationType() {
		return transportationType;
	}

	public String getBusCompany() {
		return busCompany;
	}

	public int getNumberOfStops() {
		return numberOfStops;
	}

	public void setBusCompany(String busCompany) {
		this.busCompany = busCompany;
	}

	public void setNumberOfStops(int numberOfStops) {
		this.numberOfStops = numberOfStops;
	}

	// Other Methods
	public Transportation copy() {
		return new Bus(this);
	}

	@Override
	public String toString() {
		String display;
		display = super.toString() + " \n" + busCompany + " - " + numberOfStops + " stops";
		return display;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Bus other = (Bus) obj;
			return (super.equals((Transportation) other) && busCompany.equalsIgnoreCase(other.busCompany)
					&& (numberOfStops == other.numberOfStops));
		}
	}

	public double calculateCost(int numberOfDays) {
		double cost = 0;
		cost = (BASE_BUS_FARE * numberOfDays) + (SURCHARGE_PER_STOP * numberOfStops);
		return cost;
	}

}
