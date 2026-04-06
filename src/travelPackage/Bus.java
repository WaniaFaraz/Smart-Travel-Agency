//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
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
	@Override
	public double getBasePrice(){
		return busFare;
	}
	//convert object to csv format
	@Override
	public String toCsvRow(){
		return toString();
	}
	//method fromCsvRow to reconstruct bus object
	public static Bus fromCsvRow(String csvLine) throws InvalidTransportDataException{
		String[] parts = csvLine.split(";");

		if (parts.length != 7){
			throw new InvalidTransportDataException("Invalid Bus CSV format");
		}
		if (!parts[0].equalsIgnoreCase("BUS")){
			throw new InvalidTransportDataException("CSV row is not a Bus record.");
		}
		String id = parts[1];
		String companyName = parts[2];
		String departureCity = parts[3];
		String arrivalCity = parts[4];
		double busFare = Double.parseDouble(parts[5]);
		int numberOfStops = Integer.parseInt(parts[6]);

		return new Bus(id, companyName, departureCity, arrivalCity, busFare, numberOfStops);
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
