package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.ima.entities.Category;
import com.cg.ima.exception.CategoryExistsException;
import com.cg.ima.exception.CategoryNotFoundException;
import com.cg.ima.repository.ICategoryRepository;
import com.cg.ima.service.CategoryService;


@ExtendWith(MockitoExtension.class)			//to use JUnit extensions from Junit 5
public class CategoryServiceTest {

	@Mock				 //invoke methods of the class that has external communication
	ICategoryRepository categoryRepository;
	
	
	@InjectMocks		//creates an instance of the class and injects the mocks that are created with the @Mock 
	CategoryService categoryService;
	
	
	/*
		testAddCategory() is used to test whether the category details is added successfully. 
	*/
	
	@Test				// tells JUnit that the method attached can be run as a test case
	public void testAddCategory() throws CategoryExistsException
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		

		Mockito.when(categoryRepository.save(cat)).thenReturn(cat);
		assertEquals(cat, categoryService.addCategory(cat));  //check the expected result with actual result.
	}
	
	
	
	/*
		testUpdateCategory() is used to test whether the category details is updated successfully. 
	*/
	
	@Test
	public void testUpdateCategory()
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");		
		
		//updating catName
		cat.setCatName("Electronics");
		
		Mockito.when(categoryRepository.save(cat)).thenReturn(cat);
		assertEquals(cat,categoryService.updateCategory(cat));
		
	}
	
	
	/*
		testDeleteCategory() is used to test whether the category details are deleted successfully. 
	*/

	@Test
	public void testDeleteCategory()
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		
		Mockito.when(categoryRepository.existsById(cat.getCatId())).thenReturn(false);
	    assertFalse(categoryRepository.existsById(cat.getCatId()));
	}
	
	
	/*
		testGetCategoryById() is used to test whether the correct category details are obtained with the respective given category id. 
	*/

	
	@Test
	public void testGetCategoryById() throws CategoryNotFoundException
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		
		Mockito.when(categoryRepository.findById(10)).thenReturn(Optional.of(cat));
		Category actualcat = categoryService.getCategoryById(10);
		assertEquals(10,actualcat.getCatId());
	}

	
	/*
		testGetCategoryByName() is used to test whether the correct category details are obtained with the respective given category name. 
	*/
	
	@Test
	public void testGetCategoryByName() throws CategoryNotFoundException
	{
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Mockito.when(categoryRepository.findByName("Clothing")).thenReturn(Optional.of(cat));
		Category actualCat = categoryService.getCategoryByName("Clothing");
		assertEquals("Clothing",actualCat.getCatName());
	}
	

	/*
		testGetAllCategories() is used to test whether the correct error message is shown if no category is provided.
	*/
	
	@Test
	public void testGetAllCategories()
	{
		List<Category> categories = new ArrayList<>();
		Mockito.when(categoryRepository.findAll()).thenReturn(categories);
		Exception ex = assertThrows(CategoryNotFoundException.class, ()-> categoryService.getAllCategories());			//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Category Found!", ex.getMessage());
		
	}
}
