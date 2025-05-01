package com.infotech.productapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infotech.productapi.dto.Category;
import com.infotech.productapi.serviceimpl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryServiceImpl service;

	@GetMapping
	public Page<Category> getAll(@RequestParam(defaultValue = "0") int page) {
		return service.getAll(page, 10);
	}

	@PostMapping
	public Category create(@RequestBody @Validated Category dto) {
		return service.create(dto);
	}

	@GetMapping("/{id}")
	public Category get(@PathVariable Long id) {
		return service.getById(id);
	}

	@PutMapping("/{id}")
	public Category update(@PathVariable Long id, @RequestBody @Validated Category dto) {
		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		service.delete(id);
		return "The Category is delete Successfully";

	}
}