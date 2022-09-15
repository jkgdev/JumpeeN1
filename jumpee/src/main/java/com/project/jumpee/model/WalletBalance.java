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
@Table (name="wallet")
public class WalletBalance {

	@Column (name="walletbalance_id") @JsonView(View.Base.class)
	private Integer walletbalance_id;
	
	@Column (name="customer_id") @JsonView(View.Base.class)
	private int customer_id;
	
	@Column (name="amount") @JsonView(View.Base.class)
	private float amount;
	
	WalletBalance () {}

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getWalletbalance_id() {
		return walletbalance_id;
	}
	public void setWalletbalance_id(int walletbalance_id) {
		this.walletbalance_id = walletbalance_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
		
}
