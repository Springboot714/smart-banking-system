package com.smartbakingsystem.exception;

import com.smartbakingsystem.Enumeration.AddressStatus;

public class AddressAlreadyInactiveExceptionImpl {
	
	
	public static void AddressAlreadyInactiveExceptionmethod(AddressStatus addressStatus) throws AddressAlreadyInactiveException {
		if(addressStatus==AddressStatus.INACTIVE) {
			throw new AddressAlreadyInactiveException("Address Already Inactive");
		}
	}

}
