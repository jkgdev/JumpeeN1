package com.project.jumpee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.jumpee.View;

@Entity
@Table (name="address")
public class Address {

	@Column (name="addressid") @JsonView(View.Base.class)
	private Integer addressid;
	@Column (name="address") @JsonView(View.Base.class)
	private String address;
	@Column (name="contactperson") @JsonView(View.Base.class)
	private String contactperson;
	@Column (name="contactnumber") @JsonView(View.Base.class)
	private String contactnumber;
	@Column (name="customerid")
	private Integer customerid;
	
	Address () {}

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getAddressid() {
		return addressid;
	}

	public void setAddressid(Integer addressid) {
		this.addressid = addressid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactperson() {
		return contactperson;
	}

	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}

	public String getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
	
}
