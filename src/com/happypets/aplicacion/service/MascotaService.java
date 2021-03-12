package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.Mascota;

public interface MascotaService {
	public Mascota findById (Long idMascota)throws DataException;
	public List<Mascota> findByIdCliente(Long idCliente)throws DataException;
	public Mascota create (Mascota mascota)throws DataException;
	public Mascota update (Mascota mascota)throws DataException;
	public boolean deleteMascota(Long id) throws DataException;
	public boolean deleteMascotaByCliente(Long idCliente) throws DataException;
}
