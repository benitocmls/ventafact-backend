package com.ventafact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventafact.dao.IPersonaDAO;
import com.ventafact.model.Persona;
import com.ventafact.service.IPersonaService;


@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
	private IPersonaDAO personaDAO;

	@Override
	public Persona save(Persona persona) {
		return personaDAO.save(persona);
	}

	@Override
	public Persona update(Persona persona) {
		return personaDAO.save(persona);
	}

	@Override
	public void delete(int id) {
		personaDAO.delete(id);
	}

	@Override
	public Persona getById(int id) {
		return personaDAO.findOne(id);
	}

	@Override
	public List<Persona> getAll() {
		return personaDAO.findAll();
	}


}
