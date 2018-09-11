package com.ventafact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventafact.dao.IProductoDAO;
import com.ventafact.model.Producto;
import com.ventafact.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

	@Autowired
	private IProductoDAO productoDAO;
	
	@Override
	public Producto save(Producto producto) {
		return productoDAO.save(producto);
	}

	@Override
	public Producto update(Producto producto) {
		return productoDAO.save(producto);
	}

	@Override
	public void delete(int id) {
		productoDAO.delete(id);
	}

	@Override
	public Producto getById(int id) {
		return productoDAO.findOne(id);
	}

	@Override
	public List<Producto> getAll() {
		return productoDAO.findAll();
	}

}
