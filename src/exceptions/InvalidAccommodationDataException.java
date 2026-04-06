//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
// this custom exception is used when invalid data is encountered when creating or loading
// accommodation object.
//
//
package exceptions;

public class InvalidAccommodationDataException extends Exception{
	//create a new exception with a specific message describing the error
	public InvalidAccommodationDataException(String message) {
		super(message);
	}

}
