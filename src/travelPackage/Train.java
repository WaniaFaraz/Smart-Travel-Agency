//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
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

	public Train(Train other){
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(),other.baseFare, other.trainType);
	}

	public Train(){
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
	@Override
	public double getBasePrice(){
		return baseFare;
	}
	//convert the object to csv format
	@Override
	public String toCsvRow(){
		return toString();
	}
	//method fromCsvRow
	public static Train fromCsvRow(String csvLine) throws InvalidTransportDataException{
		String[] parts = csvLine.split(";");

		if (parts.length != 7){
			throw new InvalidTransportDataException("Invalid Train CSV format.");
		}
		if (!parts[0].equalsIgnoreCase("TRAIN")){
			throw new InvalidTransportDataException("CSV row is not a Train record");
		}
		String id = parts[1];
		String companyName = parts[2];
		String departureCity = parts[3];
		String arrivalCity = parts[4];
		double baseFare = Double.parseDouble(parts[5]);
		String trainType = parts[6];

		return new Train(id, companyName, departureCity, arrivalCity, baseFare, trainType);
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
