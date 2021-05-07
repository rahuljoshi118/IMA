package com.cg.ima.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.ima.entities.Resource;
import com.cg.ima.exception.ResourceNotFoundException;

public interface IResourceService {

	public Resource addResource(Resource resource);
	
	public Resource updateResource(Resource resource);
	
	public ResponseEntity<String> removeResource(int resId) throws ResourceNotFoundException;
	
	public Resource getResourceById(int resId) throws ResourceNotFoundException;
	
	public List<Resource> getAllResourcesByCategory(int catId) throws ResourceNotFoundException;
	
	public List<Resource> getAllResources() throws ResourceNotFoundException;
	
}
