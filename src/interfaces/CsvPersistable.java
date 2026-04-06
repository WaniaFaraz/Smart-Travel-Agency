/*
 * -----------------------------------------------------------------
 * Assignment 3
 * Written by :  Wania Faraz, ID: 
 *               Zahira Atmani, ID: 40350342
 *               
 * ------------------------------------------------------------------
 * This interface is required for the generic manager file which loads and saves objects. 
 * 
 * 
 * 
 */


package interfaces;

public interface CsvPersistable {
	//converts the object into CSV format string (attributes separated by semicolon)
	// and will return the CSV formatted string
	String toCsvRow();

}
