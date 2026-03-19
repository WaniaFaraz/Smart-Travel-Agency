//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------

package travelPackage;

public class Train extends Transportation {

	private static String TRANSPORT_TYPE = "Train";
	private static final double ECONOMY_FARE = 32;
	private static final double BUSINESS_FARE = 57;
	private static final double SLEEPER_FARE = 89;

	private String trainType; // high-speed, regional,...
	private String seatClass; // Economy, Business, First

	// Constructors
	public Train(String companyName, String departureCity, String arrivalCity, String trainType, String seatClass) {
		super(companyName, departureCity, arrivalCity);
		this.trainType = trainType;
		this.seatClass = seatClass;
	}

	public Train(Train other) {
		this(other.getCompanyName(), other.getDepartureCity(), other.getArrivalCity(), other.trainType,
				other.seatClass);
	}

	public Train() {
		this(null, null, null, null, null);
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

	public void setSeatClass(String seatClass) {
		this.seatClass = seatClass;
	}

	// Other Methods
	@Override
	public Transportation copy() {
		return new Train(this);
	}

	@Override
	public String toString() {
		String display;
		display = String.join(";", TRANSPORT_TYPE);
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
			cost = ECONOMY_FARE * numberOfDays;
		} else if (seatClass.equalsIgnoreCase("Business")) {
			cost = BUSINESS_FARE * numberOfDays;
		} else if (seatClass.equalsIgnoreCase("Sleeper")) {
			cost = SLEEPER_FARE * numberOfDays;
		} else {
			cost = 1000; // no class selected - random price...
		}
		return cost;
	}

}
