package com.happypets.aplicacion.service;


import java.util.List;

import com.happypets.aplicacion.model.Experiencia;

public interface ExperienciaService {
	public Experiencia findById(Integer idExperiencia, String idioma)throws DataException;
	public List<Experiencia> findAll(String idioma)throws DataException;
}
