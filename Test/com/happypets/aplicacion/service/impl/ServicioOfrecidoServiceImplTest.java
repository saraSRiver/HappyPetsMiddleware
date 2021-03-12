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

import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ServicioOfrecidoService;
import com.happypets.aplicacion.serviceImpl.ServicioOfrecidoServiceImpl;

class ServicioOfrecidoServiceImplTest {
	private ServicioOfrecidoService servOfreServ;

	private static Logger logger= 
			LogManager.getLogger(ServicioOfrecidoServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		servOfreServ= new ServicioOfrecidoServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testServicioOfrecidoServiceImpl() {

	}
	@Disabled
	@Test
	void testFindByCuidador()throws Exception {
		List<ServicioOfrecido> serviciosOfrecidos;
		logger.traceEntry("Testing ServicioOfrecido FindByCuidador...");

		serviciosOfrecidos= new ArrayList<ServicioOfrecido>();
		serviciosOfrecidos=servOfreServ.findByCuidador(1L);
		logger.debug(serviciosOfrecidos);
		assertNotNull(serviciosOfrecidos);

		logger.traceExit("Saliendo testFindByCuidador...");
	}
	@Disabled
	@Test
	void testCreate()throws Exception {
		logger.traceEntry("Testing ServicioOfrecido Create...");
		ServicioOfrecido servicio1;

		servicio1= new ServicioOfrecido();
		servicio1.setIdCuidador(10L);
		servicio1.setIdServicio(3L);
		servicio1.setNombreServicio("Visita a domicilio");
		servicio1.setPrecio(12.50);

		servOfreServ.create(servicio1);
		logger.debug(servicio1);

		logger.traceExit("Saliendo testCreate...");
	}
	@Disabled
	@Test
	void testUpdate()throws Exception {
		List<ServicioOfrecido> serviciosOfrecidos;
		logger.traceEntry("Testing ServicioOfrecido Update...");

		serviciosOfrecidos= servOfreServ.findByCuidador(2L);
		serviciosOfrecidos.get(0).setPrecio(12d);
		assertTrue(servOfreServ.update(serviciosOfrecidos.get(0)));

		logger.traceExit("Saliendo testUpdate...");
	}
	@Disabled
	@Test
	void testDeleteByIdCuidador() throws Exception{
		logger.traceEntry("Testing ServicioOfrecido DeleteByIdCuidador...");

		ServicioOfrecido servOfrecido;
		servOfrecido= new ServicioOfrecido();
		servOfreServ.deleteByIdCuidador(9L);
		logger.debug(servOfrecido);
		assertNotNull(servOfrecido);

		logger.traceExit("Saliendo testDeleteByIdCuidador...");
	}

}
