package com.cg.ima.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.ima.entities.Resource;
import com.cg.ima.exception.ResourceNotFoundException;
import com.cg.ima.repository.IResourceRepository;

@Service		//It indicates that this class is used to write business logic.
public class ResourceService implements IResourceService {

	@Autowired                //It injects object dependency implicitly.
	private IResourceRepository resourceRepository;		//Repository layer of resource entity.
	
	
	
	
	//this method is used to add a single resource.
	
	@Override
	public Resource addResource(Resource resource) {
		
		Resource r = resourceRepository.save(resource);
		return r;

	}

	
	//this method is used to update a single resource.
	
	@Override
	public Resource updateResource(Resource resource) {
		
		Resource r = resourceRepository.save(resource);
		return r;
		
	}
	
	
	//this method is used to delete a single resource.
	
	@Override
	public ResponseEntity<String> removeResource(int resId) throws ResourceNotFoundException {
		
		Optional<Resource> searchedResource = resourceRepository.findById(resId);

		if (!searchedResource.isPresent()) 
		{
			throw new ResourceNotFoundException("Resource ID '"+ resId +"' Doesn't Exists!");
		}
		else
		{
			resourceRepository.deleteById(resId);
			return ResponseEntity.ok("Resource Id '"+resId+"' has been deleted!");
		}
		
	}

	
	//this method is used to get a single resource.
	
	@Override
	public Resource getResourceById(int resId) throws ResourceNotFoundException {
		
		Optional<Resource> resource = resourceRepository.findById(resId);

		if (resource.isPresent()) 
		{
			return resource.get();
		} 
		else 
		{
			throw new ResourceNotFoundException("Resource ID '"+ resId +"' Not Found!");
		}
		
	}

	
	//this method is used to get all resources using category id.
	
	@Override
	public List<Resource> getAllResourcesByCategory(int catId) throws ResourceNotFoundException {
		
		List<Resource> resources = resourceRepository.findByCategory(catId);
		if (resources.isEmpty()) 
		{
			throw new ResourceNotFoundException("Resource For Category Id '"+ catId +"' Not Found!");
		}
		else
		{	
			return resources;
		}
		
	}


	//this method is used to get all resources.

	@Override
	public List<Resource> getAllResources() throws ResourceNotFoundException {
		
		List<Resource> resources = resourceRepository.findAll();
		if (resources.isEmpty()) 
		{
			throw new ResourceNotFoundException("No Resource Found!");
		}
		else
		{	
			return resources;
		}
	}


}
