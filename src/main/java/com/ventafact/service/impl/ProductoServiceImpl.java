package com.ventafact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventafact.dao.IProductoDAO;
import com.ventafact.model.Producto;
import com.ventafact.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService{
	@Autowired
	private IProductoDAO dao;

	@Override
	public Producto registrar(Producto t) {
		return dao.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Producto listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Producto> listar() {
		return dao.findAll();
	}
}
