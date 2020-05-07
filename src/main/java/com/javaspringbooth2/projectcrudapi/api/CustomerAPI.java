package com.javaspringbooth2.projectcrudapi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;
import com.javaspringbooth2.projectcrudapi.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/CustomerAPI")
public class CustomerAPI {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private Environment environment;
	
	
	@PostMapping(value = "addProductToCart/{customerEmailId}/{quantity}")
	public ResponseEntity<String> addProductToCart(@PathVariable("customerEmailId") String customerEmailId, @PathVariable Integer quantity, @RequestBody Product product) throws Exception {
		try
		{
			System.out.println("Customer add api .................");
			customerService.addProductToCart(customerEmailId,product.getProductId(), quantity);
			
			String message = environment.getProperty("CustomerAPI.PRODUCT_ADDED_TO_CART");
			
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
			
		}
		catch (Exception e) {
			String s=environment.getProperty(e.getMessage());
			return new ResponseEntity<>(s,HttpStatus.BAD_REQUEST);
			
			//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
	
	@GetMapping(value = "getCustomerCart/{customerEmailId}")
	public ResponseEntity<List<Cart>> getCustomerCart(@PathVariable("customerEmailId") String customerEmailId) throws Exception{
	List<Cart> list = null;	
		try
		{
			
			list = customerService.getCustomerCarts(customerEmailId);
			ResponseEntity<List<Cart>> response=new ResponseEntity<List<Cart>>(list, HttpStatus.OK);
			return response;
		}
		catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	
	
	@PostMapping(value = "modifyQuantityOfProductInCart/{customerEmailId}/{quantity}")
	public ResponseEntity<String> modifyQuantityOfProductInCart(@PathVariable String customerEmailId, @PathVariable Integer quantity, @RequestBody Product product) throws Exception{
		
		try
		{	
			customerService.modifyQuantityOfProductInCart(customerEmailId, product.getProductId(),quantity);
			
			String message = environment.getProperty("CustomerAPI.QUANTITY_UPDATE_SUCCESS");
			
			return new ResponseEntity<String>(message, HttpStatus.CREATED);
		}
        catch (Exception e) {
			
        	String s=environment.getProperty(e.getMessage());
			return new ResponseEntity<>(s,HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PostMapping(value = "deleteProductFromCart/{customerEmailId}")
	public ResponseEntity<String> deleteProductFromCart(@PathVariable("customerEmailId") String customerEmailId, @RequestBody Product product) throws Exception{
		
		try
		{
			customerService.deleteProductFromCart(customerEmailId, product.getProductId());
			
			String message = environment.getProperty("CustomerAPI.PRODUCT_DELETED_FROM_CART_SUCCESS");
			
			return new ResponseEntity<String>(message, HttpStatus.OK);
		}
		catch (Exception e) {
			
			String s=environment.getProperty(e.getMessage());
		
			return new ResponseEntity<>(s,HttpStatus.BAD_REQUEST);
		}
	}
}


