package com.cg.ima.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.ima.entities.Category;

@Repository // It is for data CRUD operations and also known as DAO (Data Access Object)
public interface ICategoryRepository extends JpaRepository<Category, Integer> {
	
	// Custom JPQL query to find category name
	@Query("from Category where category_name like %:catName%") // JPQL -> table name=Entity class
    Optional<Category> findByName(@Param("catName") String catName);
	
	//Custom JPQL query to find specific category
	@Query("from Category where category_name like %:catName%") // JPQL -> table name=Entity class
    Category findBySpecificCategoryName(@Param("catName") String catName);


}
