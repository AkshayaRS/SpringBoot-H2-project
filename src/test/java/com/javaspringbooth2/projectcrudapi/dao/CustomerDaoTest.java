package com.javaspringbooth2.projectcrudapi.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback(true)
public class CustomerDaoTest {
	
	@Autowired
	private CustomerDao customerDao;
	
	
	@Test
	public void getCustomerCartsValid(){
		
		Assert.assertNotNull(customerDao.getCustomerCarts("tom@abc.com"));
	}
	
	@Test
	public void getProductById(){
		
		Assert.assertNotNull(customerDao.getProductById(1000));
	}
	
	@Test
	public void deleteProductFromCart(){
		
		customerDao.deleteProductFromCart("tom@abc.com", 1004);
		
	}
	
	@Test
	public void modifyQuantityOfProductInCart(){
		
		customerDao.modifyQuantityOfProductInCart("tom@abc.com", 1001, 2);
	}
	
	@Test
	public void addProductToCart(){
		
		customerDao.addProductToCart("tom@abc.com", 1001, 1);
	}

}
