package com.javaspringbooth2.projectcrudapi.service;

import java.util.List;

import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;

public interface CustomerService {

	public void addProductToCart(String customerEmailId, Integer productId, Integer quantity) throws Exception;
	public List<Cart> getCustomerCarts(String customerEmailId) throws Exception;
	public void modifyQuantityOfProductInCart(String customerEmailId, Integer productId, Integer quantity) throws Exception;
	public void deleteProductFromCart(String customerEmailId, Integer cartId);

}
