package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Pais;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PaisService;
import com.happypets.aplicacion.serviceImpl.PaisServiceImpl;

class PaisServiceImplTest {
	private PaisService paisService;

	private static Logger logger= 
			LogManager.getLogger(PaisServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		paisService= new PaisServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPaisServiceImpl() {

	}

	@Test
	void testFindById() throws SQLException {
		logger.traceEntry("Testing Pais findById...");
		try {
			Pais pais;
			pais= new Pais();
			pais=paisService.findById(1L);
			logger.debug(pais);
			assertNotNull(pais);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindById...");
	}

}
