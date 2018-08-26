package com.rajusays.demo.exception;

/**
 * {@link UserAlreadyExistsException} will be thrown when the user_name already
 * taken by some other user
 * 
 * @author rajub
 *
 */
public class UserAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 218961259861403761L;

	public UserAlreadyExistsException() {
		super("User Id is already taken");
	}

}
