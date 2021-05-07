package com.cg.ima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entities.Resource;

@Repository				// It is for data CRUD operations and also known as DAO (Data Access Object)
public interface IResourceRepository extends JpaRepository<Resource, Integer> {
	
	// Custom JPQL query to find list of resources using categoryID
	@Query("from Resource where category_id = :catId") 			// JPQL -> table name=Entity class
	List<Resource> findByCategory(@Param("catId") int catId);

}
