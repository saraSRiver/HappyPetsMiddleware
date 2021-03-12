package com.happypets.aplicacion.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.happypets.aplicacion.model.Cuidador;
import com.happypets.aplicacion.model.Direccion;
import com.happypets.aplicacion.model.DireccionDTO;
import com.happypets.aplicacion.model.Experiencia;
import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.model.TipoEspecie;
import com.happypets.aplicacion.service.CuidadorCriteria;
import com.happypets.aplicacion.service.CuidadorService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.service.ExperienciaService;
import com.happypets.aplicacion.service.exceptions.MailException;
import com.happypets.aplicacion.service.exceptions.UserNotFoundException;
import com.happypets.aplicacion.serviceImpl.CuidadorServiceImpl;
import com.happypets.aplicacion.serviceImpl.ExperienciaServiceImpl;

class CuidadorServiceImplTest {

	private CuidadorService cuidadorServ;
	private ExperienciaService experienciaServ;
	private static Logger logger= 
			LogManager.getLogger(CuidadorServiceImplTest.class);

	@BeforeEach
	void setUp() throws Exception {
		cuidadorServ= new CuidadorServiceImpl();
		experienciaServ= new ExperienciaServiceImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCuidadorServiceImpl() {

	}
	@Disabled
	@Test
	void testFindById() throws Exception{
		logger.traceEntry("Testing Cuidador findById...");
		Cuidador cuidador;
		cuidador= new Cuidador();
		cuidador=cuidadorServ.findById(1L);
		logger.debug(cuidador);
		assertNotNull(cuidador);

		logger.traceExit("Saliendo testFindById...");
	}
	@Disabled
	@Test
	void testFindByEmail() throws Exception{
		logger.traceEntry("Testing Cuidador findByEmail...");
		Cuidador cuidador;
		cuidador= new Cuidador();
		cuidador=cuidadorServ.findByEmail("sandravelgondar@gmail.com");
		logger.debug(cuidador);
		assertNotNull(cuidador);

		logger.traceExit("Saliendo testFindByEmail...");
	}

	@Test
	void testFindByCriteria() {
		logger.traceEntry("Testing Cuidador findByCriteria...");
		try {
			List<Cuidador>cuidadores;
			CuidadorCriteria cuidadorCriteria;
			cuidadores= new ArrayList<Cuidador>();
			cuidadorCriteria= new CuidadorCriteria();
			cuidadorCriteria.setIdExperiencia(4);
			cuidadorCriteria.setIdServicio(1L);
			cuidadorCriteria.setPrecioDesde(8d);
			cuidadorCriteria.setPrecioHasta(15d);
			cuidadorCriteria.setIdTipoEspecie(1L);
			cuidadorCriteria.setIdIdioma("es");
			cuidadorCriteria.setIdPoblacion(4L);
			
			cuidadores=cuidadorServ.findByCriteria(cuidadorCriteria);
			logger.debug(cuidadores);
			assertNotNull(cuidadores);
		} catch (DataException e) {
			logger.error(e);
		}
		logger.traceExit("Saliendo testFindByfindByCriteria...");
	}
	@Disabled
	@Test
	void testRegistro() throws MailException, DataException {
		logger.traceEntry("Testing Cuidador registro...");
		Cuidador cuidador;
		cuidador= new Cuidador();
		List<Idioma>idiomas;
		Idioma idioma;
		Direccion direccion;
		DireccionDTO direccionDto;
		Experiencia experiencia;
		ServicioOfrecido servicio1;
		ServicioOfrecido servicio2;
		List<ServicioOfrecido> servicios;
		TipoEspecie especie1;
		TipoEspecie especie2;
		List<TipoEspecie> especies;
		idiomas= new ArrayList<Idioma>();
		direccion= new Direccion();
		idioma= new Idioma();
		direccionDto= new DireccionDTO();
		experiencia= new Experiencia();
		servicios= new ArrayList<ServicioOfrecido>();
		servicio1= new ServicioOfrecido();
		servicio2= new ServicioOfrecido();
		especie1= new TipoEspecie();
		especie2= new TipoEspecie();
		especies= new ArrayList<TipoEspecie>();

		cuidador.setNombre("Lucas");
		cuidador.setApellidos("García");
		cuidador.setEmail("lugarcia132@gmail.com");
		cuidador.setTelefono("699204216");
		cuidador.setPassword("4dic1966");
		Experiencia exp = experienciaServ.findById(2, "Es");
		cuidador.setExperiencia(exp);
		especie1.setIdTipoEspecie(1L);
		especie1.setNombre("perro");
		cuidador.add(especie1);
		especie2.setIdTipoEspecie(2L);
		especie2.setNombre("gato");
		cuidador.add(especie2);
		idioma.setIdIdioma("ES");
		cuidador.add(idioma);
		servicio1.setIdServicio(1L);
		servicio1.setPrecio(09.00);
		cuidador.add(servicio1);
		servicio2.setIdServicio(3L);
		servicio2.setPrecio(13.00);
		cuidador.add(servicio2);
		direccionDto.setCalle("Lugar de Chisqueira");
		direccionDto.setPortal(15);
		direccionDto.setCp(15510);
		direccionDto.setIdPoblacion(4L);
		direccionDto.setNombrePoblacion("Ferrol");
		direccionDto.setIdProvincia(1L);
		direccionDto.setNombreProvincia("A Coruña");
		direccionDto.setIdpais(1L);
		direccionDto.setNombrePais("España");
		cuidador.setDireccion(direccionDto);
		cuidador=cuidadorServ.registro(cuidador);
		logger.debug(cuidador);
		assertNotNull(cuidador);

		logger.traceExit("Saliendo testRegistro...");
	}
	@Disabled
	@Test
	void testLogin() throws Exception {

		logger.traceEntry("Testing Cuidador login...");
		Cuidador cuidador= new Cuidador();
		try {
		cuidador=cuidadorServ.login("lugarcia132@gmail.com", "4dic1966");
		logger.debug(cuidador);
		assertNotNull(cuidador);
		logger.debug("test correcto");
		} catch (UserNotFoundException e) {
			logger.error("test incorrecto");
		}
		logger.traceExit("Saliendo testLogin...");
	}

	@Disabled
	@Test
	void testBaja() throws Exception{
		logger.traceEntry("Testing Cuidador baja...");
		Cuidador cuidador;
		cuidador= new Cuidador();

		logger.debug(cuidador);
		assertTrue(cuidadorServ.baja(5L));

		logger.traceExit("Saliendo testBaja...");
	}


	@Disabled
	@Test
	void testUpdate() throws Exception{
		logger.traceEntry("Testing Cuidador update...");
		Cuidador cuidador;
		cuidador= new Cuidador();
		cuidador= cuidadorServ.findById(6L);
		cuidador.setApellidos("Roca Zaino");
		cuidadorServ.update(cuidador);
		logger.debug(cuidador);
		assertNotNull(cuidador);

		logger.traceExit("Saliendo testUpdateCuidador...");
	}

}
