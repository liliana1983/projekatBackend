package com.iktpreobuka.dataaccess.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class OfferEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	protected Integer Id;
	@Column
	protected String offerName;
	@Column
	protected String offerDescription;
	@Column
	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	protected LocalDate offerCreated;
	@Column
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	protected LocalDate offerExpires;
	@Column
	protected Double regularPrice;
	@Column
	protected Double actionPrice;
	@Column
	protected String imagePath;
	@Column
	protected Integer availableOffers;
	@Column
	protected Integer boughtOffers;
	@Column
	protected EuOfferStatus EuOfferStatus;
	
	@ManyToOne(cascade=CascadeType.REFRESH,fetch = FetchType.LAZY)
	@JoinColumn(name="user")
	private UserEntity user;
	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
	}



	@ManyToOne (cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="category")
	private CategoryEntity category;
	
	@OneToMany(mappedBy="offer",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JsonIgnore

	public List<BillEntity> bills;
	@OneToMany (mappedBy="offer",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JsonIgnore

	public List<VoucherEntity> vouchers;
	
	public List<BillEntity> getBills() {
		return bills;
	}

	public void setBills(List<BillEntity> bills) {
		this.bills = bills;
	}

	public List<VoucherEntity> getVouchers() {
		return vouchers;
	}

	public void setVouchers(List<VoucherEntity> vouchers) {
		this.vouchers = vouchers;
	}

	public OfferEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OfferEntity(Integer id, String offerName, String offerDescription, LocalDate offerCreated, LocalDate offerExpires,
			Double regularPrice, Double actionPrice, String imagePath, Integer availableOffers, Integer boughtOffers,
			EuOfferStatus EuOfferStatus) {
		super();
		this.Id = id;
		this.offerName = offerName;
		this.offerDescription = offerDescription;
		this.offerCreated = offerCreated;
		this.offerExpires = offerExpires;
		this.regularPrice = regularPrice;
		this.actionPrice = actionPrice;
		this.imagePath = imagePath;
		this.availableOffers = availableOffers;
		this.boughtOffers = boughtOffers;
		this.EuOfferStatus = EuOfferStatus;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		this.Id = id;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	public LocalDate getOfferCreated() {
		return offerCreated;
	}

	public void setOfferCreated(LocalDate localDate) {
		this.offerCreated = localDate;
	}

	public LocalDate getOfferExpires() {
		return offerExpires;
	}

	public void setOfferExpires(LocalDate localDate) {
		this.offerExpires = localDate;
	}

	public Double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(Double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public Double getActionPrice() {
		return actionPrice;
	}

	public void setActionPrice(Double actionPrice) {
		this.actionPrice = actionPrice;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Integer getAvailableOffers() {
		return availableOffers;
	}

	public void setAvailableOffers(Integer availableOffers) {
		this.availableOffers = availableOffers;
	}

	public Integer getBoughtOffers() {
		return boughtOffers;
	}

	public void setBoughtOffers(Integer boughtOffers) {
		this.boughtOffers = boughtOffers;
	}

	public EuOfferStatus getEuOfferStatus() {
		return EuOfferStatus;
	}

	public void setEuOfferStatus(EuOfferStatus euOfferStatus) {
		EuOfferStatus = euOfferStatus;
	}
}
