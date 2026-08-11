package com.smartbakingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbakingsystem.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
