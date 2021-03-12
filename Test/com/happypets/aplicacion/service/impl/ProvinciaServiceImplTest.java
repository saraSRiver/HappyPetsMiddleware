package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Provincia;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ProvinciaService;
import com.happypets.aplicacion.serviceImpl.ProvinciaServiceImpl;

class ProvinciaServiceImplTest {
	private ProvinciaService provinciaServ;

	private static Logger logger= 
			LogManager.getLogger(ProvinciaServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		provinciaServ= new ProvinciaServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testProvinciaServiceImpl() {

	}

	@Test
	void testFindById() throws Exception {
		logger.traceEntry("Testing Provincia findById...");
		Provincia provincia;
		provincia= new Provincia();
		provincia= provinciaServ.findById(1L);
		logger.debug(provincia);
		assertNotNull(provincia);

		logger.traceExit("Saliendo testFindById...");
	}

	@Test
	void testFindAll()  throws Exception{
		logger.traceEntry("Testing Provincia findAll...");

		List<Provincia> provincias;
		provincias= new ArrayList<Provincia>();
		provincias= provinciaServ.findAll();
		logger.debug(provincias);
		assertNotNull(provincias);

		logger.traceExit("Saliendo testFindAll...");
	}

}
