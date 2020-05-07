package com.javaspringbooth2.projectcrudapi.model;

import java.time.LocalDateTime;

public class Cart {
	
	private Integer cartId;
	private Product product;
	private Integer quantity;
	private LocalDateTime dateToOrder;
	
	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getDateToOrder() {
		return dateToOrder;
	}
	public void setDateToOrder(LocalDateTime dateToOrder) {
		this.dateToOrder = dateToOrder;
	}
		
	
}
