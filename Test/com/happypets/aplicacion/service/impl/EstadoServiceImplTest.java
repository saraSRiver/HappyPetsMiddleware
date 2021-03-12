package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Estado;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.EstadoService;
import com.happypets.aplicacion.serviceImpl.EstadoServiceImpl;

class EstadoServiceImplTest {
	private EstadoService estadoServ;

	private static Logger logger= 
			LogManager.getLogger(EstadoServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		estadoServ= new EstadoServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {

	}

	@Test
	void testEstadoServiceImpl() {

	}
	@Disabled
	@Test
	void testUpdate() throws Exception{
		logger.traceEntry("Testing Estado update...");

		Estado estado = null;
		estadoServ.update('A');
		logger.debug(estado);
		assertNotNull(estado);

		logger.traceExit("Saliendo testUpdate...");
	}
}
