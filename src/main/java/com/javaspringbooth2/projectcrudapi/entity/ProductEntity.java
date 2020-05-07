package com.javaspringbooth2.projectcrudapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SALES_PRODUCT")
public class ProductEntity {
	
	@Id
	@Column(name="PRODUCT_ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer productId;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="CATEGORY")
	private String category;
	
	@Column(name="PRICE")
	private Double price;
	
	@Column(name="QUANTITY")
	private Integer quantity;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
