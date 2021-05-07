package com.cg.ima.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryExistsException;
import com.cg.ima.exception.CategoryNotFoundException;

public interface ICategoryService {
	
	public Category addCategory(Category category) throws CategoryExistsException;
	
	public Category updateCategory(Category category);
	
	public ResponseEntity<String> removeCategory(int catId) throws CategoryNotFoundException;
	
	public List<Category> getAllCategories() throws CategoryNotFoundException;

	public Category getCategoryByName(String catName) throws CategoryNotFoundException;

	public Category getCategoryById(int catId) throws CategoryNotFoundException;

}
