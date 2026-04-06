//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
// this custom exception is used when invalid data is encountered while creating or loading a trip object.
//
//
//
package exceptions;

public class InvalidTripDataException extends Exception {
	//create new exception with a specific message describing the error
	public InvalidTripDataException(String message) {
		super(message);
	}

}
