package com.iktpreobuka.dataaccess.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer Id;
	@Column
	protected String categoryName;
	@Column
	protected String categoryDescription;
	
	@OneToMany(mappedBy="category",cascade=CascadeType.REFRESH, fetch= FetchType.LAZY)
	@JsonIgnore
	private List<OfferEntity> offer;
	
	@OneToMany(mappedBy="category", cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JsonIgnore
	public List<BillEntity> bills;
	
	public CategoryEntity() {
		super();
	}
	public CategoryEntity(Integer id, String categoryName, String categoryDescription) {
		super();
		Id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
	}
	public List<OfferEntity> getOffer() {
		return offer;
	}
	public void setOffer(List<OfferEntity> offer) {
		this.offer = offer;
	}
	public List<BillEntity> getBills() {
		return bills;
	}
	public void setBills(List<BillEntity> bills) {
		this.bills = bills;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
}
