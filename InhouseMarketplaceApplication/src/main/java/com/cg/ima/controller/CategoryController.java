package com.cg.ima.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryExistsException;
import com.cg.ima.exception.CategoryNotFoundException;
import com.cg.ima.exception.InvalidInputException;
import com.cg.ima.service.CategoryService;

@RestController               //It is used to create RESTful web services using Spring MVC, Once response body is generated from the handler method, it converts it to JSON or XML response.
@RequestMapping("/ima")       //This maps HTTP requests to handler methods of MVC and REST controllers.
public class CategoryController {
	
	@Autowired                //It injects object dependency implicitly.
	private CategoryService categoryService;        //Service layer of category entity.

	
	
// if we want to post(insert) multiple categories	
//		@PostMapping(path = "/category")
//		public List<Category> addCategory(@RequestBody List<Category> categories) {
//			
//			for(Category c: categories)
//			{
//				Category cat = categoryService.addCategory(c);
//			}		
//			return categories;
//			
//		}

	
	// if we want to post(insert) a single category
	
	@PostMapping(path = "/category")      //http://localhost:8000/ima/category
	public Category addCategory(@Valid @RequestBody Category category, BindingResult bindingResult) throws InvalidInputException, CategoryExistsException {
	
		
		// It is used to throw invalid input exception for default field message.
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		
		}
		
		Category c = categoryService.addCategory(category); 	// calls service layer addCategory() method.	
		return c;
		
	}
	
	
	
	
	// if we want to put(update) a single category
	
	@PutMapping(path = "/category") 	//http://localhost:8000/ima/category
	public Category updateCategory(@Valid @RequestBody Category category, BindingResult bindingResult) throws InvalidInputException {
		
		// It is used to throw invalid input exception for default field message.
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
			 
		}
		
		Category c = categoryService.updateCategory(category); 		// calls service layer updateCategory() method.
		return c;
		
	}

	
	
	
	// if we want to delete(remove) a single category
	
	@DeleteMapping(path = "/category/{catId}") 		//http://localhost:8000/ima/category/10
	public ResponseEntity<String> removeCategory(@PathVariable("catId") int catId) throws CategoryNotFoundException {
		
		return categoryService.removeCategory(catId); 		// calls service layer removeCategory() method.

	}
	
	
	
	// if we want to get(fetch) a single category

	@GetMapping(path = "/category/{catId}") 	//http://localhost:8000/ima/category/10
	public Category getCategoryById(@PathVariable("catId") int catId) throws CategoryNotFoundException {
	
		Category category = categoryService.getCategoryById(catId); 	// calls service layer getCategoryById() method.
		return category;
		
	}

	
	
	
//if we want all categories using like query	
//	@GetMapping(path = "/category/name/{catName}")
//	public List<Category> getAllCategoriesByName(@PathVariable("catName") String catName) {
//		
//		List<Category> categories = categoryService.getAllCategoriesByName(catName);
//		return categories;
//	
//	}
	
	
	

	// if we want to get(fetch) a single category by their full name	
	
	@GetMapping(path = "/category/name/{catName}") 		//http://localhost:8000/ima/category/name/Clothing
	public Category getCategoryByName(@PathVariable("catName") String catName) throws CategoryNotFoundException {
		
		Category category = categoryService.getCategoryByName(catName); 	// calls service layer getCategoryByName() method.
		return category;	
		
	}

	
	
	// if we want to get(fetch) all categories
	
	@GetMapping(path = "/category") 	//http://localhost:8000/ima/category
	public List<Category> getAllCategories() throws CategoryNotFoundException {
		
		List<Category> categories = categoryService.getAllCategories(); 	// calls service layer getAllCategories() method.
		return categories;
		
	}

}
