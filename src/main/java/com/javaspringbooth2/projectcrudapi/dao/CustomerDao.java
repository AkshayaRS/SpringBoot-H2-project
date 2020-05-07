package com.javaspringbooth2.projectcrudapi.dao;

import java.util.List;

import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;


public interface CustomerDao {
	
	public void addProductToCart(String customerEmailId, Integer productId,Integer quantity);
	public List<Cart> getCustomerCarts(String customerEmailId);
	public void modifyQuantityOfProductInCart(String customerEmailId,Integer productId, Integer quantity);
	public void deleteProductFromCart(String customerEmailId, Integer cartId);
	public Product getProductById(Integer productId);
}
