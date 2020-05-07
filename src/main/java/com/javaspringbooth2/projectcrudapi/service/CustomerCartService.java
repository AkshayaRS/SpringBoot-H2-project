package com.javaspringbooth2.projectcrudapi.service;

import java.util.List;

import com.javaspringbooth2.projectcrudapi.model.Cart;


public interface CustomerCartService {
	public List<Cart> getAllCartItems();
}
