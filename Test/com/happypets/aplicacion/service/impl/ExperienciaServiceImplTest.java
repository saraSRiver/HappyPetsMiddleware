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

import com.happypets.aplicacion.model.Experiencia;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ExperienciaService;
import com.happypets.aplicacion.serviceImpl.ExperienciaServiceImpl;

class ExperienciaServiceImplTest {
	private ExperienciaService experienciaServ;

	private Experiencia experiencia;
	private static Logger logger= 
			LogManager.getLogger(ExperienciaServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		experienciaServ= new ExperienciaServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testExperienciaServiceImpl() {

	}
	@Disabled
	@Test
	void testFindById() throws Exception{
		logger.traceEntry("Testing Experiencia findById...");

		experiencia= new Experiencia();
		experiencia=experienciaServ.findById(2, "Es");
		logger.debug(experiencia);
		assertNotNull(experiencia);

		logger.traceExit("Saliendo testFindById...");
	}

	@Test
	void testFindAll() throws Exception{
		logger.traceEntry("Testing Experiencia findAll...");

		List<Experiencia> experiencias;
		experiencias= new ArrayList<Experiencia>();	
		experiencias= experienciaServ.findAll("En");

		logger.debug(experiencias);
		assertNotNull(experiencias);

		logger.traceExit("Saliendo testFindAll...");

	}

}
