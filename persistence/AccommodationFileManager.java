package persistence;

	
// import classes needed for file reading and writing
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// import custom exception 
import exceptions.InvalidAccommodationDataException;

// import accommodation classes
import travelPackage.Accommodation;
import travelPackage.Hotel;
import travelPackage.Hostel;

	public class AccommodationFileManager {

	  
	    // save accommodations to file
	
	    // this will writes all valid accommodation objects from the
	    // array into a CSV file.
	    

	    public static void saveAccommodations(Accommodation[] accommodations, int accommodationCount, String filePath)
	            throws IOException {

	        // open the file for writing
	        PrintWriter pw = new PrintWriter(new FileWriter(filePath));

	        // loop thru all valid accommodations in the array
	        for (int i = 0; i < accommodationCount; i++) {

	            // make sure current element is not null
	            if (accommodations[i] != null) {
					pw.println(accommodations[i]);
	            }
	        }

	        // close the file after writing
	        pw.close();
	    }

	    // load accommodations from file
	  
	    // this will reads a CSV file and reconstructs accommodation
	    // objects (Hotel or Hostel) based on the first attribute.
	    
	    // the method returns the number of valid accommodations loaded.

	    

	    public static int[] loadAccommodations(Accommodation[] accommodations, String filePath) throws IOException {

	        // open the file for reading
	        BufferedReader br = new BufferedReader(new FileReader(filePath));

	        String line;
	        int count = 0, hotelCount = 0, hostelCount = 0;

	        // read file one line at a time
	        while ((line = br.readLine()) != null) {

	            try {

	                //split the line using ";"
	                String[] parts = line.split(";");

	                // each line must contain exactly 6 attributes
	                if (parts.length != 6) {
	                    throw new InvalidAccommodationDataException("Invalid number of fields.");
	                }

	                // extract common accommodation data
	             
	                String type = parts[0];              // HOTEL or HOSTEL
	                String accommodationID = parts[1];
	                String name = parts[2];
	                String location = parts[3];
	                double pricePerNight = Double.parseDouble(parts[4]);
	                int numberOfNights = Integer.parseInt(parts[5]); //starRating for hotel (int as well)

	                // prevent array overflow
	                if ((count) >= accommodations.length) {
	                    ErrorLogger.log("Accommodation array is full. Remaining lines were skipped.");
	                    break;
	                }

	                // create the correct subclass object
	             

	               
	                if (type.equalsIgnoreCase("HOTEL")) {

	                    accommodations[count] = new Hotel(
	                            accommodationID,
	                            name,
	                            location,
	                            pricePerNight,
	                            numberOfNights
	                    );
						count++;
	                    hotelCount++;
	                }

	             
	                else if (type.equalsIgnoreCase("HOSTEL")) {

	                    accommodations[count] = new Hostel(
	                            accommodationID,
	                            name,
	                            location,
	                            pricePerNight,
	                            numberOfNights
	                    );
						count++;
	                    hostelCount++;
	                }

	                // if type is unknown
	                else {
	                    throw new InvalidAccommodationDataException("Unknown accommodation type: " + type);
	                }

	            }

	            // if accommodation validation fails
	            catch (InvalidAccommodationDataException e) {
	                ErrorLogger.log("Invalid accommodation line: " + line + " | Reason: " + e.getMessage());
	            }

	            // if conversion from String to number fails
	            catch (NumberFormatException e) {
	                ErrorLogger.log("Number format error in accommodation line: " + line + " | Reason: " + e.getMessage());
	            }

	            // any other unexpected error
	            catch (Exception e) {
	                ErrorLogger.log("Unexpected error while loading accommodation line: " + line + " | Reason: " + e.getMessage());
	            }
	        }

	        // close file after reading
	        br.close();
			

	        // return the number of successfully loaded accommodations
			int[] counts = {hotelCount, hostelCount};
	        return counts;
	   
	    }

}
