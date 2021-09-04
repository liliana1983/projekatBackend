package com.iktpreobuka.dataaccess.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.dataaccess.entities.BillEntity;
import com.iktpreobuka.dataaccess.entities.UserEntity;

public interface BillRepository extends CrudRepository<BillEntity,Integer> {
	public List<BillEntity> findByBillCreatedBetween(LocalDate startDate, LocalDate endDate);

	public List<BillEntity> findByUserId(Integer id);

	//public List<BillEntity> findByCategory(Integer categoryId);

//	public List<BillEntity> findByOfferAndCategory(Integer offerId,Integer categoryId);

	//public List<BillEntity> findByOfferCategory(Integer categoryId);

}
