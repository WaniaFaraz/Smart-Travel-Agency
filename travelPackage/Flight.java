//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------

package travelPackage;

import exceptions.InvalidTransportDataException;

public class Flight extends Transportation {
	public static final String TRANSPORT_TYPE = "FLIGHT";
	private double ticketPrice;

	private String airlineName;
	private double luggageAllowance; // unit of kg

	// Constructors
	public Flight(String companyName, String departureCity, String arrivalCity, String airlineName, double luggageAllowance, double ticketPrice) throws InvalidTransportDataException {
		super(companyName, departureCity, arrivalCity);
		setAirlineName(airlineName);
		setLuggageAllowance(luggageAllowance);
		setTicketPrice(ticketPrice);
	}

	public Flight(Flight other) throws InvalidTransportDataException {
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(), other.airlineName,
				other.luggageAllowance,other.ticketPrice);
	}

	public Flight() throws InvalidTransportDataException {
		this("no company", "no departure city", "no arrival city", "no airline", 0, 100);
	}

	// Accessors and Mutators
	public String getTransportType() {
		return TRANSPORT_TYPE;
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

	public void setLuggageAllowance(double luggageAllowance) throws InvalidTransportDataException {
		if(luggageAllowance < 0) {
			throw new InvalidTransportDataException("Luggage Allowance has a minimum of 0kg.");
		}
		this.luggageAllowance = luggageAllowance;
	}
	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	// Other Methods
	@Override
	public String toString() {
		String display;
		String formattedLuggage = String.format("%.2f", luggageAllowance);
		String formattedTicketPrice = String.format("%.2f", ticketPrice);
		display = String.join(";", TRANSPORT_TYPE, TRANSPORT_ID, companyName, departureCity, arrivalCity, formattedTicketPrice, formattedLuggage);
		return display;
	}

	@Override
	public Transportation copy() throws InvalidTransportDataException {
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
		double cost = ticketPrice * numberOfDays;
		return cost;
	}

}
