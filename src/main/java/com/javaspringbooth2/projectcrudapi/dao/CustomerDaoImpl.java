package com.javaspringbooth2.projectcrudapi.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaspringbooth2.projectcrudapi.entity.CartEntity;
import com.javaspringbooth2.projectcrudapi.entity.CustomerEntity;
import com.javaspringbooth2.projectcrudapi.entity.ProductEntity;
import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;

@Repository(value= "customerDao")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private EntityManager entityManager;
	

	@Override
	public void addProductToCart(String customerEmailId, Integer productId,Integer quantity) {
		System.out.println("In customer dao...........");
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		Integer remainingQ = productEntity.getQuantity()-quantity;
		productEntity.setQuantity(remainingQ);
		entityManager.persist(productEntity);
		
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		
		CartEntity cartEntity = new CartEntity();
		cartEntity.setProductEntity(productEntity);
		cartEntity.setQuantity(quantity);
		cartEntity.setDateoforder(LocalDateTime.now());
		entityManager.persist(cartEntity);
		
		customerEntity.getcustomerCarts().add(cartEntity);
		
		entityManager.persist(customerEntity);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Cart> getCustomerCarts(String customerEmailId) {
		// TODO Auto-generated method stub
		
		CustomerEntity customerEntity =entityManager.find(CustomerEntity.class, customerEmailId);
		List<CartEntity> cartEntities = customerEntity.getcustomerCarts();
		
		List<Cart> listOfcartProds = new ArrayList<Cart>();
		
		for (CartEntity cartEntity : cartEntities) {
			Cart cart =new Cart();
			cart.setCartId(cartEntity.getCartId());
			
			Product product = new Product();
			product.setCategory(cartEntity.getProductEntity().getCategory());
			product.setName(cartEntity.getProductEntity().getName());
			product.setPrice(cartEntity.getProductEntity().getPrice());
			product.setProductId(cartEntity.getProductEntity().getProductId());
			product.setQuantity(cartEntity.getProductEntity().getQuantity());
			
			cart.setProduct(product);
			cart.setQuantity(cartEntity.getQuantity());
			cart.setDateToOrder(cartEntity.getDateoforder());
			
			listOfcartProds.add(cart);
		}
		return listOfcartProds;
	}

	@Override
	public void modifyQuantityOfProductInCart(String customerEmailId,Integer productId, Integer quantity) {
		// TODO Auto-generated method stub
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		
		//Integer cartId = 0;
		List<CartEntity> cartEntities = customerEntity.getcustomerCarts();
		for (CartEntity cartEntity : cartEntities) {
			if (productId.equals(cartEntity.getProductEntity().getProductId())) {
				//cartId = cartEntity.getCartId();
				
				Integer previousQuanity = cartEntity.getQuantity();
				cartEntity.setQuantity(quantity);
				cartEntity.setDateoforder(LocalDateTime.now());
				entityManager.persist(cartEntity);
				
				Integer remainingQ = (productEntity.getQuantity()+previousQuanity)-quantity;
				productEntity.setQuantity(remainingQ);

			}
		}
		//CartEntity cartEntityToModify = entityManager.find(CartEntity.class, cartId);

	}

	@Override
	public void deleteProductFromCart(String customerEmailId, Integer productId) {
		// TODO Auto-generated method stub
		CustomerEntity customerEntity = entityManager.find(CustomerEntity.class, customerEmailId);
		
		CartEntity cartEntityToRemove = null;
		
		List<CartEntity> cartEntities = customerEntity.getcustomerCarts();
		Integer quantity = null;
		for (CartEntity cartEntity : cartEntities) {
			if (cartEntity.getProductEntity().getProductId().equals(productId)) {
				cartEntity.setProductEntity(null);
				cartEntityToRemove = cartEntity;
				quantity = cartEntity.getQuantity();
			}
			
		}	
		
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		productEntity.setQuantity(productEntity.getQuantity()+quantity);
		
		cartEntities.remove(cartEntityToRemove);
		CartEntity cartEntity = entityManager.find(CartEntity.class, cartEntityToRemove.getCartId());
		entityManager.remove(cartEntity);
		
	}

	@Override
	public Product getProductById(Integer productId) {
		// TODO Auto-generated method stub
		ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
		
		Product product = new Product();
		product.setCategory(productEntity.getCategory());
		product.setName(productEntity.getName());
		product.setPrice(productEntity.getPrice());
		product.setProductId(productEntity.getProductId());
		product.setQuantity(productEntity.getQuantity());
		
		return product;
	}

}
