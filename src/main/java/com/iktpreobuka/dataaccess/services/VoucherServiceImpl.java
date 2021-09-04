package com.iktpreobuka.dataaccess.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

@Service
public class VoucherServiceImpl implements VoucherService{
	@PersistenceContext
	private EntityManager em;
}
