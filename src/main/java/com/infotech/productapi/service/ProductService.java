package com.infotech.productapi.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.infotech.productapi.dto.Product;

public interface ProductService {

	 Page <Product> getAllProducts(int page, int size);
	 
	 Product getProductById(Long id);
	 
	 Product createProduct(Product dto);
	 
	 Product updateProduct(Long id, Product dto);
	 
	 void deleteProduct(Long id);
}
