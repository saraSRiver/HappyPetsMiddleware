package com.happypets.aplicacion.service;


import java.util.List;


import com.happypets.aplicacion.model.Contrato;

public interface ContratoService {
	public Contrato findByid (Long idContrato)throws DataException;
	public List<Contrato> findByHistorialCuidador(Long idCuidador)
			throws DataException;
	public List<Contrato> findByHistorialCliente(Long idCliente)
			throws DataException;
	public Contrato create (Contrato c)
			throws DataException;
	public boolean updateEstado(Long idContrato, char idEstado)
			throws DataException;


}
