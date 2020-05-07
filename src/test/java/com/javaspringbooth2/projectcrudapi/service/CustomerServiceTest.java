package com.javaspringbooth2.projectcrudapi.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.javaspringbooth2.projectcrudapi.dao.CustomerDao;
import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Customer;
import com.javaspringbooth2.projectcrudapi.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {
	
	@Mock
	private CustomerDao customerDao;
	
	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void getCustomerCartValid() throws Exception{
		Customer customer=new Customer();
		customer.setEmailId("tom@abc.com");
		List<Cart> list = new ArrayList<Cart>();
		Cart e = null;
		list.add(e);
		Mockito.when(customerDao.getCustomerCarts(Mockito.anyString())).thenReturn(list);
		List<Cart> returned=customerService.getCustomerCarts(customer.getEmailId());
		Assert.assertNotNull(returned);
		}
	
	@Test
	public void getCustomerCartInValid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.CART_IS_EMPTY");
		Customer customer=new Customer();
		customer.setEmailId("tom@abc.com");
		Mockito.when(customerDao.getCustomerCarts(Mockito.anyString())).thenReturn(new ArrayList<Cart>());
		customerService.getCustomerCarts(customer.getEmailId());
		
	}

	
	@Test
	public void addProductToCartValid() throws Exception{
		Product p=new Product();
		p.setQuantity(4);
		p.setProductId(112);
		Cart cart=new Cart();
		cart.setProduct(p);
		cart.setQuantity(1);
		Product p1=new Product();
		p1.setProductId(222);
		Cart cart1=new Cart();
		cart1.setProduct(p1);
		List<Cart> customerCart=new ArrayList<Cart>();
		customerCart.add(cart1);
		
		Mockito.when(customerDao.getCustomerCarts(Mockito.anyString())).thenReturn(customerCart);
		Mockito.when(customerDao.getProductById(cart.getProduct().getProductId())).thenReturn(p);
		Mockito.doNothing().when(customerDao).addProductToCart(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());
		
		customerService.addProductToCart("abcd@abc.com", p.getProductId(), 1);
			}
	@Test
	public void addProductToCartInValid() throws Exception{
		Product p=new Product();
		p.setProductId(3);
		p.setQuantity(4);
		Cart cart=new Cart();
		cart.setProduct(p);
		cart.setQuantity(1);
		List<Cart> customerCart=new ArrayList<Cart>();
		customerCart.add(cart);
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.PRODUCT_PRESENT_IN_CART");
		
		Mockito.when(customerDao.getCustomerCarts(Mockito.anyString())).thenReturn(customerCart);
		Mockito.when(customerDao.getProductById(cart.getProduct().getProductId())).thenReturn(p);
		Mockito.doNothing().when(customerDao).addProductToCart(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());
		
		customerService.addProductToCart("abcd@abc.com", p.getProductId(), 1);
			}
	@Test
	public void addProductToCartInValidSufficientStock() throws Exception{
		Product p=new Product();
		p.setQuantity(1);
		p.setProductId(112);
		Cart cart=new Cart();
		cart.setProduct(p);
		cart.setQuantity(3);
	
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.OUT_OF_STOCK");
		
		Mockito.when(customerDao.getCustomerCarts(Mockito.anyString())).thenReturn(new ArrayList<Cart>());
		Mockito.when(customerDao.getProductById(cart.getProduct().getProductId())).thenReturn(p);
		Mockito.doNothing().when(customerDao).addProductToCart(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt());
		
		customerService.addProductToCart("abcd@abc.com", p.getProductId(), 3);
			}
	
	@Test public void testDeleteProductFromCartValid() throws Exception{
		Mockito.doNothing().when(customerDao).deleteProductFromCart(Mockito.anyString(), Mockito.anyInt());
		customerService.deleteProductFromCart("abcd@ab.com", 1);
	}
	
	@Test public void testModifyQuantityToCartValid() throws Exception{
		Product p=new Product();
		p.setProductId(3);
		p.setQuantity(4);
		Mockito.when(customerDao.getProductById(3)).thenReturn(p);
		Mockito.doNothing().when(customerDao).modifyQuantityOfProductInCart(Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt());
		customerService.modifyQuantityOfProductInCart("abcd@ab.com", 3, 3);
	}
	
	@Test public void testModifyQuantityToCartInValid() throws Exception{
		expectedException.expect(Exception.class);
		expectedException.expectMessage("CustomerService.OUT_OF_STOCK");
		Product p=new Product();
		p.setProductId(3);
		p.setQuantity(1);
		Mockito.when(customerDao.getProductById(3)).thenReturn(p);
		Mockito.doNothing().when(customerDao).modifyQuantityOfProductInCart(Mockito.anyString(),Mockito.anyInt(),Mockito.anyInt());
		customerService.modifyQuantityOfProductInCart("abcd@ab.com", 3, 3);
	}
}
