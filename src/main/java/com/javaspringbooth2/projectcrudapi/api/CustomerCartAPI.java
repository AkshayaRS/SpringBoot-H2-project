package com.javaspringbooth2.projectcrudapi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.service.CustomerCartService;


@CrossOrigin
@RestController
@RequestMapping("/CustomerCartAPI")
public class CustomerCartAPI {
	@Autowired
	private CustomerCartService customerCartService;
	
	
	@GetMapping(value = "getAllCartItems")
	public ResponseEntity<List<Cart>> getAllCartItems() throws Exception{
		List<Cart> items = null;
		try {
			items = customerCartService.getAllCartItems();
			return new ResponseEntity<List<Cart>>(items,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
			}
	}
}
