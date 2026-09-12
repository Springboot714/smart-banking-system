package com.smartbakingsystem.exception;

public class AddressAndcustomerNotMatchExpectionImpl {
	
	
	public static void addressAndcustomerNotMatchExpectionMethod(boolean isAddressCustomermatch) throws AddressAndcustomerNotMatchExpection{
		
		
		if(isAddressCustomermatch) {
			throw new AddressAndcustomerNotMatchExpection("Address does not belong to customer");
		}
	}

}
