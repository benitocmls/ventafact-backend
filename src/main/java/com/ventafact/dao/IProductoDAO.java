package com.ventafact.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ventafact.model.Persona;
import com.ventafact.model.Producto;

public interface IProductoDAO extends JpaRepository<Producto, Integer>{

}
