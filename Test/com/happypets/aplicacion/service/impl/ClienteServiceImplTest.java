package com.happypets.aplicacion.service.impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Cliente;
import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.service.ClienteService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.exceptions.IncorrectPasswordException;
import com.happypets.aplicacion.service.exceptions.MailException;
import com.happypets.aplicacion.service.exceptions.UserNotFoundException;
import com.happypets.aplicacion.serviceImpl.ClienteServiceImpl;

class ClienteServiceImplTest {

	private ClienteService clienteServ;

	private static Logger logger= 
			LogManager.getLogger(ClienteServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		clienteServ= new ClienteServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testClienteServiceImpl() {

	}
	@Disabled
	@Test
	void testFindById() throws Exception{

		logger.traceEntry("Testing Cliente findById...");
		Cliente cliente= new Cliente();
		cliente=clienteServ.findById(11L);
		logger.debug(cliente);
		assertNotNull(cliente);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindByEmail() throws Exception{

		logger.traceEntry("Testing Cliente findByEmail...");
		Cliente cliente= new Cliente();
		cliente=clienteServ.findByEmail("ramonrh@gmail.com");
		logger.debug(cliente);
		assertNotNull(cliente);

		logger.traceExit("Saliendo testFindByEmail...");
	}
	@Disabled
	@Test
	void testRegistro() throws Exception {
		logger.traceEntry("Testing Cliente registro...");
		List<Idioma>idiomas;
		Idioma idioma;
		Direccion direccion;
		DireccionDTO direccionDto;
		Cliente cliente= new Cliente();
		idiomas= new ArrayList<Idioma>();
		direccion= new Direccion();
		idioma= new Idioma();
		direccionDto= new DireccionDTO();

		cliente.setNombre("Sara");
		cliente.setApellidos("Sequeiro Río");
		cliente.setEmail("sarasequeironeda93@gmail.com");
		cliente.setTelefono("695509988");
		cliente.setPassword("Promesa93");
		cliente.setEstadoPromocion(false);
		idioma.setIdIdioma("ES");
		cliente.add(idioma);
		direccionDto.setCalle("Lugar de Chisqueira");
		direccionDto.setPortal(15);
		direccionDto.setCp(15510);
		direccionDto.setIdPoblacion(4L);
		direccionDto.setNombrePoblacion("Ferrol");
		direccionDto.setIdProvincia(1L);
		direccionDto.setNombreProvincia("A Coruña");
		direccionDto.setIdpais(1L);
		direccionDto.setNombrePais("España");
		cliente.setDireccion(direccionDto);
		cliente=clienteServ.registro(cliente);
		logger.debug(cliente);
		assertNotNull(cliente);

		logger.traceExit("Saliendo testRegistro...");
	}

	@Test
	void testLogin() throws Exception {
		
		logger.traceEntry("Testing Cliente Login...");
		try {
			Cliente cliente  = new Cliente();
			cliente=clienteServ.login("sarasequeironeda93@gmail.com", "Promesa93");
			logger.debug(cliente);
			assertNotNull(cliente);
			logger.debug("test correcto");
		} catch (UserNotFoundException e) {
			logger.error("test incorrecto");
		} 
		logger.traceExit("Saliendo testLogin...");
	}
	
	
	@Disabled
	@Test
	void testBaja()throws Exception  {
		logger.traceEntry("Testing Cliente Baja...");
		Cliente cliente;
		cliente  = new Cliente();
		logger.debug(cliente);
		assertTrue(clienteServ.baja(1L));

		logger.traceExit("Saliendo testBaja...");
	}
	@Disabled
	@Test
	void testUpdate() throws Exception {
		logger.traceEntry("Testing Cliente Update...");
		Cliente cliente= new Cliente();
		cliente = clienteServ.findById(11L);
		cliente.setApellidos("Sequeiro");
		clienteServ.update(cliente);

		logger.debug(cliente);
		assertNotNull(cliente);

		logger.traceExit("Saliendo testUpdate...");
	}
	@Disabled
	@Test
	void testUptMascotaDelMes() throws Exception {
		logger.traceEntry("Testing Cliente UptMascotaDelMes...");
		Cliente cliente= new Cliente();
		cliente = clienteServ.findById(5L);
		assertTrue(clienteServ.uptPromocion(6, 5L));

		logger.traceExit("Saliendo testUptMascotaDelMes...");
	}
	@Disabled
	@Test
	void testUptEstadoPromocion() throws Exception{
		logger.traceEntry("Testing Cliente UptEstadoPromocion...");
		Cliente cliente= new Cliente();
		cliente = clienteServ.findById(1L);
		clienteServ.uptEstadoPromocion(false, 1L);

		logger.debug(cliente);
		assertNotNull(cliente);

		logger.traceExit("Saliendo testUptEstadoPromocion...");
	}

}
