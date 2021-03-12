package com.happypets.aplicacion.dao;

import java.sql.Connection;
import java.util.List;

import com.happypets.aplicacion.model.Experiencia;
import com.happypets.aplicacion.service.DataException;

public interface ExperienciaDao {
public Experiencia findById(Connection conection,Integer idExperiencia, String idioma)throws DataException;
public List<Experiencia> findAll(Connection conection, String idioma)throws DataException;


}
