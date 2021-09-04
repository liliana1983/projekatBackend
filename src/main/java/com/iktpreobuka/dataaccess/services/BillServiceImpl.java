package com.iktpreobuka.dataaccess.services;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iktpreobuka.dataaccess.entities.BillEntity;

@Service
public class BillServiceImpl implements BillService{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Boolean activeBills(Boolean paymentMade, Boolean paymentCanceled) {
		// TODO Auto-generated method stub
		return null;
	}
		
		
	

	
	}

