package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Servicio;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ServicioService;
import com.happypets.aplicacion.serviceImpl.ServicioServiceImpl;

class ServicioServiceImplTest {
	private ServicioService servicioServ;
	private static Logger logger= 
			LogManager.getLogger(ServicioServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		servicioServ= new ServicioServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testServicioServiceImpl() {

	}
	@Disabled
	@Test
	void testFindById() throws Exception{
		logger.traceEntry("Testing Servicio findById...");
		Servicio servicio;
		servicio=  new Servicio();
		servicio= servicioServ.findById(1L,"ES");
		logger.debug(servicio);
		assertNotNull(servicio);	

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindAll() throws Exception{
		logger.traceEntry("Testing Servicio findAll...");

		List<Servicio> servicios;
		servicios= new ArrayList<Servicio>();
		servicios= servicioServ.findAll("Es");
		logger.debug(servicios);
		assertNotNull(servicios);

		logger.traceExit("Saliendo testFindAll...");
	}
	
	@Test
	void testFindByIdCuidador() throws Exception{
		logger.traceEntry("Testing Servicio testFindByIdCuidador...");
		Servicio servicio = null;
		List<Servicio> servicios;
		servicios= new ArrayList<Servicio>();
		servicios=servicioServ.findByIdCuidador(4L, "Es");
		logger.debug(servicio);
		assertNotNull(servicio);	

		logger.traceExit("Saliendo de testFindByIdCuidador...");
	}

}
