package com.happypets.aplicacion.dao.jdbc;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.happypets.aplicacion.model.Cuidador;
import com.happypets.aplicacion.model.Idioma;
import com.happypets.aplicacion.model.ServicioOfrecido;
import com.happypets.aplicacion.model.TipoEspecie;
import com.happypets.aplicacion.service.CuidadorCriteria;
import com.happypets.aplicacion.service.DataException;

import com.happypets.aplicacion.util.DBUtils;
import com.happypets.aplicacion.dao.CuidadorDao;
import com.happypets.aplicacion.dao.DireccionDTODAO;
import com.happypets.aplicacion.dao.DireccionDao;
import com.happypets.aplicacion.dao.ExperienciaDao;
import com.happypets.aplicacion.dao.IdiomaDao;
import com.happypets.aplicacion.dao.PuntuacionDao;
import com.happypets.aplicacion.dao.ServicioOfrecidoDAO;
import com.happypets.aplicacion.dao.TipoEspecieDao;


public class CuidadorDaoImpl implements CuidadorDao{

	private TipoEspecieDao tipoEspecieDao=null;
	private PuntuacionDao puntuacionDao=null;
	private ServicioOfrecidoDAO servicioOfrecidoDao=null;
	private IdiomaDao idiomaDao=null;
	private DireccionDao direccionDao=null;
	private DireccionDTODAO direccionDto = null;
	private ExperienciaDao experienciaDao=null;
	private static Logger logger= 
			LogManager.getLogger(CuidadorDaoImpl.class);
	public CuidadorDaoImpl() {
		tipoEspecieDao= new TipoEspecieDaoImpl();
		puntuacionDao= new PuntuacionDaoImpl();
		servicioOfrecidoDao= new ServicioOfrecidoDaoImpl();
		idiomaDao= new IdiomaDaoImpl();
		direccionDao= new DireccionDaoImpl();
		direccionDto = new DireccionDTODAOImpl();
		experienciaDao= new ExperienciaDaoImpl();
	}
	@Override
	public Cuidador findById(Connection conection,Long idCuidador) 
			throws DataException{
		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Cuidador result=null;
		String sql = null;
		try {
			// Ejecuta la query
			logger.trace("Creating statement...");

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT C.IDCUIDADOR, C.NOMBRE, C.APELLIDOS, C.EMAIL, ");
			stringBuilder.append(" C.PASSWORD, C.TELEFONO, C.IDEXPERIENCIA, C.FECHA_BAJA ");
			stringBuilder.append("	FROM CUIDADOR C WHERE C.IDCUIDADOR= ? AND C.FECHA_BAJA IS NULL ");
			sql = stringBuilder.toString();

			StringBuilder stringBuilder2 = new StringBuilder();
			stringBuilder2.append("findById: ");
			stringBuilder2.append(sql);
			logger.trace(stringBuilder2.toString());

			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			preparedStatement.setLong(i++, idCuidador);
			rs = preparedStatement.executeQuery();

			// Extract data from result set
			result = new Cuidador();

			if (rs.next()) {			
				result = loadNext(conection, rs); 
			}
		} catch (SQLException se) {
			logger.error(se);
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cuidador ")
			.append(idCuidador)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return result;		
	}

	@Override
	public Cuidador findByEmail(Connection conection,String email) 
			throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		Cuidador result=null;
		String sql = null;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT C.IDCUIDADOR, C.NOMBRE, C.APELLIDOS, C.EMAIL, ");
			stringBuilder.append(" C.PASSWORD, C.TELEFONO, C.IDEXPERIENCIA, C.FECHA_BAJA ");
			stringBuilder.append("	FROM CUIDADOR C WHERE C.EMAIL= ? AND C.FECHA_BAJA IS NULL ");
			// Execute a query
			sql =stringBuilder.toString();

			logger.trace("findByEmail:"+sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, 
					ResultSet.CONCUR_READ_ONLY);

			int i = 1;
			preparedStatement.setString(i++,email.toUpperCase());

			ResultSet resultSet = preparedStatement.executeQuery();
			result =new Cuidador();
			// Extract data from result set
			if (resultSet.next()) {
				result = loadNext(conection, resultSet); 
			}

		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cuidador ")
			.append(email)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return result;
	}
	@Override
	public List<Cuidador> findByCriteria(Connection conection,
			CuidadorCriteria cuidadorCriteria) throws DataException {

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		List<Cuidador> results=null;
		String sql = null;
		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT C.IDCUIDADOR, C.NOMBRE, C.APELLIDOS, C.EMAIL, ");
			stringBuilder.append(" C.PASSWORD, C.TELEFONO, C.IDEXPERIENCIA, C.FECHA_BAJA ");
			stringBuilder.append("	FROM CUIDADOR C  ");
			// Execute a query
			sql = stringBuilder.toString();
			if(cuidadorCriteria.getIdPoblacion() != null) {
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append(" INNER JOIN DIRECCION D ON D.IDCUIDADOR=C.IDCUIDADOR ");
				stringBuilder2.append(" INNER JOIN POBLACION PO ON PO.IDPOBLACION=D.IDPOBLACION ");
				sql += stringBuilder2.toString();
			}
			if(cuidadorCriteria.getIdServicio()!= null ||cuidadorCriteria.getPrecioDesde()!= null ||
					cuidadorCriteria.getPrecioHasta()!= null	) {
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append(" INNER JOIN SERVICIOOFRECIDO SO ON SO.IDCUIDADOR=C.IDCUIDADOR ");
				stringBuilder2.append(" INNER JOIN SERVICIO S ON SO.IDSERVICIO=S.IDSERVICIO ");
				sql += stringBuilder2.toString();
			}
			if(cuidadorCriteria.getIdTipoEspecie()!= null) {
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append(" INNER JOIN CUIDADOR_ATIENDE_TIPO CT ON C.IDCUIDADOR=CT.IDCUIDADOR  ");
				stringBuilder2.append(" INNER JOIN TIPO T ON CT.IDTIPO=T.IDTIPO ");
				sql += stringBuilder2.toString();
			}
			if(cuidadorCriteria.getIdIdioma()!= null) {
				StringBuilder stringBuilder2 = new StringBuilder();
				stringBuilder2.append(" INNER JOIN IDIOMACUIDADOR IC ON IC.IDCUIDADOR=C.IDCUIDADOR ");
				stringBuilder2.append("INNER JOIN IDIOMA I ON IC.IDIDIOMA=I.IDIDIOMA ");
				sql += stringBuilder2.toString();
			}
			boolean isFirst = false;
			if(cuidadorCriteria.getIdPoblacion() != null) {
				if(isFirst == true) {
					sql+= " AND PO.IDPOBLACION = ? ";
				}
				else {
					sql += " WHERE PO.IDPOBLACION = ? ";
					isFirst = true;
				}
			}
			if(cuidadorCriteria.getIdServicio()!= null) {
				if(isFirst == true) {
					sql += " AND S.IDSERVICIO = ? ";
				}
				else {
					sql += " WHERE S.IDSERVICIO = ? ";
					isFirst = true;
				}
			}
			if(cuidadorCriteria.getIdTipoEspecie()!= null) {
				if(isFirst == true) {
					sql += " AND T.IDTIPO = ? ";
				}
				else {
					sql += " WHERE T.IDTIPO = ? ";
					isFirst = true;
				}
			}
			if(cuidadorCriteria.getIdIdioma()!= null) {
				if(isFirst == true) {
					sql += " AND I.IDIDIOMA = ? ";
				}
				else {
					sql += " WHERE I.IDIDIOMA = ?  ";
					isFirst = true;
				}
			}
			if(cuidadorCriteria.getPrecioDesde()!= null) {
				if(isFirst == true) {
					sql += " AND SO.PRECIO > ? ";
				}
				else {
					sql += " WHERE SO.PRECIO > ?  ";
					isFirst = true;
				}
			}
			if(cuidadorCriteria.getPrecioHasta()!= null) {
				if(isFirst == true) {
					sql += " AND SO.PRECIO < ? ";
				}
				else {
					sql += " WHERE SO.PRECIO < ?  ";
					isFirst = true;
				}
			}
			if(isFirst == true) {
				sql+= " AND FECHA_BAJA IS NULL";
			}
			else {
				sql+= " WHERE FECHA_BAJA IS NULL ";
			}

			logger.trace(sql);
			preparedStatement = conection.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int i = 1;
			if(cuidadorCriteria.getIdPoblacion() != null) {
				preparedStatement.setLong(i++, cuidadorCriteria.getIdPoblacion());
			}
			if(cuidadorCriteria.getIdServicio()!= null) {
				preparedStatement.setLong(i++, cuidadorCriteria.getIdServicio());

			}
			if(cuidadorCriteria.getIdTipoEspecie()!= null) {
				preparedStatement.setLong(i++, cuidadorCriteria.getIdTipoEspecie());

			}
			if(cuidadorCriteria.getIdIdioma()!= null) {
				preparedStatement.setString(i++, cuidadorCriteria.getIdIdioma());

			}
			if(cuidadorCriteria.getPrecioDesde()!= null) {
				preparedStatement.setDouble(i++, cuidadorCriteria.getPrecioDesde());
			}
			if(cuidadorCriteria.getPrecioHasta()!= null) {
				preparedStatement.setDouble(i++, cuidadorCriteria.getPrecioHasta());
			}
			rs = preparedStatement.executeQuery();
			results = new ArrayList<Cuidador>();
			Cuidador cuidador = new Cuidador();

			// Extract data from result set
			while (rs.next()) {
				cuidador = loadNext(conection, rs);
				results.add(cuidador);			
			}
			// Clean-up environment
		} catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append(" cuidadores ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return results;
	}

