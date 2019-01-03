package com.revature.exceptions;

public class InvalidInputException extends RuntimeException {

	public InvalidInputException(String errorMessage) {
		super(errorMessage);
	}
}
