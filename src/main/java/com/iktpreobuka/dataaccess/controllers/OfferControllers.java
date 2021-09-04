package com.iktpreobuka.dataaccess.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.CategoryEntity;
import com.iktpreobuka.dataaccess.entities.EuOfferStatus;
import com.iktpreobuka.dataaccess.entities.OfferEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.entities.UserRoles;
import com.iktpreobuka.dataaccess.repositories.CategoryRepository;
import com.iktpreobuka.dataaccess.repositories.OfferRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;

@RestController
@RequestMapping("/project/offers")
public class OfferControllers {
	@Autowired
	private OfferRepository offerRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;

/*	@RequestMapping(method = RequestMethod.POST)
	public OfferEntity createOffer(@RequestBody OfferEntity newOffer) {
		return offerRepository.save(newOffer);
	}
*/
	@RequestMapping(method = RequestMethod.POST, value = "/{categoryId}/seller/{sellerId}")
	public OfferEntity newOfferWithUserAndCategory(@RequestBody @DateTimeFormat(pattern="dd-MM-yyyy") OfferEntity newOffer,
			@PathVariable Integer categoryId, @PathVariable Integer sellerId) {
		if (categoryRepository.existsById(categoryId)) {
			CategoryEntity cat = categoryRepository.findById(categoryId).get();
			if (userRepository.existsById(sellerId)) {
				UserEntity seller = userRepository.findById(sellerId).get();
				if (seller.getUserRoles().equals(UserRoles.SELLER)) {
					newOffer.setUser(seller);
					newOffer.setCategory(cat);
					LocalDate offerCreated = LocalDate.now();
					LocalDate offerExpires = offerCreated.plusDays(10);
					newOffer.setOfferCreated(offerCreated);
					newOffer.setOfferExpires(offerExpires);
					newOffer.setEuOfferStatus(EuOfferStatus.WAIT_FOR_APPROVING);
					offerRepository.save(newOffer);
				}
			}
		}
		return newOffer;

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{offerId}/category/{categoryId}")
	public OfferEntity changeOffer(@PathVariable Integer offerId, @PathVariable Integer categoryId,
			@RequestBody @DateTimeFormat(iso = ISO.DATE) OfferEntity newOffer) {
		if (offerRepository.existsById(offerId)) {
			OfferEntity offer = offerRepository.findById(offerId).get();
			offer.setCategory(categoryRepository.findById(categoryId).get());
			offer.setActionPrice(newOffer.getActionPrice());
			offer.setBoughtOffers(newOffer.getBoughtOffers());
			offer.setOfferCreated(newOffer.getOfferCreated());
			offer.setOfferExpires(newOffer.getOfferExpires());
			offer.setOfferDescription(newOffer.getOfferDescription());
			offer.setRegularPrice(newOffer.getRegularPrice());
			offerRepository.save(newOffer);

			return offer;
		} else
			return null;
	}
@RequestMapping(method=RequestMethod.DELETE, value="/{offerId}")
	public OfferEntity deleteCat(@PathVariable Integer offerId) {
		OfferEntity delOffer= offerRepository.findById(offerId).get();
		offerRepository.delete(delOffer);
		return delOffer;
	}

}
