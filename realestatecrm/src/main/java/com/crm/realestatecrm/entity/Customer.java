package com.crm.realestatecrm.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
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
	
	@Column(name="source")
	private String source;
	
	@Column(name="status")
	private String status;
	
	@Column(name="property_id")
	private String propertyId;
	
	@Column(name="budget_range")
	private String budgetRange;
	
	@Column(name="location_preference")
	private String locationPreference;
	
	@Column(name="created_at")
	private String createdAt;
	
	@PrePersist
    protected void onCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        createdAt = LocalDateTime.now().format(formatter); 
    }
	
	public Customer() {
		
	}

	public Customer(String email, String propertyId) {
		super();
		this.email = email;
		this.propertyId = propertyId;
	}
	
	public Customer(String email, String locationPreference, String budgetRange, String status) {
		super();
		this.email = email;
		this.locationPreference = locationPreference;
		this.budgetRange = budgetRange;
		this.status = status;
	}
	
	public Customer(String firstName, String lastName, String email, long mobileNumber, String salesExecId, String managerId,
			String password, String source) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.salesExecId = salesExecId;
		this.managerId = managerId;
		this.source = source;
	}
	
	public Customer(String customerId, String firstName, String lastName, String email, long mobileNumber,
			String salesExecId, String managerId, String source, String status, String propertyId, String budgetRange,
			String locationPreference) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.salesExecId = salesExecId;
		this.managerId = managerId;
		this.source = source;
		this.status = status;
		this.propertyId = propertyId;
		this.budgetRange = budgetRange;
		this.locationPreference = locationPreference;
	}
	
	
	public Customer(String customerId, String firstName, String lastName, String email, long mobileNumber,
			String salesExecId, String managerId, String source, String status, String propertyId) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.salesExecId = salesExecId;
		this.managerId = managerId;
		this.source = source;
		this.status = status;
		this.propertyId = propertyId;
	}
	
	public Customer(String customerId, String firstName, String lastName, String email, long mobileNumber,
			String salesExecId, String managerId, String source, String status, String budgetRange,
			String locationPreference) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.salesExecId = salesExecId;
		this.managerId = managerId;
		this.source = source;
		this.status = status;
		this.budgetRange = budgetRange;
		this.locationPreference = locationPreference;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public String getBudgetRange() {
		return budgetRange;
	}

	public void setBudgetRange(String budgetRange) {
		this.budgetRange = budgetRange;
	}

	public String getLocationPreference() {
		return locationPreference;
	}

	public void setLocationPreference(String locationPreference) {
		this.locationPreference = locationPreference;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
