package com.ventafact.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventafact.dao.IVentaDAO;
import com.ventafact.model.Venta;
import com.ventafact.service.IVentaService;


@Service
public class VentaServiceImpl implements IVentaService{
	@Autowired
	private IVentaDAO dao;
	
	@Override
	public Venta registrar(Venta t) {
		t.getDetalleVenta().forEach(x -> x.setVenta(t));
		 dao.save(t);
		// t.getDetalleVenta().forEach(e -> dao.save(t));
		
		return t;
	}

	@Override
	public Venta modificar(Venta t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Venta listarId(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Venta> listar() {
		return dao.findAll();
	}
	
}
