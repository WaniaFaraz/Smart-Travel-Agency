/*
 * -----------------------------------------------------------------
 * Assignment 3
 * Written by :  Wania Faraz, ID: 
 *               Zahira Atmani, ID: 40350342
 *               
 * ------------------------------------------------------------------
 * This class is used for any other class (Client, Trip, Accommodation, Transportation)
 *  that has a unique ID. 
 * 
 * 
 * 
 */


package interfaces;

public interface Identifiable {
	//It will return the unique ID for each class: C1001, T2001, A4001
	String getId();

}
