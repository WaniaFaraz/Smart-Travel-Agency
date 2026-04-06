/*
* ----------------------------------------------------------------------------------
* Assignment 2
* Written by Wania Faraz 40332781
*            Zahira Atmani 40350242
* ----------------------------------------------------------------------------------
* This class is responsible for handling the persistence of Transportation objects using CSV files.
* It provides methods for saving transportation objects into a file, loading transportation objects from a file.
* 
*
*/

package persistence;

//import classes needed for file reading and writing
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

//import custom exception
import exceptions.InvalidTransportDataException;

//import transportation classes
import travelPackage.*;

public class TransportFileManager {
    // save transportations to file
    // this will take the transportation array and write all valid transportation
    // objects into a csv file
    public static void saveTransportations(List<Transportation> transports, String filePath) throws IOException {
        // open the file for writing
        PrintWriter pw = new PrintWriter(new FileWriter(filePath));
        // loop thru all valid transportations in the array
        for (int i = 0; i < transports.size(); i++) {
            if (transports.get(i) != null) {// check if the array slot is not empty
                pw.println(transports.get(i)); // calls appropriate toString() method
            }
        }
        pw.close();// close the file after writing
    }

    // load transportations from file
    // this will read a csv file and reconstructs the transport objects based on the
    // first column
    public static void loadTransportations(List<Transportation> transports, String filePath) throws IOException {
        // open the file for reading
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        // read the file line by line
        while ((line = br.readLine()) != null) {
            try {
                String[] parts = line.split(";");// split the line with ;

                if (parts.length != 7) {// check if the row has the good number of attributes
                    throw new InvalidTransportDataException("Invalid number of fields.");
                }
                // extract the transportation attributes
                String type = parts[0];
                String transportID = parts[1];
                String company = parts[2];
                String departure = parts[3];
                String arrival = parts[4];
                double cost = Double.parseDouble(parts[5]);

               
                // create object based on transport type
                // create flight object
                if (type.equalsIgnoreCase("FLIGHT")) {
                    double luggageAllowance = Double.parseDouble(parts[6]);
                    Transportation flightCreated = new Flight(
                            transportID, company, departure, arrival, cost, luggageAllowance);
                    transports.add(flightCreated);
                }
                // create train object
                else if (type.equalsIgnoreCase("TRAIN")) {
                    String trainType = parts[6];
                    Transportation trainCreated = new Train(
                            transportID, company, departure, arrival, cost, trainType);
                    transports.add(trainCreated);
                }
                // create bus object
                else if (type.equalsIgnoreCase("BUS")) {
                    int numberOfStops = Integer.parseInt(parts[6]);
                    Transportation busCreated = new Bus(
                            transportID, company, departure, arrival, cost, numberOfStops);
                    transports.add(busCreated);
                }

                else {// if the transport type is unknown
                    throw new InvalidTransportDataException("Unknown transportation type: " + type);
                }
                // catch invalid transport data
            } catch (InvalidTransportDataException e) {// if validation fails
                ErrorLogger.log("Invalid transportation line: " + line + " | Reason: " + e.getMessage());
                // catch number conversion errors
            } catch (NumberFormatException e) {
                ErrorLogger.log("Number format error in transportation line: " + line + " | Reason: " + e.getMessage());
            } catch (Exception e) {// catch other unexpected error
                ErrorLogger.log(
                        "Unexpected error while loading transportation line: " + line + " | Reason: " + e.getMessage());
            }
        }

        br.close();// close file after reading
        

    }
}
