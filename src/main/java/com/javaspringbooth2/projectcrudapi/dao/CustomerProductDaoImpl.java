package com.javaspringbooth2.projectcrudapi.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaspringbooth2.projectcrudapi.entity.ProductEntity;
import com.javaspringbooth2.projectcrudapi.model.Cart;
import com.javaspringbooth2.projectcrudapi.model.Product;

@Repository(value="customerProductDao")
public class CustomerProductDaoImpl implements CustomerProductDao {
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Product> getAllProduct() {
		// TODO Auto-generated method stub
		Query query = entityManager.createQuery("select p from ProductEntity p");
		List<ProductEntity> ProductsEntities = query.getResultList();
		
		List<Product> listOfProducts = new ArrayList<Product>();
		for (ProductEntity productEntity : ProductsEntities) {
			Product p = new Product();
			p.setCategory(productEntity.getCategory());
			p.setName(productEntity.getName());
			p.setPrice(productEntity.getPrice());
			p.setQuantity(productEntity.getQuantity());
			p.setProductId(productEntity.getProductId());
			
			listOfProducts.add(p);
		}
		return listOfProducts;
	}

	
}
