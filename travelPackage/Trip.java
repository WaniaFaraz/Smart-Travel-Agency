//-----------------------------------------------------------------------------
//Assignment 1
//Written by Wania Faraz 40332781
//-----------------------------------------------------------------------------
package travelPackage;

import clientPackage.*;

public class Trip {
	private static String tripIDFF = "T2001"; // FF stands for first five
	public final String TRIP_ID;
	private static int tripNumber = 0;

	private String destination;
	private int duration;
	private double basePrice;
	private Client client; // client associated with the trip
	private Accommodation accommodation;
	private Transportation transportation;

	// Constructors
	public Trip(String destination, int duration, double basePrice, Client client, Accommodation accommodation, Transportation transportation) {
		try {
			if(basePrice < 100)
				throw InvalidTripDataException("Invalid base price. Must be greater than $100.");
			if(duration < 1 || duration > 20)
				throw InvalidTripDataException("Duration must be 1-20 days.");
			
		}

		this.destination = destination;
		this.duration = duration;
		this.basePrice = basePrice;
		this.client = client; // Updating this client should update all references of this client. Also,
								// changing this client SHOULD change the original client. So no need for deep
								// copy

		// setting transportation and accommodation - copy required so that changing one
		// client's accommodation or transportation does not affect other clients
		this.transportation = transportation.copy();
		this.accommodation = accommodation.copy();

		// tripID
		String tripNum = String.format("%04d", tripNumber);
		TRIP_ID = tripIDFF + tripNum;
		tripNumber++;
	}

	public Trip() {
		this(null, 0, 0, new Client(), null, null);
	}

	public Trip(Trip other) {
		// creating a copy of the client since the constructor does not copy. no need to
		// create new transportations and accommodations since the constructor copies
		// anyway
		this(other.destination, other.duration, other.basePrice, new Client(other.client), other.accommodation,
				other.transportation);
	}

	// Accessors and Mutators
	public void setAccommodation(Accommodation accommodation) {
		this.accommodation = accommodation.copy();
	}

	public void setTransportation(Transportation transportation) {
		this.transportation = transportation.copy();
	}

	public String getTripID() {
		return TRIP_ID;
	}

	public int getDuration() {
		return duration;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public Client getClient() {
		return client; // no need for new so that a client can be edited by getting it from the trip
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public void setClient(Client client) {
		this.client = new Client(client);
	}

	public String getDestination() {
		return destination;
	}

	public int getDurationInDays() {
		return duration;
	}

	// optional but useful for extra functionality
	public String getTripType() {
		return "";
	}

	public double getDistance() {
		return 0;
	}

	// Other Methods
	@Override
	public String toString() {
		String display;
		String formattedPrice = String.format("%.2f", basePrice);
		display = client.toString() + "\n";
		display = display + "TRIP ID: " + TRIP_ID + "\n" + "Destination: " + destination + "\n" + "Duration: "
				+ duration + " days" + "\n" + "Base Price: $" + formattedPrice;
		return display;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} else if (obj.getClass() != getClass()) {
			return false;
		} else {
			boolean equality;
			Trip other = (Trip) obj;
			equality = ((duration == other.duration) && (basePrice == other.basePrice) && (client.equals(other.client))
					&& destination.equalsIgnoreCase(other.destination));
			equality = equality && (accommodation.equals(other.accommodation))
					&& (transportation.equals(other.transportation));
			return equality;
		}
	}

	public double calculateTotalCost() {
		double cost = basePrice + accommodation.calculateCost(duration) + transportation.calculateCost(duration);
		return cost;
	}

}
