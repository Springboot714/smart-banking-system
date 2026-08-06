package com.smartbakingsystem.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.smartbakingsystem.Enumeration.AddressStatus;
import com.smartbakingsystem.Enumeration.AddressType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="addresses")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AddressType addressType; 
	
	@Column(nullable = false)
	private String addressLine1;
	
	private String addressLine2;
	
	private String landmark;
	
	@Column(nullable = false)
	private String city;
	
	@Column(nullable = false)
	private String district;
	
	@Column(nullable = false)
	private String state;
	
	@Column(nullable = false)
	private String country;
	
	@Column(nullable = false)
	private String postalCode;
	
	@Enumerated(EnumType.STRING)
	private AddressStatus addressStatus;
	
	@CreationTimestamp
    private LocalDateTime createdAt;
	
	@UpdateTimestamp
    private LocalDateTime updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

}
