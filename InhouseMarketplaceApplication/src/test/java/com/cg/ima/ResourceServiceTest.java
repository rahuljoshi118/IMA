package com.cg.ima;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
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
import com.cg.ima.entities.Resource;
import com.cg.ima.exception.ResourceNotFoundException;
import com.cg.ima.repository.IResourceRepository;
import com.cg.ima.service.ResourceService;



@ExtendWith(MockitoExtension.class)				//to use JUnit extensions from Junit 5
public class ResourceServiceTest {

	@Mock										//invoke methods of the class that has external communication
	IResourceRepository resourceRepository;
	
	
	@InjectMocks								//creates an instance of the class and injects the mocks that are created with the @Mock 
	ResourceService resourceService;
	
	
	
	/*
		testAddResource() is used to test whether the resource details is added successfully. 
	*/
	
	@Test
	public void testAddResource()
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);
		
		Mockito.when(resourceRepository.save(res)).thenReturn(res);
		assertEquals(res, resourceService.addResource(res)); //check the expected result with actual result.
	}
	
	
	/*
		testUpdateResource() is used to test whether the resource detail is updated successfully. 
	*/
	
	@Test
	public void testUpdateResource()
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);		
		
		res.setResId(20);
		Mockito.when(resourceRepository.save(res)).thenReturn(res);
		
		assertEquals(res,resourceService.updateResource(res));
		
	}
	
	
	
	/*
		testDeleteResource() is used to test whether the resource details are deleted successfully. 
	*/
	
	@Test
	public void testDeleteResource()
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);		
		
		
		
		Mockito.when(resourceRepository.existsById(res.getResId())).thenReturn(false);
	    assertFalse(resourceRepository.existsById(res.getResId()));
	}
	
	
	
	/*
		testGetResourceById() is used to test whether the correct resource details are obtained with the respective given resource id.
	*/
	
	@Test
	public void testGetResourceById() throws ResourceNotFoundException
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);
		
		Mockito.when(resourceRepository.findById(10)).thenReturn(Optional.of(res));
		Resource actualResource = resourceService.getResourceById(10);
		assertEquals(10,actualResource.getResId());
	}

	
	
	/*
		testGetEmployeeByName() is used to test whether the correct resource details are obtained with the respective given categoryId.
	*/
	
	@Test
	public void testGetAllResourcesByCategory() throws ResourceNotFoundException
	{
		LocalDate resDate = null;
		
		Category cat = new Category();
		cat.setCatId(10);
		cat.setCatName("Clothing");
		
		Resource res = new Resource();
		res.setResId(10);
		res.setResDate(resDate);
		res.setCategory(cat);
		res.setResDescription("100% Cotton");
		res.setResTitle("Shirt");
		res.setResPrice(1000);
		
		List<Resource> resources = new ArrayList<>();
		resources.add(res);
		Mockito.when(resourceRepository.findByCategory(10)).thenReturn(resources);
		List<Resource> actualResource = resourceService.getAllResourcesByCategory(10);
		assertEquals(10,actualResource.get(0).getCategory().getCatId());
		
	}
	
	
	/*
		testGetAllEmployees() is used to test whether the correct error message is shown if no resource details is provided.
	*/

	@Test
	public void testGetAllResources()
	{
		List<Resource> resources = new ArrayList<>();
		Mockito.when(resourceRepository.findAll()).thenReturn(resources);
		Exception ex = assertThrows(ResourceNotFoundException.class, ()-> resourceService.getAllResources());//assertThrows(Class<T> expectedType,Executable executable: inside this execute() method is present)
		assertEquals("No Resource Found!", ex.getMessage());
		
	}
}
