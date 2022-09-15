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
@Table (name="product")
public class Product {

	@Column (name="product_id") @JsonView(View.Base.class)
	private Integer product_id;
	
	@Column (name="product_name") @JsonView(View.Base.class)
	private String product_name;
	
	@Column (name="brand") @JsonView(View.Base.class)
	private String brand;
	
	@Column (name="price") @JsonView(View.Base.class)
	private float price;
	
	@Column (name="quantity") @JsonView(View.Base.class)
	private int quantity;
	
	Product () {}

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}


	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
