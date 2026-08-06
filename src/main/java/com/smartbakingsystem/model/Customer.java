package com.smartbakingsystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.smartbakingsystem.Enumeration.CustomerStatus;
import com.smartbakingsystem.Enumeration.Gender;
import com.smartbakingsystem.Enumeration.KycStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumeratedValue;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false,unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false,unique = true)
	private String phoneNumber;
	
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false,unique = true)
	private String aadhaarNumber;
	
	@Column(nullable = false,unique = true)
	private String panNumber;
	
	@Enumerated(EnumType.STRING)
	private CustomerStatus customerStatus;
	
	@Enumerated(EnumType.STRING)
	private KycStatus kycStatus;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToMany(mappedBy = "customers",cascade = CascadeType.ALL)
	private List<Address> addresses;
	
	
	
	
	
	

}
