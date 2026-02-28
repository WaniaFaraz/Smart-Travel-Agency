//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------

package travelPackage;

public class Flight extends Transportation {
	private static String transportationType = "Flight";
	private static final double TICKET_PRICE = 370;

	private String airlineName;
	private double luggageAllowance; // unit of kg

	// Constructors
	public Flight(String companyName, String departureCity, String arrivalCity, String airlineName,
			double luggageAllowance) {
		super(companyName, departureCity, arrivalCity);
		this.airlineName = airlineName;
		this.luggageAllowance = luggageAllowance;
	}

	public Flight(Flight other) {
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(), other.airlineName,
				other.luggageAllowance);
	}

	public Flight() {
		this(null, null, null, null, 0);
	}

	// Accessors and Mutators
	public String getTransportationType() {
		return transportationType;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public double getLuggageAllowance() {
		return luggageAllowance;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public void setLuggageAllowance(double luggageAllowance) {
		this.luggageAllowance = luggageAllowance;
	}

	// Other Methods
	@Override
	public String toString() {
		String display;
		String formattedLuggage = String.format("%.2f", luggageAllowance);
		display = super.toString() + " \n" + airlineName + "\n" + "Max Luggage Allowance: " + formattedLuggage + "kg";
		return display;
	}

	@Override
	public Transportation copy() {
		return new Flight(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Flight other = (Flight) obj;
			return (super.equals((Transportation) other) && (luggageAllowance == other.luggageAllowance)
					&& airlineName.equalsIgnoreCase(other.airlineName));
		}
	}

	public double calculateCost(int numberOfDays) {
		// using number of days doesn't make sense for a flight
		double cost = TICKET_PRICE * numberOfDays;
		return cost;
	}

}
