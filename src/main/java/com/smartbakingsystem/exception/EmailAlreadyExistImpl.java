package com.smartbakingsystem.exception;

public class EmailAlreadyExistImpl {
	
	
	public static void emailAlreadyExistmethod(boolean existByEmail) throws EmailAlreadyExistException {
		if(existByEmail) {
			 throw new EmailAlreadyExistException("Email already Exist");
		}
	}

}
