package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Mascota;
import com.happypets.aplicacion.service.DataException;


public interface MascotaDao {
	public Mascota findById (Connection conection,Long idMascota)throws DataException;
	public Mascota findByPromocion(Connection conection, Integer idPromocion)throws DataException;
	public List<Mascota> findByIdCliente(Connection conection,Long idCliente)throws DataException;
	public Mascota create (Connection conection,Mascota mascota)throws DataException;
	public Mascota update (Connection conection,Mascota mascota)throws DataException;
	public boolean deleteMascota(Connection conection,Long id) throws DataException;
	public boolean deleteMascotaByCliente(Connection conection,Long idCliente) throws DataException;


}
