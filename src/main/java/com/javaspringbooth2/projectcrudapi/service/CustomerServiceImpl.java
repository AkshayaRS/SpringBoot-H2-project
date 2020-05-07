package com.javaspringbooth2.projectcrudapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaspringbooth2.projectcrudapi.dao.CustomerDao;
import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;

@Service(value = "customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;
	

	@Override
	public void addProductToCart(String customerEmailId, Integer productId, Integer quantity) throws Exception{
		System.out.println("In customer service...................");
		List<Cart> listOfCustomerCart = customerDao.getCustomerCarts(customerEmailId);
		for (Cart cart : listOfCustomerCart) {
			if (productId.equals(cart.getProduct().getProductId())) {
				throw new Exception("CustomerService.PRODUCT_PRESENT_IN_CART");
			}
		}
		
		if (customerDao.getProductById(productId).getQuantity() < quantity) {
			throw new Exception("CustomerService.OUT_OF_STOCK");
		}
		
		System.out.println("In customer service...................");
		customerDao.addProductToCart(customerEmailId, productId, quantity);
	}

	@Override
	public List<Cart> getCustomerCarts(String customerEmailId) throws Exception {
		// TODO Auto-generated method stub
		List<Cart> listOfCustmerCaert = customerDao.getCustomerCarts(customerEmailId);
		if (listOfCustmerCaert.isEmpty()) {
			throw new Exception("CustomerService.CART_IS_EMPTY");
		}
		return listOfCustmerCaert;
	}

	@Override
	public void modifyQuantityOfProductInCart(String customerEmailId, Integer productId, Integer quantity) throws Exception{
		// TODO Auto-generated method stub
		
			Product product = customerDao.getProductById(productId);
			if(product.getQuantity() < quantity) {
				throw new Exception("CustomerService.OUT_OF_STOCK");
			}
			customerDao.modifyQuantityOfProductInCart(customerEmailId,productId, quantity);
		
	}

	@Override
	public void deleteProductFromCart(String customerEmailId, Integer productId) {
		// TODO Auto-generated method stub
		
			customerDao.deleteProductFromCart(customerEmailId, productId);
		
		
	}
	
}
