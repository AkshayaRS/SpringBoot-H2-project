package com.javaspringbooth2.projectcrudapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaspringbooth2.projectcrudapi.dao.CustomerCartDao;
import com.javaspringbooth2.projectcrudapi.model.Cart;

@Service(value = "customerCartService")
@Transactional
public class CustomerCartServiceImpl implements CustomerCartService {
	
	@Autowired
	private CustomerCartDao customerCartDao;
	
	@Override
	public List<Cart> getAllCartItems() {
		// TODO Auto-generated method stub
		
		List<Cart> items = null;
		items = customerCartDao.getAllCartItems();
		return items;
	}

}
