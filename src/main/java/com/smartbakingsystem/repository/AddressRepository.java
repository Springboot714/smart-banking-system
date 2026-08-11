package com.smartbakingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbakingsystem.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
