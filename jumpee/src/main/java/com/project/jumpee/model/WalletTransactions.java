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
@Table (name="walletrecord")
public class WalletTransactions {
	
	@Column (name="wallet_transaction_id") @JsonView(View.Base.class)
	private Integer wallet_transaction_id;
	
	@Column (name="customer_id") @JsonView(View.Base.class)
	private int customer_id;
	
	@Column (name="addedamount") @JsonView(View.Base.class)
	private float addedamount;
	
	@Column (name="currentamount") @JsonView(View.Base.class)
	private float currentamount;
	
	@Column (name="date") @JsonView(View.Base.class)
	private String date;
	
	
	WalletTransactions (){}

	
	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getWallet_transaction_id() {
		return wallet_transaction_id;
	}
	public void setWallet_transaction_id(Integer wallet_transaction_id) {
		this.wallet_transaction_id = wallet_transaction_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public float getAddedamount() {
		return addedamount;
	}
	public void setAddedamount(float addedamount) {
		this.addedamount = addedamount;
	}

	public float getCurrentamount() {
		return currentamount;
	}
	public void setCurrentamount(float currentamount) {
		this.currentamount = currentamount;
	}

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
		
}
