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

import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.model.Estado;
import com.happypets.aplicacion.service.ContratoService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.serviceImpl.ContratoServiceImpl;
import com.happypets.aplicacion.util.DBDataUtils;


class ContratoServiceImplTest {
	private ContratoService contratoServ;

	private static Logger logger= 
			LogManager.getLogger(ContratoServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		contratoServ= new ContratoServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testContratoServiceImpl() {

	}
	@Disabled
	@Test
	void testFindByid()  throws Exception{
		logger.traceEntry("Testing Contrato findById...");
		Contrato contrato;
		contrato= new Contrato();
		contrato=contratoServ.findByid(1L);
		logger.debug(contrato);
		assertNotNull(contrato);

		logger.traceExit("Saliendo testFindById...");
	}
	
	@Test
	void testFindByHistorialCuidador() throws Exception {
		logger.traceEntry("Testing Contrato findById...");
		List<Contrato> contratos;
		contratos= new ArrayList<Contrato>();
		contratos=contratoServ.findByHistorialCuidador(1L);
		logger.debug(contratos);
		assertNotNull(contratos);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindByHistorialCliente()  throws Exception{
		logger.traceEntry("Testing Contrato findById...");
		List<Contrato> contratos;
		contratos= new ArrayList<Contrato>();
		contratos=contratoServ.findByHistorialCliente(1L);
		logger.debug(contratos);
		assertNotNull(contratos);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testCreate() throws Exception {
		logger.traceEntry("Testing Contrato findById...");
		Contrato contrato;
		contrato= new Contrato();
		contrato.setFechaContrato(DBDataUtils.formatDate("10-02-2021"));
		contrato.setFechaInicio(DBDataUtils.formatDate("13-02-2021"));
		contrato.setFechaFinal(DBDataUtils.formatDate("15-02-2021"));
		contrato.setIdCliente(10L);
		contrato.setIdCuidador(2L);
		contrato.setIdMascota(11L);
		contrato.setIdEstado('P');
		contrato.setIdServicio(3L);
	
		
		contratoServ.create(contrato);
		logger.debug(contrato);
		assertNotNull(contrato);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testUpdateEstado() throws Exception {
		logger.traceEntry("Testing Contrato findById...");

		assertTrue(contratoServ.updateEstado(9L, 'R'));

		logger.traceExit("Saliendo testFindById...");
	}

}
