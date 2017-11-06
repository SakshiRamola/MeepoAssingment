package com.notes.assignment.exception;
/**
 * Custom error to be used in controller for sending exception to rest api users.
 *
 * @author sakshi
 */

public class CustomErrorType {
	 
	
	    private String errorMessage;
	 
	    public CustomErrorType(String errorMessage){
	        this.errorMessage = errorMessage;
	    }
	 
	    public String getErrorMessage() {
	        return errorMessage;
	    }
	 
	
}
