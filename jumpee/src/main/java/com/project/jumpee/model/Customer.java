package com.project.jumpee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


import com.fasterxml.jackson.annotation.JsonView;
import com.project.jumpee.View;

@Entity
@Table (name="customer")
public class Customer {
	@Column (name="customer_id") @JsonView(View.Base.class)
	private Integer customer_id;
	
	@Column (name="firstname")@JsonView(View.Base.class)
	private String firstname;
	
	@Column (name="lastname") @JsonView(View.Base.class)
	private String lastname;
	
	@Column (name="contact")@JsonView(View.Base.class)
	private String contact;
	
	@Column (name="email")@JsonView(View.Base.class)
	private String email;
	
	@Column (name="password") 
	private String password;	
	
	@Column (name="status") @JsonView(View.Base.class)
	private String status;

	private String confirmpassword;
	private String currentpassword;
	private String newpassword;
	private String confirmnewpassword;
	
	public Customer () {}	

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Transient
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	
	@Transient
	public String getCurrentpassword() {
		return currentpassword;
	}	
	public void setCurrentpassword(String currentpassword) {
		this.currentpassword = currentpassword;
	}

	@Transient
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	@Transient
	public String getConfirmnewpassword() {
		return confirmnewpassword;
	}
	public void setConfirmnewpassword(String confirmnewpassword) {
		this.confirmnewpassword = confirmnewpassword;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
