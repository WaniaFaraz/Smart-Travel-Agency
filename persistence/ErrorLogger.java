/*
* ----------------------------------------------------------------------------------
* Assignment 2
* Written by Wania Faraz
*            Zahira Atmani 40350242
* ----------------------------------------------------------------------------------
* This class is responsible for logging error messages into a text file.
* It is used during loading process when invalid data is encountered in CSV files. Each error 
* message is appended to the file which means previous logs are not overwritten.
*
*/


package persistence;
//import classes needed for writing to a file
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class ErrorLogger {
	//writes an error message to the log file
	public static void log(String message) {
	
	PrintWriter pw = null;
	try {
		//open file in append mode
		pw = new PrintWriter(new FileWriter("output/logs/errors.txt", true));
		//write the error message into the file
		pw.println(message);
		
	//if file cannot be written, print error to console
	} catch (IOException e) {
		System.out.println("Errors: could not write to logs/errors.txt");
	
	}finally {
		//ensure the file is properly closed
		if(pw != null) {
			pw.close();
		}
	}
  }
}
