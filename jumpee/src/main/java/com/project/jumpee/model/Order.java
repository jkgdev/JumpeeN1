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
@Table (name="ordercart")
public class Order {

	@Column (name="order_id") @JsonView(View.Base.class)
	private Integer order_id; 
	
	@Column (name="order_id") @JsonView(View.Base.class)
	private int product_id;
	
	@Column (name="productname") @JsonView(View.Base.class)
	private String productname;
	
	@Column (name="price") @JsonView(View.Base.class)
	private float price;
	
	@Column (name="quantity") @JsonView(View.Base.class)
	private int quantity;
	
	@Column (name="totalprice") @JsonView(View.Base.class)
	private float totalprice;
	
	Order () {}

	@Id @GeneratedValue (strategy = GenerationType.IDENTITY)
	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
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

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
}
