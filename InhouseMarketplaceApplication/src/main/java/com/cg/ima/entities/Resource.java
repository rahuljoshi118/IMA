package com.cg.ima.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity					  // It defines that a class can be mapped to a database table
@Table(name = "resource") // define table name
public class Resource {

	@Id    // define primary key
	@Column(name = "resource_id") // define column name
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="resource_seq")
	@SequenceGenerator(name="resource_seq",sequenceName="resource_seq", allocationSize=1)
	private int resId;

	@Column(name = "resource_title")
	@NotBlank(message="Resource title should not be blank.") //For Validation
	private String resTitle;
	
	@Column(name = "resource_description")
	private String resDescription;

	@Column(name = "resource_price")
	@Digits(integer=6, fraction=2, message = "invalid price.") //For Validation
	private double resPrice;

	@Column(name = "resource_date")
	private LocalDate resDate;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id")
	private Category category;


	//  Bi-Directional ManyToMany Relation

	@JsonIgnore // It is used at field level to mark a property or list of properties to be ignored.
	@ManyToMany(fetch=FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "resources")
	private List<Order> orders;

	
	/*
	 * (mappedBy) signals hibernate that the key for the relationship is on the other side.
	 * (FetchType.EAGER) will retrieve everything
	 * (FetchType.LAZY) will retrieve on request
	 * (CascadeType.REMOVE) will remove data from related table also.
	 */
	
	

	//Default constructor of class Resource
	
	public Resource() {
		super();
	}

	//Parameterized constructor of class Resource
	
	public Resource(int resId, String resTitle, String resDescription, double resPrice, LocalDate resDate, Category category,
			List<Order> orders, Employee employee) {
		super();
		this.resId = resId;
		this.resTitle = resTitle;
		this.resDescription = resDescription;
		this.resPrice = resPrice;
		this.resDate = resDate;
		this.category = category;
		this.orders = orders;
	}

	//Getters and Setters 
	public int getResId() {
		return resId;
	}

	public void setResId(int resId) {
		this.resId = resId;
	}

	public String getResTitle() {
		return resTitle;
	}

	public void setResTitle(String resTitle) {
		this.resTitle = resTitle;
	}

	public String getResDescription() {
		return resDescription;
	}

	public void setResDescription(String resDescription) {
		this.resDescription = resDescription;
	}

	public double getResPrice() {
		return resPrice;
	}

	public void setResPrice(double resPrice) {
		this.resPrice = resPrice;
	}

	public LocalDate getResDate() {
		return resDate;
	}

	public void setResDate(LocalDate resDate) {
		this.resDate = resDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	//toString()
	@Override
	public String toString() {
		return "Resource [resId=" + resId + ", resTitle=" + resTitle + ", resDescription=" + resDescription
				+ ", resPrice=" + resPrice + ", resDate=" + resDate + ", category=" + category + ", orders=" + orders
				+ "]";
	}
	
	
	

}
