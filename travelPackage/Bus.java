//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------
package travelPackage;

import exceptions.InvalidTransportDataException;

public class Bus extends Transportation {

	public static final String TRANSPORT_TYPE = "BUS";
	private static final double SURCHARGE_PER_STOP = 3.50;

	private int numberOfStops;
	private double busFare;


	// Constructors
	public Bus(String companyName, String departureCity, String arrivalCity, double busFareint, int numberOfStops) throws InvalidTransportDataException {
		super(companyName, departureCity, arrivalCity);
		setNumberOfStops(numberOfStops);
		setBusFare(busFare);
	}

	public Bus(Bus other) throws InvalidTransportDataException{
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(), other.busFare, 
				other.numberOfStops);
	}

	public Bus() throws InvalidTransportDataException {
		this("no company", "no departure city", "no arrival city", 1, 10);
	}

	//constructor that takes ID for loadAccommodations - so new ID is not generated
	public Bus(String ID, String companyName, String departureCity, String arrivalCity,  double busFare, int numberOfStops) throws InvalidTransportDataException{
		super(ID, companyName, departureCity, arrivalCity);
		setNumberOfStops(numberOfStops);
		setBusFare(busFare);
	}


	// Accessor and Mutator Methods
	public String getTransportType() {
		return TRANSPORT_TYPE;
	}

	public int getNumberOfStops() {
		return numberOfStops;
	}

	public void setNumberOfStops(int numberOfStops) throws InvalidTransportDataException {
		if (numberOfStops < 1) {
			throw new InvalidTransportDataException("The bus should have at least 1 stop.");
		}
		this.numberOfStops = numberOfStops;
	}

	public void setBusFare(double busFare) {
		this.busFare = busFare;
	}

	// Other Methods
	public Transportation copy() throws InvalidTransportDataException {
		return new Bus(this);
	}

	@Override
	public String toString() {
		String display;
		String formattedFare = String.format("%.2f", busFare);
		display = String.join(";", TRANSPORT_TYPE, TRANSPORT_ID, companyName, departureCity, arrivalCity, formattedFare, numberOfStops+"");
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
			return (super.equals((Transportation) other) && (numberOfStops == other.numberOfStops));
		}
	}

	public double calculateCost(int numberOfDays) {
		double cost = 0;
		cost = (busFare * numberOfDays) + (SURCHARGE_PER_STOP * numberOfStops);
		return cost;
	}

}
