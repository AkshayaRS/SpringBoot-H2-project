package com.javaspringbooth2.projectcrudapi.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "SALES_CART")
public class CartEntity {
	
	@Id
	@Column(name="CART_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cartId;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="PRODUCT_ID")
	private ProductEntity productEntity;
	
	@Column(name="QUANTITY")
	private Integer quantity;
	
	@Column(name="DATE_OF_ORDER")
	private LocalDateTime dateoforder;
	
	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public ProductEntity getProductEntity() {
		return productEntity;
	}

	public void setProductEntity(ProductEntity productEntity) {
		this.productEntity = productEntity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDateTime getDateoforder() {
		return dateoforder;
	}

	public void setDateoforder(LocalDateTime dateoforder) {
		this.dateoforder = dateoforder;
	}
	
	

}
