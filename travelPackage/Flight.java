//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------

package travelPackage;

import exceptions.InvalidTransportDataException;

public class Flight extends Transportation {
	public static final String TRANSPORT_TYPE = "FLIGHT";
	private double ticketPrice;

	private double luggageAllowance; // unit of kg

	// Constructors
	public Flight(String companyName, String departureCity, String arrivalCity, double ticketPrice, double luggageAllowance) throws InvalidTransportDataException {
		super(companyName, departureCity, arrivalCity);
		setLuggageAllowance(luggageAllowance);
		setTicketPrice(ticketPrice);
	}

	public Flight(Flight other) throws InvalidTransportDataException {
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(),other.ticketPrice, other.luggageAllowance);
	}

	public Flight() throws InvalidTransportDataException {
		this("no company", "no departure city", "no arrival city", 100, 0);
	}

	//constructor that takes ID for loadAccommodations - so new ID is not generated
		public Flight(String ID, String companyName, String departureCity, String arrivalCity, double ticketPrice, double luggageAllowance) throws InvalidTransportDataException {
			super(ID, companyName, departureCity, arrivalCity);
			setTicketPrice(ticketPrice);
			setLuggageAllowance(luggageAllowance);
		}


	// Accessors and Mutators
	public String getTransportType() {
		return TRANSPORT_TYPE;
	}

	public double getLuggageAllowance() {
		return luggageAllowance;
	}

	public double getTicketPrice() {
		return ticketPrice;
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
			return (super.equals((Transportation) other) && (luggageAllowance == other.luggageAllowance));
		}
	}

	public double calculateCost(int numberOfDays) {
		// using number of days doesn't make sense for a flight
		double cost = ticketPrice * numberOfDays;
		return cost;
	}

}
