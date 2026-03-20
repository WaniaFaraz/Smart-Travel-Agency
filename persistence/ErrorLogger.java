package persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class ErrorLogger {
	
	public static void log(String message) {
	PrintWriter pw = null;
	try {
		pw = new PrintWriter(new FileWriter("output/logs/errors.txt", true));
		pw.println(message);
		
	} catch (IOException e) {
		System.out.println("Errors: could not write to logs/errors.txt");
	}finally {
		if(pw != null) {
			pw.close();
		}
	}
  }
}
