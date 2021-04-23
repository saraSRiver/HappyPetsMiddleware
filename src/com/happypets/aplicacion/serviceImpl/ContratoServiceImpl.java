package com.happypets.aplicacion.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.dao.ClienteDao;
import com.happypets.aplicacion.dao.ContratoDao;
import com.happypets.aplicacion.dao.PromocionDao;
import com.happypets.aplicacion.dao.ServicioOfrecidoDAO;
import com.happypets.aplicacion.dao.jdbc.ClienteDaoImpl;
import com.happypets.aplicacion.dao.jdbc.ContratoDaoImpl;
import com.happypets.aplicacion.dao.jdbc.PromocionDaoImpl;
import com.happypets.aplicacion.dao.jdbc.ServicioOfrecidoDaoImpl;
import com.happypets.aplicacion.model.Cliente;
import com.happypets.aplicacion.model.Contrato;
import com.happypets.aplicacion.model.Promocion;
import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.service.ContratoService;
import com.happypets.aplicacion.service.DataException;
import com.happypets.aplicacion.util.DBUtils;

public class ContratoServiceImpl implements ContratoService{
	private static Logger logger= 
			LogManager.getLogger(ContratoServiceImpl.class);
	private ContratoDao contratoDao;
	private PromocionDao promocionDao;
	private ClienteDao clienteDao;
	private ServicioOfrecidoDAO servOfrecido;
	public ContratoServiceImpl() {
		clienteDao = new ClienteDaoImpl();
		contratoDao= new ContratoDaoImpl();
		promocionDao = new PromocionDaoImpl();
		servOfrecido = new ServicioOfrecidoDaoImpl();
	}
	public Contrato findByid (Long idContrato)throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		Contrato contrato;
		try {
			conection.setAutoCommit(false);
			contrato=contratoDao.findByid(conection, idContrato);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return contrato;

	}
	public List<Contrato> findByHistorialCuidador(Long idCuidador)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Contrato> contratos;
		try {
			conection.setAutoCommit(false);
			contratos=contratoDao.findByHistorialCuidador
					(conection, idCuidador);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return contratos;
	}
	public List<Contrato> findByHistorialCliente(Long idCliente)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;
		List<Contrato> contratos;
		try {
			conection.setAutoCommit(false);
			contratos=contratoDao.findByHistorialCliente
					(conection, idCliente);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return contratos;

	}
	public Contrato create (Contrato c)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			ServicioOfrecido servOfre = servOfrecido.findByCuidadorServicio(conection, c.getIdCuidador(), c.getIdServicio());
			Cliente  cliente = clienteDao.findById(conection, c.getIdCliente());
			List<Promocion> promociones = promocionDao.findAll(conection);

			if(cliente.getPromocion()!= null) {
				if(cliente.getEstadoPromocion() == true) {
					c.setPrecioFinal(calcularDescuento(servOfre.getPrecio(), cliente.getPromocion()
							.getPorcentajeDescuento()));
				}
				else {
					c.setPrecioFinal(servOfre.getPrecio());
				}
			}
			else {
				c.setPrecioFinal(servOfre.getPrecio());
			}

			contratoDao.create(conection, c);
			actualizarPromocionCliente(conection, promociones,
					c.getIdCliente());
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return c;

	}

	private Double calcularDescuento(Double precio, Integer desc)
			throws DataException {
		Double descuento=0.0;
		Double precioFinal;

		descuento=(precio * desc/100);
		precioFinal=precio-descuento;

		return precioFinal;
	}
	private void actualizarPromocionCliente(Connection conection, 
			List<Promocion> list, Long idCliente) throws DataException {
		List<Contrato> cont = 
				contratoDao.findByHistorialCliente(conection, idCliente);

		for(Promocion p : list) {
			if(p.getNumerador() == cont.size()) {
				clienteDao.updateIdPromocion(conection, p.getIdpromocion(),idCliente);
			}
		}
	}

	public boolean updateEstado(Long idContrato, char idEstado)
			throws DataException{
		Connection conection= DBUtils.getConnection();
		boolean commit = false;

		try {
			conection.setAutoCommit(false);
			contratoDao.updateEstado(conection, idContrato, idEstado);
			commit = true;

		} catch (SQLException se) {
			logger.error(se);
			throw new DataException(se);
		}
		finally {
			DBUtils.closeConnection(conection,commit);
		}
		return true;
	}
}
