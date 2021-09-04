package com.iktpreobuka.dataaccess.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.iktpreobuka.dataaccess.entities.BillEntity;
import com.iktpreobuka.dataaccess.entities.OfferEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.iktpreobuka.dataaccess.helpClass.Validation;
import com.iktpreobuka.dataaccess.repositories.BillRepository;
import com.iktpreobuka.dataaccess.repositories.CategoryRepository;
import com.iktpreobuka.dataaccess.repositories.OfferRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;
import com.iktpreobuka.dataaccess.services.OfferService;

@RestController
@RequestMapping(path = "/project/bills")
public class BillControllers {
	@Autowired
	BillRepository billRepository;
	@Autowired
	OfferRepository offerRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	private OfferService offerServices;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public List<BillEntity> getAll() {
		return (List<BillEntity>) billRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{offerId}/buyer/{buyerId}")
	public BillEntity createBill(@RequestParam Boolean paymentMade, @RequestParam Boolean paymentCanceled,
			@DateTimeFormat(pattern="dd-MM-yyyy")@RequestParam Date billCreated, @PathVariable Integer offerId, @PathVariable Integer buyerId) {
		if (offerRepository.existsById(offerId)) {
			if (userRepository.existsById(buyerId)) {
				BillEntity bill = new BillEntity();
				OfferEntity offer = offerRepository.findById(offerId).get();
				UserEntity user = userRepository.findById(buyerId).get();
				bill.setPaymentMade(paymentMade);
				bill.setPaymentCanceled(paymentCanceled);
				bill.setBillCreated(billCreated);
				bill.setOffer(offer);
				bill.setUser(user);
				bill.setCategory(offer.getCategory());
				offer.setAvailableOffers(offer.getAvailableOffers() - 1);
				offer.setBoughtOffers(offer.getBoughtOffers() + 1);
				offerRepository.save(offer);
				if (bill.getPaymentCanceled() == true) {
					bill.setPaymentCanceled(true);
					offerServices.changeAvailableBoughtOfferCanceled(bill.getOffer().getId());			
				} else
					bill.setPaymentCanceled(false);
				return billRepository.save(bill);
			} // od ukupnog broja ponuda oduzmi napravljene ponude u racunu
		}//("content=Type,"application/json; charset=utf8")
		return null;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public BillEntity changeBill(@PathVariable Integer id,
			@DateTimeFormat(pattern="dd-MM-yyyy") @RequestBody BillEntity changeBill) {
		if (billRepository.existsById(id)) {
			BillEntity bill = billRepository.findById(id).get();
			Date billCreated = new Date();
			bill.setBillCreated(billCreated);
			bill.setCategory(Validation.setIfNotNull(bill.getCategory(), changeBill.getCategory()));
			bill.setOffer(Validation.setIfNotNull(bill.getOffer(), changeBill.getOffer()));
			bill.setPaymentCanceled(bill.getPaymentCanceled());
			bill.setPaymentMade(bill.getPaymentMade());
			bill.setUser(Validation.setIfNotNull(bill.getUser(), changeBill.getUser()));
			billRepository.save(bill);
			if (bill.getPaymentCanceled()) {
				bill.getOffer().setAvailableOffers(bill.getOffer().getAvailableOffers() + 1);
				bill.getOffer().setBoughtOffers(bill.getOffer().getBoughtOffers() - 1);
				offerRepository.save(bill.getOffer());
			}

			return bill;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public BillEntity removeBill(@PathVariable Integer id) {
		if (billRepository.existsById(id)) {
			BillEntity bill = billRepository.findById(id).get();
			billRepository.delete(bill);
			return bill;
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findByBuyer/{buyerId}")
	public List<BillEntity> allBillsOneUser(@PathVariable Integer id) {
		return billRepository.findByUserId(id);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/findByDate/{startDate}/and/{endDate}")
	public List<BillEntity> billsByDate(@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate startDate,
			@DateTimeFormat(iso = ISO.DATE) @PathVariable LocalDate endDate) {
		return billRepository.findByBillCreatedBetween(startDate, endDate);
	}

	/*@RequestMapping(method = RequestMethod.GET, value = "/findByCategory/{categoryId}")
	public List<BillEntity> findByCat(@PathVariable Integer categoryId) {
		return billRepository.findByOfferCategory(categoryId);
	}*/
}
