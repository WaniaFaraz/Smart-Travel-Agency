//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
// this custom exception is used when invalid data is provided while creating or loading client object.
//
//
//
package exceptions;

public class InvalidClientDataException extends Exception {
	//create a new exception with a specific message describing the error
	public InvalidClientDataException(String message) {
		super(message);
	}

}