	private Cuidador loadNext(Connection conection,ResultSet resultSet) throws DataException, SQLException {
		int i = 1;
		Cuidador cuidador= new Cuidador();
		cuidador.setIdcuidador(resultSet.getLong(i++));
		cuidador.setNombre(resultSet.getString(i++));
		cuidador.setApellidos(resultSet.getString(i++));
		cuidador.setEmail(resultSet.getString(i++));
		cuidador.setPassword(resultSet.getString(i++));
		cuidador.setTelefono(resultSet.getString(i++));
		cuidador.setExperiencia(experienciaDao.findById(conection, resultSet.getInt(i++), "es"));
		cuidador.setFechaBaja(resultSet.getDate(i++));
		cuidador.setDireccion(direccionDto.findByIdCuidador(conection,
				cuidador.getIdcuidador()));
		cuidador.setServiciosOfrecidos(servicioOfrecidoDao.findByCuidador
				(conection, cuidador.getIdcuidador()));
		cuidador.setEspecies(tipoEspecieDao.findByIdCuidador(conection, 
				cuidador.getIdcuidador(), "es"));
		cuidador.setIdiomas(idiomaDao.findByIdiomasCuidador(conection, 
				cuidador.getIdcuidador()));
		cuidador.setPuntuacionMedia(puntuacionDao.findByMedia(conection,
				cuidador.getIdcuidador()));


		return cuidador;
	}
	@Override
	public Cuidador create(Connection conection,Cuidador cuidador) throws DataException{

		PreparedStatement preparedStatement = null;
		ResultSet rs=null;
		try {         
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" INSERT INTO CUIDADOR (NOMBRE, APELLIDOS, EMAIL, ");
			stringBuilder.append(" PASSWORD, TELEFONO, IDEXPERIENCIA)  ");
			stringBuilder.append(" VALUES(? , ? , ?, ? , ?, ? ) ");
			String queryString = stringBuilder.toString(); 

			preparedStatement = conection.prepareStatement(queryString, Statement.RETURN_GENERATED_KEYS);

			int i = 1;
			preparedStatement.setString(i++, cuidador.getNombre());
			preparedStatement.setString(i++, cuidador.getApellidos());
			preparedStatement.setString(i++, cuidador.getEmail());
			preparedStatement.setString(i++, cuidador.getPassword());
			preparedStatement.setString(i++, cuidador.getTelefono());
			preparedStatement.setInt(i++, cuidador.getExperiencia().getIdExperiencia());

			preparedStatement.executeUpdate();
			rs = preparedStatement.getGeneratedKeys();
			i =1;
			if(rs.next()) {

				cuidador.setIdcuidador(rs.getLong(i++));
			}

			createServicioOfrecido(conection, cuidador);
			createEspeciesCuidadas(conection, cuidador);
			cuidador.getDireccion().setIdcuidador(cuidador.getIdcuidador());
			direccionDao.createDirCuidador(conection, cuidador.getDireccion());
			createIdiomaCuid(conection, cuidador);

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido crear el cuidador ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return cuidador;
	}

