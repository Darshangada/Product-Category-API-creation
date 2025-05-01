package com.infotech.productapi.service;

import org.springframework.data.domain.Page;

import com.infotech.productapi.dto.Category;

public interface CategoryService {

	 Page<Category> getAll(int page, int size);
	 
	 Category getById(Long id);
	 
	 Category create(Category dto);
	 
	 Category update(Long id, Category dto);
	 
	 void delete(Long id);
}
