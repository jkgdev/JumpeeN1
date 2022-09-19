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
	
	@Column (name="customerid") @JsonView(View.Base.class)
	private int customerid;
	
	@Column (name="transaction_type") @JsonView(View.Base.class)
	private String transaction_type;
	
	@Column (name="transact_amount") @JsonView(View.Base.class)
	private float transact_amount;
	
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

	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}

	public float getTransact_amount() {
		return transact_amount;
	}


	public void setTransact_amount(float transact_amount) {
		this.transact_amount = transact_amount;
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


	public String getTransaction_type() {
		return transaction_type;
	}


	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}
	
	
	
}
