/*
 * -----------------------------------------------------------------
 * Assignment 3
 * Written by :  Wania Faraz, ID: 
 *               Zahira Atmani, ID: 40350342
 *               
 * ------------------------------------------------------------------
 * This class is for objects that have pricing logic like Trip class.  
 * 
 * 
 * 
 */


package interfaces;

public interface Billable {
	//returns the base price of the object
	double getBasePrice();
	//returns the total cost of the object (includes accommodation, transportation, etc)
	double getTotalCost();

}
