package com.happypets.aplicacion.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.model.TipoEspecie;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.TipoEspecieService;
import com.happypets.aplicacion.serviceImpl.TipoEspecieServiceImpl;

class TipoEspecieServiceImplTest {
	private static Logger logger= 
			LogManager.getLogger(TipoEspecieServiceImplTest.class);
	private TipoEspecieService tipEspService;

	@BeforeEach
	void setUp() throws Exception {
		tipEspService = new TipoEspecieServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTipoEspecieServiceImpl() {

	}

	@Test
	void testFindById() throws Exception{
		logger.traceEntry("Testing tipoEspecie findById...");

		TipoEspecie tipoEspecie;
		Idioma idioma;
		idioma= new Idioma();
		tipoEspecie = new TipoEspecie();
		tipoEspecie = tipEspService.findById(1L, "Es");
		logger.debug(tipoEspecie);
		//assertNotNull para objetos, assertTrue si es boolean
		assertNotNull(tipoEspecie);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindAll()throws Exception {
		logger.traceEntry("Testing tipoEspecie findAll...");

		List<TipoEspecie> especies;
		especies = new ArrayList<TipoEspecie>();
		especies = tipEspService.findAll("Es");
		logger.debug(especies);

		logger.traceExit("Saliendo testFindAll...");
	}
	@Disabled
	@Test
	void testFindByIdCuidador()throws Exception {
		logger.traceEntry("Testing tipoEspecie findByIdCuidador...");

		List<TipoEspecie> especies;
		especies = new ArrayList<TipoEspecie>();
		especies = tipEspService.findByIdCuidador(1L, "Es");
		logger.debug(especies);
		//assertNotNull para objetos, assertTrue si es boolean
		assertNotNull(especies);

		logger.traceExit("Saliendo testFindByIdCuidador...");
	}
	@Disabled
	@Test
	void testDeleteByIdCuidador()throws Exception {
		logger.traceEntry("Testing tipoEspecie deleteByCuidador...");

		List<TipoEspecie> especies;
		especies = new ArrayList<TipoEspecie>();
		tipEspService.deleteByIdCuidador(1L);
		logger.debug(especies);
		//assertNotNull para objetos, assertTrue si es boolean
		assertTrue(true);

		logger.traceExit("Saliendo de deleteByCuidador...");
	}

}
