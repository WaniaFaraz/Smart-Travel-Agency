package persistence;
//import classes needed for file reading and writing
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//import custom exception
import exceptions.InvalidTransportDataException;
//import transportation classes
import travelPackage.Transportation;
import travelPackage.Flight;
import travelPackage.Train;
import travelPackage.Bus;


public class TransportFileManager {
	// save transportations to file
	//this will take the transportation array and write all valid transportation objects into a csv file
	public static void saveTransportations(Transportation[] transportations, int transportCount, String filePath) throws IOException {
		//open the file for writing
		PrintWriter pw = new PrintWriter(new FileWriter(filePath));
		//loop thru all valid transportations in the array
		for (int i = 0; i < transportCount; i++) {
			if (transportations[i] != null) {//check if the array slot is not empty
				//if the object is a Flight
				if (transportations[i] instanceof Flight) {
					//cast the object to Flight so we can have access
					Flight f = (Flight) transportations[i];
					//write the flight data into the csv file
					pw.println("FLIGHT" + ";"
                            + f.getTransportID() + ";"
                            + f.getCompanyName() + ";"
                            + f.getDepartureCity() + ";"
                            + f.getArrivalCity() + ";"
                            + f.calculateCost() + ";"
                            + f.getLuggageAllowance()); //specific method to Flight
				} 
				//if the object is Train
				else if (transportations[i] instanceof Train) {
                    Train t = (Train) transportations[i];
                    pw.println("TRAIN" + ";"
                            + t.getTransportID() + ";"
                            + t.getCompanyName() + ";"
                            + t.getDepartureCity() + ";"
                            + t.getArrivalCity() + ";"
                            + t.calculateCost() + ";"
                            + t.getTrainType());//specific method to Train
                }
				//if the object is Bus
				else if (transportations[i] instanceof Bus) {
                    Bus b = (Bus) transportations[i];
                    pw.println("BUS" + ";"
                            + b.getTransportID() + ";"
                            + b.getCompanyName() + ";"
                            + b.getDepartureCity() + ";"
                            + b.getArrivalCity() + ";"
                            + b.calculateCost() + ";"
                            + b.getNumberOfStops());//specific method to Bus
                }
			}
		}
		pw.close();//close the file after writing
	}
	//load transportations from file
	//this will read a csv file and reconstructs the transport objects based on the first column
	public static int loadTransportations(Transportation[] transportations, String filePath) throws IOException {
   //open the file for reading
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        int count = 0;
        
    //read the file line by line
        while ((line = br.readLine()) != null) {
            try {
                String[] parts = line.split(";");//split the line with ;

                if (parts.length != 7) {//check if the row has the good number of attributes
                    throw new InvalidTransportDataException("Invalid number of fields.");
                }
                //extract the transportation attributes
                String type = parts[0];
                String transportID = parts[1];
                String company = parts[2];
                String departure = parts[3];
                String arrival = parts[4];
                double cost = Double.parseDouble(parts[5]);
                //make sure the array doesnt overflow
                if (count >= transportations.length) {
                    ErrorLogger.log("Transportation array is full. Remaining lines were skipped.");
                    break;
                }
             //create object based on transport type
                
                if (type.equalsIgnoreCase("FLIGHT")) {
                    double luggageAllowance = Double.parseDouble(parts[6]);
                    transportations[count] = new Flight(
                            transportID, company, departure, arrival, cost, luggageAllowance);
                    count++;
                }

                else if (type.equalsIgnoreCase("TRAIN")) {
                    String trainType = parts[6];
                    transportations[count] = new Train(
                            transportID, company, departure, arrival, cost, trainType);
                    count++;
                }

                else if (type.equalsIgnoreCase("BUS")) {
                    int numberOfStops = Integer.parseInt(parts[6]);
                    transportations[count] = new Bus(
                            transportID, company, departure, arrival, cost, numberOfStops);
                    count++;
                }

                else {//if the transport type is unknown
                    throw new InvalidTransportDataException("Unknown transportation type: " + type);
                }

            } catch (InvalidTransportDataException e) {//if validation fails
                ErrorLogger.log("Invalid transportation line: " + line + " | Reason: " + e.getMessage());
            } catch (NumberFormatException e) {//if numeric conversion fails
                ErrorLogger.log("Number format error in transportation line: " + line + " | Reason: " + e.getMessage());
            } catch (Exception e) {//catch other unexpected error
                ErrorLogger.log("Unexpected error while loading transportation line: " + line + " | Reason: " + e.getMessage());
            }
        }

        br.close();//close file after reading
        return count;//return how many transports were successfully loaded

	}
}
