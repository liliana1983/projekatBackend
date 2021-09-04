package com.iktpreobuka.dataaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.OfferEntity;

public interface OfferRepository extends CrudRepository<OfferEntity,Integer> {
	public List<OfferEntity> findByActionPriceBetween(Double lowerPrice, Double upperPrice);
	//public OfferEntity findOfferByCategoryId(Integer catId);
}
