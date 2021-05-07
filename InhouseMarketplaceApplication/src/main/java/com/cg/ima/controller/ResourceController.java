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

import com.cg.ima.entities.Resource;
import com.cg.ima.exception.InvalidInputException;
import com.cg.ima.exception.ResourceNotFoundException;
import com.cg.ima.service.ResourceService;

@RestController					//It is used to create RESTful web services using Spring MVC, Once response body is generated from the handler method, it converts it to JSON or XML response.
@RequestMapping("/ima")			//This maps HTTP requests to handler methods of MVC and REST controllers.
public class ResourceController {
	
	@Autowired					//It injects object dependency implicitly.
	private ResourceService resourceService;				//Service layer of resource entity.

	
	
	
// if we want to post(insert) multiple resources	
//	@PostMapping(path = "/resource")
//	public List<Resource> addResource(@RequestBody List<Resource> resources) {
//		
//		for(Resource r: resources)
//		{
//			Resource res = resourceService.addResource(r);
//		}		
//		return resources;
//		
//	}

	
	
	// if we want to post(insert) a single resource
	
	@PostMapping(path = "/resource")			//http://localhost:8000/ima/resource
	public Resource addResource(@Valid @RequestBody Resource res, BindingResult bindingResult) throws InvalidInputException {
	
		
		// It is used to throw invalid input exception for default field message.
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		}
		
		Resource resource = resourceService.addResource(res);			// calls service layer addResource() method.	
		return resource;
		
	}
	
	
	
	// if we want to put(update) a single resource
	
	@PutMapping(path = "/resource")				//http://localhost:8000/ima/resource
	public Resource updateResource(@Valid @RequestBody Resource res, BindingResult bindingResult) throws InvalidInputException {
		
		
		// It is used to throw invalid input exception for default field message.
		if (bindingResult.hasErrors()) 
		{
			 List<FieldError> errors = bindingResult.getFieldErrors();
			 for (FieldError error : errors ) 
			 {
				 throw new InvalidInputException(error.getObjectName() + " - " + error.getDefaultMessage());
			 }
		}
		
		Resource resource = resourceService.updateResource(res);			// calls service layer updateResource() method.
		return resource;
		
	}

	
	
	// if we want to delete(remove) a single resource
	
	@DeleteMapping(path = "/resource/{resId}")			//http://localhost:8000/ima/resource/10
	public ResponseEntity<String> removeResource(@PathVariable("resId") int resId) throws ResourceNotFoundException {
		
		return resourceService.removeResource(resId);			// calls service layer removeResource() method.

	}
	
	
	
	// if we want to get(fetch) a single resource

	@GetMapping(path = "/resource/{resId}") 			//http://localhost:8000/ima/resource/10
	public Resource getResourceById(@PathVariable("resId") int resId) throws ResourceNotFoundException {
	
		Resource resource = resourceService.getResourceById(resId);			// calls service layer getResourceById() method.
		return resource;
		
	}

	
	
	// if we want to get(fetch) all resources with categoryId	
	
	@GetMapping(path = "/resource/category/{catId}")			//http://localhost:8000/ima/resource/category/10
	public List<Resource> getAllResourcesByCategory(@PathVariable("catId") int catId) throws ResourceNotFoundException {
		
		List<Resource> resources = resourceService.getAllResourcesByCategory(catId);			// calls service layer getAllResourcesByCategory() method.
		return resources;
		
	}
	

	
	// if we want to get(fetch) all resources
	
	@GetMapping(path = "/resource")					//http://localhost:8000/ima/resource
	public List<Resource> getAllResources() throws ResourceNotFoundException {
		
		List<Resource> resources = resourceService.getAllResources();					// calls service layer getAllResources() method.
		return resources;
		
	}

}
