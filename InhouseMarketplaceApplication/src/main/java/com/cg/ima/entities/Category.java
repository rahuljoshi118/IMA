package com.cg.ima.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity						// It defines that a class can be mapped to a database table
@Table(name = "category")	// user define table name
public class Category {

	@Id		// define primary key
	@Column(name = "category_id")		// define column name
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="category_seq")
	@SequenceGenerator(name="category_seq",sequenceName="category_seq", allocationSize=1)
	private int catId;

	@Column(name = "category_name")
	@NotBlank(message="Category name should not be blank.")		// for validation
	private String catName;


	//default constructor of class Category
	
	public Category() {
		super();
	}

	
	//parameterized constructor of class Category
	
	public Category(int catId, String catName) {
		super();
		this.catId = catId;
		this.catName = catName;
	}
	
	

	//getters and setters
	
	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}


	
	//toString()
	
	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + "]";
	}
	


}
