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
	//adjustment to base fare depending on seat class
	private static final double ECONOMY_FEE = 0.15;
	private static final double BUSINESS_FEE = 0.30;
	private static final double SLEEPER_FEE = 0.50;

	private String trainType; // high-speed, regional,...
	private String seatClass; // Economy, Business, First
	private double baseFare; //baseFare of default train ticket before seatClass adjustment

	// Constructors
	public Train(String companyName, String departureCity, String arrivalCity, String trainType, String seatClass, double baseFare) throws InvalidTransportDataException{
		super(companyName, departureCity, arrivalCity);
		setBaseFare(baseFare);
		setTrainType(trainType);
		setSeatClass(seatClass);
	}

	public Train(Train other) throws InvalidTransportDataException{
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(), other.trainType,
				other.seatClass, other.baseFare);
	}

	public Train() throws InvalidTransportDataException {
		this("no company name", "no departure city", "no arrival city", "no train type", "economy", 30);
	}

	// Accessors and Mutator Methods
	public String getTransportType() {
		return TRANSPORT_TYPE;
	}

	public String getTrainType() {
		return trainType;
	}

	public String getSeatClass() {
		return seatClass;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public void setBaseFare(double baseFare) {
		this.baseFare = baseFare;
	}

	public void setSeatClass(String seatClass) throws InvalidTransportDataException{
		if(seatClass.equalsIgnoreCase("economy")) {
			this.seatClass = "Economy";
		}
		else if(seatClass.equalsIgnoreCase("business")) {
			this.seatClass = "Business";
		}
		else if(seatClass.equalsIgnoreCase("sleeper")) {
			this.seatClass = "Sleeper";
		}
		else {
			throw new InvalidTransportDataException("Invalid seat class.");
		}
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
			return ((super.equals((Transportation) other) && trainType.equalsIgnoreCase(other.trainType)
					&& seatClass.equalsIgnoreCase(other.seatClass)));
		}
	}

	public double calculateCost(int numberOfDays) {
		double cost = 0;
		if (seatClass.equalsIgnoreCase("Economy")) {
			cost = baseFare * (1 + ECONOMY_FEE) * numberOfDays;
		} else if (seatClass.equalsIgnoreCase("Business")) {
			cost = baseFare * (1 + BUSINESS_FEE) * numberOfDays;
		} else if (seatClass.equalsIgnoreCase("Sleeper")) {
			cost = baseFare * (1 + SLEEPER_FEE) * numberOfDays;
		} else {
			cost = 1000; // no class selected - random price...
		}
		return cost;
	}

}
