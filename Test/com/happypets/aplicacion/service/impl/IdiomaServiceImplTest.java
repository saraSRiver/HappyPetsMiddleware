package com.happypets.aplicacion.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.IdiomaService;
import com.happypets.aplicacion.serviceImpl.IdiomaServiceImpl;

class IdiomaServiceImplTest {

	private IdiomaService idiomaServ;

	private static Logger logger= 
			LogManager.getLogger(IdiomaServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		idiomaServ= new IdiomaServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testIdiomaServiceImpl() {

	}
	@Disabled
	@Test
	void testFindByid() throws Exception{
		logger.traceEntry("Testing Idioma findById...");
		try {
			Idioma idioma;
			idioma= new Idioma();
			idioma= idiomaServ.findByid("ES");
			logger.debug(idioma);
			assertNotNull(idioma);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindById...");
	}
	
	@Test
	void testFindByIdiomasCuidador() throws Exception{
		logger.traceEntry("Testing Idioma FindByIdiomasCuidador...");
		try {
			List<Idioma>idiomas;
			idiomas= new ArrayList<Idioma>();
			idiomas= idiomaServ.findByIdiomasCuidador(1L);
			logger.debug(idiomas);
			assertNotNull(idiomas);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindByIdiomasCuidador...");
	}
	@Disabled
	@Test
	void testFindByIdiomasCliente()throws Exception {
		logger.traceEntry("Testing Idioma FindByIdiomasCliente...");
		try {
			List<Idioma>idiomas;
			idiomas= new ArrayList<Idioma>();
			idiomas= idiomaServ.findByIdiomasCliente(2L);
			logger.debug(idiomas);
			assertNotNull(idiomas);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo FindByIdiomasCliente...");
	}
	@Disabled
	@Test
	void testFindAll() throws Exception{
		logger.traceEntry("Testing Idioma findAll...");
		try {
			List<Idioma>idiomas;
			idiomas= new ArrayList<Idioma>();
			idiomas= idiomaServ.findAll();
			logger.debug(idiomas);
			assertNotNull(idiomas);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindAll...");
	}
	@Disabled
	@Test
	void testDeleteByCliente()throws Exception {
		logger.traceEntry("Testing Idioma FindByIdiomasCuidador...");
		try {
			List<Idioma>idiomas;
			idiomas= new ArrayList<Idioma>();
			idiomaServ.deleteByCliente(4L);
			logger.debug(idiomas);
			assertTrue(true);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindByIdiomasCuidador...");
	}
	@Disabled
	@Test
	void testDeleteByCuidador() throws Exception{
		logger.traceEntry("Testing Idioma FindByIdiomasCliente...");
		try {
			List<Idioma>idiomas;
			idiomas= new ArrayList<Idioma>();
			idiomaServ.deleteByCuidador(5L);
			logger.debug(idiomas);
			assertTrue(true);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo FindByIdiomasCliente...");
	}

}
