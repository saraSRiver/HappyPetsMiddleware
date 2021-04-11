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

import com.happypets.aplicacion.model.ContratoDTO;
import com.happypets.aplicacion.service.ContratoDTOService;
import com.happypets.aplicacion.serviceImpl.ContratoDTOServiceImpl;

class ContratoDTOServiceTest {
	private ContratoDTOService contratoDTOServ;
	private static Logger logger= 
			LogManager.getLogger(ContratoDTOServiceTest.class);
	
	@BeforeEach
	void setUp() throws Exception {
		contratoDTOServ= new ContratoDTOServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	@Disabled
	@Test
	void testFindByIdCliente() throws Exception {
		logger.traceEntry("Testing ContratoDTO findByIdCliente...");
		List<ContratoDTO> contratosDto;
		contratosDto= new ArrayList<ContratoDTO>();
		contratosDto=contratoDTOServ.findByIdCliente(1L);
		logger.debug(contratosDto);
		assertNotNull(contratosDto);

		logger.traceExit("Saliendo de DTO testFindByIdCLiente...");
	}
	
	@Test
	void testFindByIdCuidador() throws Exception {
		logger.traceEntry("Testing ContratoDTO findByIdCuidador...");
		List<ContratoDTO> contratosDto;
		contratosDto= new ArrayList<ContratoDTO>();
		contratosDto=contratoDTOServ.findByIdCuidador(1L);
		logger.debug(contratosDto);
		assertNotNull(contratosDto);

		logger.traceExit("Saliendo de DTO testFindByIdCuidador...");
	}
	
}
