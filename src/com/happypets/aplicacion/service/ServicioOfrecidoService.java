package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.ServicioOfrecido;

public interface ServicioOfrecidoService {
	public List<ServicioOfrecido> findByCuidador(Long idCuidador) 
			throws DataException;
	public void create (ServicioOfrecido so)throws DataException;
	public boolean update(ServicioOfrecido so) throws DataException;
	public boolean deleteByIdCuidador(Long idCuidador)throws DataException;
}
