package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Poblacion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PoblacionService;
import com.happypets.aplicacion.serviceImpl.PoblacionServiceImpl;

class PoblacionServiceImplTest {

	private PoblacionService poblacionServ;

	private static Logger logger= 
			LogManager.getLogger(PoblacionServiceImplTest.class);
	@BeforeEach
	void setUp() throws Exception {
		poblacionServ= new PoblacionServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPoblacionServiceImpl() {

	}

	@Test
	void testFindById()  throws Exception{
		logger.traceEntry("Testing Poblacion findById...");
		Poblacion poblacion;
		poblacion=new Poblacion();
		poblacion= poblacionServ.findById(1L);
		logger.debug(poblacion);
		assertNotNull(poblacion);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindAll() throws Exception {
		logger.traceEntry("Testing Poblacion findAll...");

		List<Poblacion> poblaciones;
		poblaciones= new ArrayList<Poblacion>();
		poblaciones= poblacionServ.findAll();
		logger.debug(poblaciones);
		assertNotNull(poblaciones);

		logger.traceExit("Saliendo testFindAll...");
	}
	@Disabled
	@Test
	void testFindByProvincia() throws Exception {
		logger.traceEntry("Testing Poblacion findByProvincia...");

		List<Poblacion> poblaciones;
		poblaciones= new ArrayList<Poblacion>();
		poblaciones= poblacionServ.findByProvincia(1L);
		logger.debug(poblaciones);
		assertNotNull(poblaciones);

		logger.traceExit("Saliendo testFindByProvincia...");
	}
}
