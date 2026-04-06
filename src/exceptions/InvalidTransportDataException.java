//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
// this custom exception is used when invalid data is encountered while creating or loading transportation object.
//
//
//
package exceptions;

public class InvalidTransportDataException extends Exception {
	//create a new exception with a specific message describing the error
	public InvalidTransportDataException(String message) {
		super(message);
	}

}
