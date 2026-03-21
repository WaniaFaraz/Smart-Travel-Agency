//-----------------------------------------------------------------------------
//Assignment 2
//Written by:
// 		Wania Faraz 40332781
//		Zahira Atmani 40350242
//-----------------------------------------------------------------------------
// This custom exception is used to indicate that an attempt was made to create or load a client 
// with an email that already exists in the system.
//
//
package exceptions;

public class DuplicateEmailException extends RuntimeException {
	//create a new exception with a specific message that describes the error
	public DuplicateEmailException(String message) {
		super(message);
	}
	

}
