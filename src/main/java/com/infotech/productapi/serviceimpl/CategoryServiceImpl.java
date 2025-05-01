package com.infotech.productapi.serviceimpl;

import org.springframework.data.domain.Pageable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.infotech.productapi.dto.Category;
import com.infotech.productapi.entity.CategoryEntity;
import com.infotech.productapi.exception.ResourceNotFoundException;
import com.infotech.productapi.repository.CategoryRepository;
import com.infotech.productapi.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository repo;
	
    private final ModelMapper mapper;
    
    @Autowired(required = true)
    public CategoryServiceImpl(ModelMapper mapper) {
    	this.mapper=mapper;
    }

    @Override
    public Page<Category> getAll(int page, int size) {
        org.springframework.data.domain.Pageable pageable = PageRequest.of(page, size);
        return repo.findAll(pageable).map(cat -> mapper.map(cat, Category.class));
    }

    @Override
    public Category getById(Long id) {
        CategoryEntity cat = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return mapper.map(cat, Category.class);
    }

    @Override
    public Category create(Category category) {
        CategoryEntity entity = mapper.map(category, CategoryEntity.class);
        CategoryEntity savedEntity = repo.save(entity);
        return mapper.map(savedEntity, Category.class);
    }
    @Override
    public Category update(Long id, Category category) {
        CategoryEntity cat = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        cat.setName(category.getName());
        return mapper.map(repo.save(cat), Category.class);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
