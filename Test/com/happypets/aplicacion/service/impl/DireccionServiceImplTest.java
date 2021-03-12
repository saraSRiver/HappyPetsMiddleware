package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.DireccionDTOService;
import com.happypets.aplicacion.service.DireccionService;
import com.happypets.aplicacion.serviceImpl.DireccionDTOServiceImpl;
import com.happypets.aplicacion.serviceImpl.DireccionServiceImpl;

class DireccionServiceImplTest {
	private DireccionService dirServ;
	private DireccionDTOService dirDtoServ;

	private static Logger logger= 
			LogManager.getLogger(DireccionServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		dirServ= new DireccionServiceImpl();
		dirDtoServ= new DireccionDTOServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDireccionServiceImpl() {

	}
	@Disabled
	@Test
	void testFindById()throws Exception {
		logger.traceEntry("Testing Direccion findById...");

		Direccion direccion;
		direccion= new Direccion();
		direccion=dirServ.findById(1L);
		logger.debug(direccion);
		assertNotNull(direccion);

		logger.traceExit("Saliendo testFindById...");
	}

	//@Test
	//	void testCreateDirCuidador() {
	//	logger.traceEntry("Testing Direccion CreateDirCuidador...");
	//	try {
	//	direccion=dirServ.createDirCuidador(dirDto);
	//logger.debug(direccion);
	//assertNotNull(direccion);
	//	} catch (DataException e) {
	//	logger.error(e);
	//}
	//logger.traceExit("Saliendo testCreateDirCuidador...");
	//}

	//@Test
	//void testCreateDirCliente() {
	//logger.traceEntry("Testing Direccion CreateDirCliente...");
	//try {
	//direccion=dirServ.createDirCuidador(dirDto);
	//logger.debug(direccion);
	//assertNotNull(direccion);
	//	} catch (DataException e) {
	//	logger.error(e);
	//	}
	//logger.traceExit("Saliendo CreateDirCliente...");
	//}
	@Disabled
	@Test
	void testUpdate() throws Exception{
		logger.traceEntry("Testing Direccion update...");

		DireccionDTO direccionDto;
		direccionDto= new DireccionDTO();
		direccionDto=dirDtoServ.findByIdCliente(4L);
		direccionDto.setPiso(4);
		dirServ.update(direccionDto);
		assertNotNull(direccionDto);

		logger.traceExit("Saliendo testUpdate...");
	}
	@Disabled
	@Test
	void testDeleteByCliente() throws Exception{
		logger.traceEntry("Testing Direccion deleteDirCliente...");

		Direccion direccion;
		direccion= new Direccion();

		dirServ.deleteByCliente(6L);
		logger.debug(direccion);
		assertNotNull(direccion);

		logger.traceExit("Saliendo testDeleteDirCliente...");
	}

	@Test
	void testDeleteByCuidador() throws Exception{
		logger.traceEntry("Testing Direccion deleteDirCuidador...");

		Direccion direccion;
		direccion=new Direccion();
		dirServ.deleteByCuidador(7L);
		logger.debug(direccion);
		assertNotNull(direccion);

		logger.traceExit("Saliendo testDeleteDirCuidador...");
	}

}
