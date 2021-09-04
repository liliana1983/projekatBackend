package com.iktpreobuka.dataaccess.services;

import java.time.LocalDate;
import java.util.List;

import com.iktpreobuka.dataaccess.entities.OfferEntity;

public interface OfferService {
public OfferEntity changeAvailableBoughtOfferBuy(Integer id);
	
	public OfferEntity changeAvailableBoughtOfferCanceled(Integer id);
	
	public Boolean checkIfOfferExpires(LocalDate offerExpires,Integer id);
	
}
