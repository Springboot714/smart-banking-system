package com.smartbakingsystem.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.smartbakingsystem.Enumeration.KycStatus;
import com.smartbakingsystem.Enumeration.KycType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
@Table(name = "customer_kyc")
public class CustomerKyc {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int kycId;
	
	@Enumerated(EnumType.STRING)
	private KycType kycType;
	
	@Column(nullable = false ,unique = true)
	private Long aadhaarNumber;
	
	@Column(nullable = false ,unique = true)
	private Long panNumber;
	
	@Enumerated(EnumType.STRING)
	private KycStatus kycStatus;
	
	
	private LocalDateTime verificationDate;
	
	private String rejectionReason;
	
	private String verifiedBy;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime updatedAt;
	
	@OneToOne
	@JoinColumn(name = "customer_id",nullable = false, unique = true)
	private Customer customer;

}
