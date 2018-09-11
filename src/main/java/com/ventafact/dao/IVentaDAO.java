package com.ventafact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventafact.model.Persona;
import com.ventafact.model.Venta;

public interface IVentaDAO extends JpaRepository<Venta, Integer>{

}
