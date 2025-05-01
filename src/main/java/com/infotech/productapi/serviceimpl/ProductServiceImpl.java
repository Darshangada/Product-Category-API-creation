package com.infotech.productapi.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.infotech.productapi.dto.Category;
import com.infotech.productapi.dto.Product;
import com.infotech.productapi.entity.CategoryEntity;
import com.infotech.productapi.entity.ProductEntity;
import com.infotech.productapi.exception.ResourceNotFoundException;
import com.infotech.productapi.repository.CategoryRepository;
import com.infotech.productapi.repository.ProductRepository;
import com.infotech.productapi.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository repo;

	@Autowired
	private CategoryRepository categoryRepo;

	private final ModelMapper mapper;

	@Autowired(required = true)
	public ProductServiceImpl(ModelMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Page<Product> getAllProducts(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return repo.findAll(pageable).map(p -> {
			Product dto = mapper.map(p, Product.class);
			return dto;
		});
	}

	@Override
	public Product getProductById(Long id) {
		ProductEntity product = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		Product dto = mapper.map(product, Product.class);
		dto.setCategoryId(product.getCategory().getId());
		return dto;
	}

	@Override
	public Product createProduct(Product dto) {
		ProductEntity productEntity = mapper.map(dto, ProductEntity.class);
		CategoryEntity category = categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		productEntity.setCategory(category);
		ProductEntity savedEntity = repo.save(productEntity);
		return mapper.map(savedEntity, Product.class);
	}

	@Override
	public Product updateProduct(Long id, Product productDto) {
		ProductEntity product = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		if (!productDto.getCategoryId().equals(product.getCategory().getId())) {
			CategoryEntity newCategory = categoryRepo.findById(productDto.getCategoryId())
					.orElseThrow(() -> new ResourceNotFoundException("Category not found"));
			product.setCategory(newCategory);
		}
		return mapper.map(repo.save(product), Product.class);
	}

	@Override
	public void deleteProduct(Long id) {
		repo.deleteById(id);
	}
}