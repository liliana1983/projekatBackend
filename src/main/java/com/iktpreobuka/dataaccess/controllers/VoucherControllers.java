package com.iktpreobuka.dataaccess.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.dataaccess.entities.VoucherEntity;
import com.iktpreobuka.dataaccess.repositories.OfferRepository;
import com.iktpreobuka.dataaccess.repositories.UserRepository;
import com.iktpreobuka.dataaccess.repositories.VoucherRepository;
@RestController
@RequestMapping(value="project/vouchers")
public class VoucherControllers {
	@Autowired
	public VoucherRepository voucherRepository;
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public OfferRepository offerRepository;
	@RequestMapping(method=RequestMethod.GET, value="/")
	public List<VoucherEntity> getAllVouchers(){
		return (List<VoucherEntity>) voucherRepository.findAll();
	}
	@RequestMapping(method=RequestMethod.POST,value="")
	public VoucherEntity createVoucher(@RequestBody VoucherEntity newVoucher /* integer iduserEntity, integer id Path OfferEntity*/) {
		VoucherEntity Voucher =new VoucherEntity();
		//if(RoleCustomer) ako postoji user sa tim id, nadji ga, i proveri da li je on customer
			//userEntity buyer
		//Offer da li postoji ponuda nadji je setuj je
			//setovanje....
		return voucherRepository.save(Voucher);
	}
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public VoucherEntity changeVoucher (@PathVariable Integer id, @RequestBody@DateTimeFormat (iso=ISO.DATE) VoucherEntity changedVoucher) {
		if(voucherRepository.existsById(id)) {//dodam ifove
			VoucherEntity voucher= voucherRepository.findById(id).get();
			voucher.setExpirationDate(changedVoucher.getExpirationDate());
			voucher.setIsUsed(changedVoucher.getIsUsed());
			voucher.setOffer(changedVoucher.getOffer());
			voucher.setUser(changedVoucher.getUser());
			voucherRepository.save(voucher);
			return voucher;
		}
		return null;
	}
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public VoucherEntity deleteVoucher(@PathVariable Integer id) {
		VoucherEntity delVoucher=voucherRepository.findById(id).get();
		voucherRepository.delete(delVoucher);
		return delVoucher;
	}
	/*@RequestMapping(method=RequestMethod.GET,value="/findByBuyer/{buyerId}")
	public List<VoucherEntity> findByUser(@PathVariable Integer buyerId){
		return voucherRepository.findByBuyer(buyerId);
	} 
*/
	@RequestMapping(method=RequestMethod.GET,value="findByOffer/{offerId}")
	public List<VoucherEntity> findByOffer(@PathVariable Integer offerId){
		return voucherRepository.findByOffer(offerId);
	}
	@RequestMapping(method=RequestMethod.GET,value="/findNonExpiredVoucher")
	public List<VoucherEntity> nonExpired(){
		//LocalDate present =LocalDate.now();
	return	voucherRepository.findByExpirationDateAfter(LocalDate.now());
	}
}
