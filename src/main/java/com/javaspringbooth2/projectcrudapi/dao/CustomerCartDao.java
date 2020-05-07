package com.javaspringbooth2.projectcrudapi.dao;

import java.util.List;

import com.javaspringbooth2.projectcrudapi.model.Cart;

public interface CustomerCartDao {
	public List<Cart> getAllCartItems();
}
