package com.crm.realestatecrm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="salesexecutive")
public class SalesExecutive {

	@Column(name="sales_exec_id")
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String salesExecId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_number")
	private long mobileNumber;
	
	@Column(name="manager_email")
	private String managerEmail;
	
	private String password;
	
	public SalesExecutive() {
		
	}

	public SalesExecutive(String firstName, String lastName, String email, long mobileNumber, String companyName, String managerEmail,
			String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.managerEmail = managerEmail;
		this.password = password;
	}

	

	public String getSalesExecId() {
		return salesExecId;
	}

	public void setSalesExecId(String salesExecId) {
		this.salesExecId = salesExecId;
	}

	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerId) {
		this.managerEmail = managerId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SalesExecutive [salesExecId=" + salesExecId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", mobileNumber=" + mobileNumber + ", managerEmail=" + managerEmail
				+ ", password=" + password + "]";
	}
	
	

	
}
