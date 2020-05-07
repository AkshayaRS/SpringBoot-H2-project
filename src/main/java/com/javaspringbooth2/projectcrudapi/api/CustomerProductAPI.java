package com.javaspringbooth2.projectcrudapi.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.client.RestTemplate;

import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;
import com.javaspringbooth2.projectcrudapi.service.CustomerProductService;

@CrossOrigin
@RestController
@RequestMapping("/CustomerProductAPI")
public class CustomerProductAPI {
	@Autowired
	private CustomerProductService customerProductService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping(value = "getAllProducts")
	public ResponseEntity<List<Product>> getAllProducts() throws Exception{
		List<Product> products = null;
		try {
			products = customerProductService.getAllProducts();
			return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
	}
	
	@GetMapping(value= "getAnalytics")
	public ResponseEntity<String> getCountOfPurchase(){
		LocalDateTime fromDate= LocalDateTime.now();
		String message = "";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Cart[]> cartList = restTemplate.getForEntity("http://localhost:8081/CustomerCartAPI/getAllCartItems", Cart[].class);
		Cart[] cartList1 =cartList.getBody();
		List<String> items = new ArrayList<String>();
		for (Cart cartItem : cartList1) {
			if (cartItem.getDateToOrder().isAfter(fromDate.minusDays(2))) {
				items.add(cartItem.getProduct().getName());
			}
		}
		
		Map<String, Integer> products = new HashMap<String, Integer>();
		Set<String> userSet = new LinkedHashSet<>();
		userSet.addAll(items);
		
		for (String product : userSet) {
			int count =0;
			
			for (String product2 : items) {
				if(product.equals(product2))
					count++;
			}
			products.put(product, count);
			message = message + product + " : " + count + "\n"; 
		}
		
		String max = products.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.get().getKey();

		return new ResponseEntity<String>("Product "+max+" is maximum bought product!!! with price: "+"\n Product List for last 2 days purchased\n"+message,HttpStatus.OK);
	}
}
