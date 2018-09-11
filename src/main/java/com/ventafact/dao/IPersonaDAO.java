package com.ventafact.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import com.ventafact.model.Persona;

public interface IPersonaDAO extends JpaRepository<Persona, Integer>{

}
