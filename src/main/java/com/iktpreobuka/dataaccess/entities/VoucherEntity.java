package com.iktpreobuka.dataaccess.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class VoucherEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
protected Integer Id;
	@Column
protected LocalDate expirationDate;
	@Column
protected Boolean isUsed;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn (name="offer")
	private OfferEntity offer;
	@ManyToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="user")
	private UserEntity user;
	
	public VoucherEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public VoucherEntity(Integer id, LocalDate expirationDate, Boolean isUsed) {
		super();
		Id = id;
		this.expirationDate = expirationDate;
		this.isUsed = isUsed;
	}
	public OfferEntity getOffer() {
		return offer;
	}
	public void setOffer(OfferEntity offer) {
		this.offer = offer;
	}
	public UserEntity getUser() {
		return user;
	}
	public void setUser(UserEntity user) {
		this.user = user;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Boolean getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	
}