	protected void createEspeciesCuidadas(Connection conection,Cuidador c) throws DataException {
		Statement statement = null;
		ResultSet rs = null;

		try {
			String sql = "INSERT INTO CUIDADOR_ATIENDE_TIPO(IDCUIDADOR, IDTIPO) VALUES  ";
			TipoEspecie te = null;
			List<TipoEspecie> especies = c.getEspecies();
			for (int j = 0; j<especies.size(); j++) {
				te = especies.get(j);
				sql+= "("+c.getIdcuidador()+", "+te.getIdTipoEspecie()+")";
				if (j<(especies.size()-1)) {
					sql+=", ";
				}
			}			
			StringBuilder stringBuilder = new StringBuilder()
			.append("sql insert especies atendidas: ")
			.append(sql);
			logger.trace(stringBuilder.toString());
			statement = conection.createStatement();

			statement.executeUpdate(sql);

		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append(" las especies del cuidador ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(statement);
		}
	}
	protected void createServicioOfrecido(Connection conection,Cuidador c) throws DataException {
		Statement statement = null;
		ResultSet rs = null;

		try {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" INSERT INTO SERVICIOOFRECIDO(IDCUIDADOR, IDSERVICIO, PRECIO) ");
			stringBuilder.append(" VALUES ");
			String sql = stringBuilder.toString();
			ServicioOfrecido te = null;
			List<ServicioOfrecido> servicioOfrecido = c.getServiciosOfrecidos();
			for (int j = 0; j<servicioOfrecido.size(); j++) {
				te = servicioOfrecido.get(j);
				sql+= "("+c.getIdcuidador()+", "+te.getIdServicio()+ "," + te.getPrecio() + ")";
				if (j<(servicioOfrecido.size()-1)) {
					sql+=", ";
				}
			}			
			StringBuilder stringBuilder2 = new StringBuilder()
			.append("sql insert especies atendidas: ")
			.append(sql);
			logger.trace(stringBuilder2.toString());
			statement = conection.createStatement();

			statement.executeUpdate(sql);


		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se han podido encontrar ")
			.append(" las especies del cuidador ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(statement);
		}
	}

	private void createIdiomaCuid(Connection connection, Cuidador cuidador) throws DataException {
		PreparedStatement preparedStatement = null;

		try {
			for(Idioma idiom : cuidador.getIdiomas()) {
				// Execute a query
				logger.trace("Creating statement...");

				StringBuilder stringBuilder = new StringBuilder();
				stringBuilder.append("INSERT INTO IDIOMACUIDADOR(IDCUIDADOR, IDIDIOMA) ");
				stringBuilder.append(" VALUES (?, ? )");
				String queryString = stringBuilder.toString();
				preparedStatement = connection.prepareStatement(queryString);

				int i = 1;
				preparedStatement.setLong(i++,cuidador.getIdcuidador());
				preparedStatement.setString(i++, idiom.getIdIdioma());
				preparedStatement.executeUpdate();
			}
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cuidador ")
			.append(cuidador.getIdcuidador())
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
	}
	@Override
	public Cuidador update(Connection conection,Cuidador cuidador) throws DataException {
		PreparedStatement preparedStatement = null;
		Cuidador c=null;
		try { 
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE CUIDADOR SET NOMBRE= ?, ");
			stringBuilder.append(" APELLIDOS= ?, PASSWORD= ?,  EMAIL= ?, ");
			stringBuilder.append(" TELEFONO= ?, IDEXPERIENCIA= ? ");
			stringBuilder.append(" WHERE IDCUIDADOR = ? ");
			String queryString = stringBuilder.toString();

			preparedStatement = conection.prepareStatement(queryString);
			int i = 1;
			preparedStatement.setString(i++,cuidador.getNombre());
			preparedStatement.setString(i++,cuidador.getApellidos());
			preparedStatement.setString(i++,cuidador.getPassword());
			preparedStatement.setString(i++,cuidador.getEmail());
			preparedStatement.setString(i++,cuidador.getTelefono());
			preparedStatement.setLong(i++,(cuidador.getExperiencia().getIdExperiencia()));

			preparedStatement.setLong(i++, cuidador.getIdcuidador());

			int updatedRows = preparedStatement.executeUpdate();
			if (updatedRows == 0) {

				throw new DataException(" No ha sido posible actualizar 'cuidador' ");
			}
			//llamada al metodo create para actualizar
			c=new Cuidador();	
			c=findById(conection, cuidador.getIdcuidador());
			//actualizacion de los servicios y las especies cuidadas
			for(ServicioOfrecido serv: cuidador.getServiciosOfrecidos()) {
				servicioOfrecidoDao.update(conection, serv);
			}
			tipoEspecieDao.deleteByIdCuidador(conection, cuidador.getIdcuidador());
			createEspeciesCuidadas(conection, cuidador);

			idiomaDao.deleteByCuidador(conection, cuidador.getIdcuidador());
			createIdiomaCuid(conection, cuidador);

			direccionDao.deleteByCuidador(conection, cuidador.getIdcuidador());
			direccionDao.createDirCuidador(conection, cuidador.getDireccion());
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cuidador ")
			.append(cuidador.getIdcuidador())
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);

		}
		return c;
	}

	@Override
	public boolean delete(Connection conection,Long idCuidador) 
			throws DataException {

		PreparedStatement preparedStatement = null;

		try {   
			tipoEspecieDao.deleteByIdCuidador(conection, idCuidador);
			idiomaDao.deleteByCuidador(conection, idCuidador);
			direccionDao.deleteByCuidador(conection, idCuidador);
			logger.trace("Creating Statement...");
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(" UPDATE CUIDADOR SET FECHA_BAJA= ? ");
			stringBuilder.append("WHERE IDCUIDADOR = ? ");
			String queryString = stringBuilder.toString(); 

			preparedStatement = conection.prepareStatement(queryString, 
					Statement.RETURN_GENERATED_KEYS);
			int i=1;
			preparedStatement.setDate(i++, new java.sql.Date(new Date().getTime()));
			preparedStatement.setLong(i++, idCuidador);
			int deleteRows = preparedStatement.executeUpdate();
			if (deleteRows == 0) {
			
				StringBuilder stringBuilder2 = new StringBuilder()
				.append(" No ha sido posible dar de baja ")
				.append("al cuidador ");
				throw new DataException(stringBuilder2.toString());
			} 
		}catch (SQLException se) {
			logger.error(se);
			StringBuilder stringBuilder = new StringBuilder()
			.append("No se ha podido encontrar ")
			.append(" el cuidador ")
			.append(idCuidador)
			.append(" ")
			.append(se);
			throw new DataException(stringBuilder.toString());
		} finally {
			DBUtils.closePreparedStatement(preparedStatement);
		}
		return true;
	}

}