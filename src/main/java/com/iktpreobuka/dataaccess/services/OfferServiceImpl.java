package com.iktpreobuka.dataaccess.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.iktpreobuka.dataaccess.entities.OfferEntity;
import com.iktpreobuka.dataaccess.repositories.OfferRepository;

@Service
public class OfferServiceImpl implements OfferService {
	@PersistenceContext
	private EntityManager em;

	private OfferRepository offerRepository;

	@Override
	public OfferEntity changeAvailableBoughtOfferBuy(Integer id) {

		if (!offerRepository.existsById(id))
			return null;
		OfferEntity offer = offerRepository.findById(id).get();
		offer.setAvailableOffers(offer.getAvailableOffers() - 1);
		offer.setBoughtOffers(offer.getBoughtOffers() + 1);
		offerRepository.save(offer);
		return offer;

	}

	@Override
	public OfferEntity changeAvailableBoughtOfferCanceled(Integer id) {
		if (!offerRepository.existsById(id))
			return null;
		OfferEntity offer = offerRepository.findById(id).get();
		offer.setAvailableOffers(offer.getAvailableOffers() + 1);
		offer.setBoughtOffers(offer.getBoughtOffers() - 1);
		offerRepository.save(offer);
		return offer;
	}

	@Override
	public Boolean checkIfOfferExpires(LocalDate offerExpires, Integer Id) {
		LocalDate present = LocalDate.now();
		OfferEntity offers = offerRepository.findById(Id).get();
			if (offers.getOfferExpires().isAfter(present)) {
				return true;
			} else
				offerRepository.delete(offers);
			return false;

	}

}
