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
@Table (name = "orderhistory")
public class Checkout {

	@Column (name="ordertransact_id") @JsonView(View.Base.class)
	private Integer ordertransact_id;
	@Column (name="order_id") @JsonView(View.Base.class)
	private int order_id;
	@Column (name="customerid") @JsonView(View.Base.class)
	private int customerid;
	@Column (name="wallet_id") @JsonView(View.Base.class)
	private int wallet_id;
	@Column (name="checkoutamount") @JsonView(View.Base.class)
	private float checkoutamount;
	@Column (name="status") @JsonView(View.Base.class)
	private String status;
	@Column (name="date") @JsonView(View.Base.class)
	private String date;
		
	Checkout() {}

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getOrdertransact_id() {
		return ordertransact_id;
	}

	public void setOrdertransact_id(Integer ordertransact_id) {
		this.ordertransact_id = ordertransact_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getCustomerid() {
		return customerid;
	}

	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public int getWallet_id() {
		return wallet_id;
	}

	public void setWallet_id(int wallet_id) {
		this.wallet_id = wallet_id;
	}

	public float getCheckoutamount() {
		return checkoutamount;
	}

	public void setCheckoutamount(float checkoutamount) {
		this.checkoutamount = checkoutamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	
}
