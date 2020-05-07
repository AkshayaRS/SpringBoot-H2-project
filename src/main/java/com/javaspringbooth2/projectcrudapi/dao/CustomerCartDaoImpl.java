package com.javaspringbooth2.projectcrudapi.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaspringbooth2.projectcrudapi.entity.CartEntity;
import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;

@Repository(value = "customerCartDao")
public class CustomerCartDaoImpl implements CustomerCartDao {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Cart> getAllCartItems() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select p from CartEntity p");
		List<CartEntity> ProductsEntities = query.getResultList();
		
		List<Cart> ListOfCartItems = new ArrayList<Cart>();
		for (CartEntity cartEntity : ProductsEntities) {
			Cart c = new Cart();
			c.setCartId(cartEntity.getCartId());
			
			Product p = new Product();
			p.setCategory(cartEntity.getProductEntity().getCategory());
			p.setName(cartEntity.getProductEntity().getName());
			p.setPrice(cartEntity.getProductEntity().getPrice());
			p.setProductId(cartEntity.getProductEntity().getProductId());
			p.setQuantity(cartEntity.getProductEntity().getQuantity());
			
			c.setProduct(p);
			c.setQuantity(cartEntity.getQuantity());
			c.setDateToOrder(cartEntity.getDateoforder());
			ListOfCartItems.add(c);
			
		}
		
		return ListOfCartItems;
		
	}
	
}
