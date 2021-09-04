package com.iktpreobuka.dataaccess.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.VoucherEntity;

public interface VoucherRepository extends CrudRepository<VoucherEntity,Integer> {
	public List<VoucherEntity> findByExpirationDateBefore (LocalDate date);
	//public List<VoucherEntity> findByBuyer(Integer id);
	public List<VoucherEntity> findByOffer(Integer offerId);
	public List<VoucherEntity> findByExpirationDateAfter(LocalDate present);
}
