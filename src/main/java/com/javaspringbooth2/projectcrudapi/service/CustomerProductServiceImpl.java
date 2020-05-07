package com.javaspringbooth2.projectcrudapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaspringbooth2.projectcrudapi.dao.CustomerProductDao;
import com.javaspringbooth2.projectcrudapi.model.Product;

@Service(value = "customerProductService")
@Transactional
public class CustomerProductServiceImpl implements CustomerProductService {
	
	@Autowired
	private CustomerProductDao customerProductDao;
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = null;
		products = customerProductDao.getAllProduct();
		return products;
		
	}

}
