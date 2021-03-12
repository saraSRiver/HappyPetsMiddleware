package com.happypets.aplicacion.dao;

import java.util.List;

import com.happypets.aplicacion.model.MetodoPago;

public interface MetodoPagoDao {
public MetodoPago findByid(Long idCuentaBancaria);
public  MetodoPago findByidPropietario (Long idCuidador, Long idCliente);
}
