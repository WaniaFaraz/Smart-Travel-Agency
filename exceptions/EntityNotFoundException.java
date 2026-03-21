//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
// this custom exception is used when an entity (client, accommodation, transportation, trip)cannot be found.
//
//
//
package exceptions;

public class EntityNotFoundException extends Exception {
	//create a new exception with a specific message describing which entity was not found
	public EntityNotFoundException(String message) {
		super(message);
	}
	

}
