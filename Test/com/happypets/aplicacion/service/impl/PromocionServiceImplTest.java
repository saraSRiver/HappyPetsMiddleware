package com.happypets.aplicacion.service.impl;



import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Promocion;
import com.happypets.aplicacion.service.PromocionService;
import com.happypets.aplicacion.serviceImpl.PromocionServiceImpl;

class PromocionServiceImplTest {
	
	private PromocionService promocionServ;
	private static Logger logger= 
			LogManager.getLogger(PromocionServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		promocionServ= new PromocionServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPromocionServiceImpl() {

	}
	
	@Disabled
	@Test
	void testFindById() throws Exception{
		logger.traceEntry("Testing Promocion findById...");
		Promocion promocion;
		promocion= new Promocion();
		promocion=promocionServ.findById(4);
		logger.debug(promocion);
		assertNotNull(promocion);
		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindByIdCliente()throws Exception {
		logger.traceEntry("Testing Promocion FindByIdCliente...");
		Promocion promocion;
		promocion= new Promocion();
		promocion=promocionServ.findByIdCliente(4L);
		logger.debug(promocion);
		assertNotNull(promocion);
		logger.traceExit("Saliendo testFindByIdCliente...");
	}
}
