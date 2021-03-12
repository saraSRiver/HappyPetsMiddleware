package com.happypets.aplicacion.dao.jdbc;

import java.util.List;

import com.happypets.aplicacion.model.MetodoPago;
import com.happypets.aplicacion.dao.MetodoPagoDao;

public class MetodoPagoDaoImpl implements MetodoPagoDao{
	// JDBC driver name and database URL
		static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/happypets?serverTimezone=UTC";

		// Database credentials
		static final String USER = "HappypetDVA";
		static final String PASS = "promesa93";
		
		public MetodoPagoDaoImpl() {
			
		}
		
	@Override
	public MetodoPago findByid(Long idCuentaBancaria) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public MetodoPago findByidPropietario(Long idCuidador, Long idCliente) {
		// TODO Auto-generated method stub
		return null;
	}

}
