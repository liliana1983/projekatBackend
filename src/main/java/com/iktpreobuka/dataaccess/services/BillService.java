package com.iktpreobuka.dataaccess.services;


public interface BillService {
	// ako su oba false onda nije prosao racun i izbrisi taj racun
	
	Boolean activeBills(Boolean paymentMade, Boolean paymentCanceled);
}
