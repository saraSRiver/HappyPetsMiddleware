package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Mascota;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.MascotaService;
import com.happypets.aplicacion.serviceImpl.MascotaServiceImpl;
import com.happypets.aplicacion.util.DBDataUtils;

class MascotaServiceImplTest {
	private MascotaService mascotaServ;

	private static Logger logger= 
			LogManager.getLogger(MascotaServiceImplTest.class);	

	@BeforeEach
	void setUp() throws Exception {
		mascotaServ= new MascotaServiceImpl();

	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testMascotaServiceImpl() {

	}
	@Disabled
	@Test
	void testFindById()throws Exception {

		logger.traceEntry("Testing Mascota findById...");
		try {
			Mascota mascota;
			mascota= new Mascota();
			mascota=mascotaServ.findById(1L);
			logger.debug(mascota);
			assertNotNull(mascota);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindById...");
	}

	@Test
	void testFindByIdCliente() throws Exception{

		logger.traceEntry("Testing Mascota FindByIdCliente...");

		List<Mascota> mascotas;
		mascotas= new ArrayList<Mascota>();
		mascotas=mascotaServ.findByIdCliente(4L);
		logger.debug(mascotas);
		assertNotNull(mascotas);

		logger.traceExit("Saliendo testFindByIdCliente...");
	}
	@Disabled
	@Test
	void testCreate() throws Exception {
		logger.traceEntry("Testing Mascota Create...");

		Mascota mascota;
		mascota= new Mascota();
		mascota.setNombre("Semi");
		mascota.setDescripcion("Es un perro muy dócil y listo. Le encanta jugar, "
				+ " pero también es muy enérgico. Necesita al menos 20 min de ejercicio "
				+ "para quemar energías. ");
		mascota.setIdTipo(1L);
		mascota.setFechaNacimiento(DBDataUtils.formatDate("05-10-2014"));
		mascota.setVacunado(true);
		mascota.setBuenoConAnimales(false);
		mascota.setBuenoConNinos(true);
		mascota.setAlergia(false);
		mascota.setTratamiento(false);
		mascota.setDesparasitado(true);
		mascota.setMicrochip(true);
		mascota.setIdCliente(11L);

		mascotaServ.create(mascota);
		logger.debug(mascota);
		assertNotNull(mascota);

		logger.traceExit("Saliendo testCreate...");
	}
	@Disabled
	@Test
	void testUpdate()throws Exception {
		logger.traceEntry("Testing Mascota Update...");

		Mascota mascota;
		mascota= new Mascota();
		mascota=mascotaServ.findById(1L);
		mascota.setAlergia(true);
		mascotaServ.update(mascota);
		logger.debug(mascota);
		assertNotNull(mascota);

		logger.traceExit("Saliendo testUpdate...");
	}
	@Disabled
	@Test
	void testDeleteMascota()throws Exception {
		logger.traceEntry("Testing Mascota DeleteMascota...");

		Mascota mascota;
		mascota= new Mascota();
		mascotaServ.deleteMascota(1L);
		logger.debug(mascota);
		assertNotNull(mascota);

		logger.traceExit("Saliendo testDeleteMascota...");
	}
	@Disabled
	@Test
	void testDeleteMascotaByCliente() throws Exception{
		logger.traceEntry("Testing Mascota DeleteMascotaByCliente...");

		Mascota mascota;
		mascota= new Mascota();
		mascotaServ.deleteMascotaByCliente(4L);
		logger.debug(mascota);
		assertNotNull(mascota);

		logger.traceExit("Saliendo testDeleteMascotaByCliente...");
	}
}
