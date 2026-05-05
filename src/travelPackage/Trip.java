//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
package travelPackage;

import clientPackage.*;
import exceptions.InvalidAccommodationDataException;
import exceptions.InvalidClientDataException;
import exceptions.InvalidTransportDataException;
import exceptions.InvalidTripDataException;

import interfaces.Identifiable;
import interfaces.Printable;
import interfaces.Billable;
import interfaces.CsvPersistable;

public class Trip implements Identifiable, Billable, CsvPersistable, Comparable<Trip>, Printable{
	private static String tripIDF = "T"; // FF stands for first
	public final String TRIP_ID;
	private static int tripNumber = 2001; //last 4 digits of trip ID. incremented by 1 after each initialization

	private String destination;
	private int duration;
	private double basePrice;
	private Client client; // client associated with the trip
	private Accommodation accommodation;
	private Transportation transportation;

	// Constructors
	public Trip(String destination, int duration, double basePrice, Client client, Accommodation accommodation, Transportation transportation) throws InvalidTripDataException, InvalidAccommodationDataException, InvalidTransportDataException {
		//Use setters to instantiate object since those will validate parameters
		//only duration and base price generate exceptions. Everything within try block so that invalid trip is not created
		setDuration(duration);
		setBasePrice(basePrice);

		setClient(client);
		setDestination(destination);
		setAccommodation(accommodation);
		setTransportation(transportation);
		//tripID
		TRIP_ID = tripIDF + tripNumber;
		tripNumber++;
			
	}

	public Trip() throws InvalidTripDataException, InvalidClientDataException, InvalidAccommodationDataException, InvalidTransportDataException{
		this("no destination", 0, 100, new Client(), null, null);
		double amountSpent = client.getAmountSpent();
		client.setAmountSpent(amountSpent + calculateTotalCost());
	}

	public Trip(Trip other) throws InvalidTripDataException, InvalidClientDataException, InvalidAccommodationDataException, InvalidTransportDataException{
		// creating a copy of the client since the constructor does not copy. no need to
		// create new transportations and accommodations since the constructor copies anyway
		this(other.destination, other.duration, other.basePrice, new Client(other.client), other.accommodation,
				other.transportation);
	}

	//constructor that takes ID - to create trip with correct ID
	public Trip(String ID, String destination, int duration, double basePrice, Client client, Accommodation accommodation, Transportation transportation) throws InvalidTripDataException, InvalidAccommodationDataException, InvalidTransportDataException {
		//Use setters to instantiate object since those will validate parameters
		//only duration and base price generate exceptions. Everything within try block so that invalid trip is not created
		TRIP_ID = ID;
		setDuration(duration);
		setBasePrice(basePrice);

		setClient(client);
		setDestination(destination);
		setAccommodation(accommodation);
		setTransportation(transportation);
		double amountSpent = client.getAmountSpent();
		client.setAmountSpent(amountSpent + calculateTotalCost());
		
	}

	// Accessors and Mutators
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDuration(int duration) throws InvalidTripDataException {
		if(duration < 1 || duration > 20) {
			throw new InvalidTripDataException("Duration must be between 1 and 20 days.");
		}
		this.duration = duration;
	}

	public void setBasePrice(double basePrice) throws InvalidTripDataException {
		if(basePrice < 100) {
			throw new InvalidTripDataException("Base price must be a minimum of $100.00");
		}
		this.basePrice = basePrice;
	}

	public void setClient(Client client) throws InvalidTripDataException {
		this.client = client; //no need to create new so that client can directly be edited from the trip
	}

	public void setAccommodation(Accommodation accommodation) throws InvalidAccommodationDataException{
		if(accommodation == null) {
			this.accommodation = null;
		}
		else {
			this.accommodation = accommodation.copy();
		}
		
	}

	public void setTransportation(Transportation transportation) throws InvalidTransportDataException {
		if(transportation == null) {
			this.transportation = null;
		}
		else {
			this.transportation = transportation.copy();
		}
		
	}

	public String getDestination() {
		return destination;
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

	public Accommodation getAccommodation() {
		return accommodation; //no need for new so that an accommodation can be edited by getting it from the trip
	}

	public String getAccommodationID() {
		if(accommodation == null) {
			return "";
		}
		else {
			return accommodation.ACCOMMODATION_ID;
		}
	}

	public Transportation getTransportation() {
		return transportation; //no need for new so that a transportation can be edited by getting it from the trip
	}

	public String getTransportationID() {
		if(transportation == null) {
			return "";
		}
		else {
			return transportation.TRANSPORT_ID;
		}
	}

	public String getTripID() {
		//simple method name so that getID() can be used for all objects without thinking of class
		return TRIP_ID;
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
	//methods to implement interface identifiable
	@Override
	public String getId(){
		return getTripID();
	}

	//return the total cost of the trip with the billable interface
	@Override
	public double getTotalCost(){
		try {
			return calculateTotalCost();
		} catch (Exception e){
			return basePrice;
		}
	}
	//convert trip to csv format with csvpersistable interface
	@Override
	public String toCsvRow(){
		return toString();
	}
	//method to sort Trip by total cost
	@Override
	public int compareTo(Trip other){
		return Double.compare(this.getTotalCost(), other.getTotalCost());
	}
	//method to create trip object from csv row
	public static Trip fromCsvRow(String csvLine, Client client, Accommodation accommodation, Transportation transportation) throws InvalidTripDataException, InvalidAccommodationDataException, InvalidTransportDataException{

		String[] parts = csvLine.split(";");

		if (parts.length != 7){
			throw new InvalidTripDataException("Invalid trip CSV format.");
		}
		return new Trip(parts[0], parts[4], Integer.parseInt(parts[5]), Double.parseDouble(parts[6]), client, accommodation, transportation);
	}

	// Other Methods
	@Override
	public String toString() {
		String display;
		String formattedPrice = String.format("%.2f", basePrice);
	
		display = String.join(";", TRIP_ID, client.CLIENT_ID, getAccommodationID(), getTransportationID(), destination, duration + "", formattedPrice);
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

	public double calculateTotalCost() throws InvalidAccommodationDataException {
		double accommodationCost = 0, transportationCost = 0;
		if(accommodation != null) {
			accommodationCost = accommodation.calculateCost(duration);
		}
		if(transportation != null) {
			transportationCost = transportation.calculateCost(duration);
		}
		double cost = basePrice + accommodationCost + transportationCost;
		return cost;
	}

	public double getTotalCost(Client client, Accommodation accommodation, Transportation transport) throws InvalidAccommodationDataException {
		double total;
		double accommodationCost = 0;
		if(accommodation != null) accommodationCost = accommodation.calculateCost(duration);
		double transportCost = 0;
		if(transportation != null) transportCost = transportation.calculateCost(duration);
		total =  accommodationCost + transportCost;
		return total;
	}

}
