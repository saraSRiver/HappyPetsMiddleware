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

import com.happypets.aplicacion.model.Puntuacion;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.PuntuacionService;
import com.happypets.aplicacion.serviceImpl.PuntuacionServiceImpl;

class PuntuacionServiceImplTest {

	private PuntuacionService puntService;
	
	private static Logger logger= 
			LogManager.getLogger(PuntuacionServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		puntService= new PuntuacionServiceImpl();
	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testPuntuacionServiceImpl() {

	}
	@Disabled
	@Test
	void testFindByidCliente()throws Exception {
		logger.traceEntry("Testing Puntuacion findByIdCliente...");
		
			List<Puntuacion> puntuaciones;
			puntuaciones= new ArrayList<Puntuacion>();
			puntuaciones= puntService.findByidCliente(4L);
			logger.debug(puntuaciones);
			//assertNotNull para objetos, assertTrue si es boolean
			assertNotNull(puntuaciones);
		
		logger.traceExit("Saliendo testFindByIdCliente...");
	}
	@Disabled
	@Test
	void testFindByMedia()throws Exception {
		logger.traceEntry("Testing Puntuacion findMedia...");
		try {
			Double puntuacion;
			puntuacion= puntService.findByMedia(4L);
			logger.debug(puntuacion);
			//assertNotNull para objetos, assertTrue si es boolean
			assertNotNull(puntuacion);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindByMedia...");
	}
	@Disabled
	@Test
	void testCreate()throws Exception {
		logger.traceEntry("Testing Puntuacion Create...");
		
			Puntuacion puntuacion;
			puntuacion= new Puntuacion();
			puntuacion.setPuntuacion(4);
			puntuacion.setComentario("Muy buen servicio. Semi quedó muy contento");
			puntuacion.setIdCliente(11L);
			puntuacion.setIdCuidador(4L);
			 puntService.create(puntuacion);
			logger.debug(puntuacion);
			//assertNotNull para objetos, assertTrue si es boolean
			assertNotNull(puntuacion);
		
		logger.traceExit("Saliendo testCreate...");
	}
	@Disabled
	@Test
	void testUpdate()throws Exception {
		logger.traceEntry("Testing Puntuacion Update...");
	
			List<Puntuacion> puntuaciones;
			puntuaciones= new ArrayList<Puntuacion>();
			puntuaciones=puntService.findByidCliente(11L);
			puntuaciones.get(0).setPuntuacion(5);
			puntService.update(puntuaciones.get(0));
			logger.debug(puntuaciones);
			//assertNotNull para objetos, assertTrue si es boolean
			assertTrue(true);
		
		logger.traceExit("Saliendo testUpdate...");
	}

}
