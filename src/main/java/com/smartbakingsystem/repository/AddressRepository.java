package com.smartbakingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbakingsystem.model.Address;
import com.smartbakingsystem.model.Customer;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	
	public List<Address> findByCustomer(Customer customer);

}
