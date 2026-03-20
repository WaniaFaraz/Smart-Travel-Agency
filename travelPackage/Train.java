//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani
//-----------------------------------------------------------------------------

package travelPackage;

import exceptions.InvalidTransportDataException;

public class Train extends Transportation {

	public static final String TRANSPORT_TYPE = "TRAIN";

	private String trainType; // high-speed, regional,...
	private double baseFare; //baseFare of default train ticket before seatClass adjustment

	// Constructors
	public Train(String companyName, String departureCity, String arrivalCity, double baseFare, String trainType) {
		super(companyName, departureCity, arrivalCity);
		setBaseFare(baseFare);
		setTrainType(trainType);
	}

	public Train(Train other) throws InvalidTransportDataException{
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(),other.baseFare, other.trainType);
	}

	public Train() throws InvalidTransportDataException {
		this("no company name", "no departure city", "no arrival city", 30, "no train type");
	}

	//constructor that takes ID for loadAccommodations - so new ID is not generated
	public Train(String ID, String companyName, String departureCity, String arrivalCity, double baseFare, String trainType) {
		super(ID, companyName, departureCity, arrivalCity);
		setBaseFare(baseFare);
		setTrainType(trainType);
	}

	// Accessors and Mutator Methods
	public String getTransportType() {
		return TRANSPORT_TYPE;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}

	// Other Methods
	@Override
	public Transportation copy() throws InvalidTransportDataException {
		return new Train(this);
	}

	@Override
	public String toString() {
		String display;
		String formattedFare = String.format("%.2f", baseFare);
		display = String.join(";", TRANSPORT_TYPE, TRANSPORT_ID, companyName, departureCity, arrivalCity, formattedFare, trainType);
		return display;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (getClass() != obj.getClass()) {
			return false;
		} else {
			Train other = (Train) obj;
			return ((super.equals((Transportation) other) && trainType.equalsIgnoreCase(other.trainType)));
		}
	}

	public double calculateCost(int numberOfDays) {
		double cost = 0;
		cost = baseFare*numberOfDays;
		return cost;
	}

}
