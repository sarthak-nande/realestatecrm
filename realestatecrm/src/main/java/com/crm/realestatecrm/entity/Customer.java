package com.crm.realestatecrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	@Column(name="customer_id")
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String customerId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_number")
	private long mobileNumber;
	
	@Column(name="sales_exec_id")
	private String salesExecId;
	
	@Column(name="manager_id")
	private String managerId;
	
	public Customer() {
		
	}

	public Customer(String firstName, String lastName, String email, long mobileNumber, String salesExecId, String managerId,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.salesExecId = salesExecId;
		this.managerId = managerId;
	}

	
	public String getSalesExecId() {
		return salesExecId;
	}

	public void setSalesExecId(String salesExecId) {
		this.salesExecId = salesExecId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
}
