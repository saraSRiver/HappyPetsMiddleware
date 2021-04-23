package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DireccionDTOService;
import com.happypets.aplicacion.serviceImpl.DireccionDTOServiceImpl;

class DireccionDTOServiceImplTest {

	private DireccionDTOService dirDtoServ;

	private static Logger logger= 
			LogManager.getLogger(DireccionDTOServiceImplTest.class);
	@BeforeEach
	void setUp() throws Exception {
		dirDtoServ= new DireccionDTOServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDireccionDTOServiceImpl() {

	}

	@Test
	void testFindByIdCliente()throws Exception {
		logger.traceEntry("Testing DireccionDTO findByIdCliente...");

		DireccionDTO dirDto;
		dirDto= new DireccionDTO();
		dirDto=dirDtoServ.findByIdCliente(10L);
		logger.debug(dirDto);
		assertNotNull(dirDto);

		logger.traceExit("Saliendo testFindByIdCliente...");
	}
	@Disabled
	@Test
	void testFindByIdCuidador() throws Exception{
		logger.traceEntry("Testing DireccionDTO findByIdCuidador...");

		DireccionDTO dirDto;
		dirDto= new DireccionDTO();
		dirDto=dirDtoServ.findByIdCuidador(2L);
		logger.debug(dirDto);
		assertNotNull(dirDto);

		logger.traceExit("Saliendo testFindByIdCuidador...");
	}

}
