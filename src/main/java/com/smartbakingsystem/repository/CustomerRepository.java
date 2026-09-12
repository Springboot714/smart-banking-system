package com.smartbakingsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartbakingsystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	
	@Query("""
		    SELECT c
		    FROM Customer c
		    WHERE LOWER(c.firstName) LIKE LOWER(CONCAT('%', :keyword, '%'))
		       OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :keyword, '%'))
		       OR LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
		       OR c.phoneNumber LIKE CONCAT('%', :keyword, '%')
		""")
	public Page<Customer> searchCustomers(@Param("keyword") String keyword,Pageable pageable);
	
	
	public boolean existsByEmail(String email);

}
