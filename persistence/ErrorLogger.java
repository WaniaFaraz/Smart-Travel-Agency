package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class ErrorLogger {
	
	public static void log(String message) {
	
	try {
		PrintWriter pw = new PrintWriter(new FileWriter("logs/errors.txt", true));
		pw.println(message);
		pw.close();
		
	} catch (IOException e) {
		System.out.println("Errors: could not write to logs/error.txt");
	}
  }
}
