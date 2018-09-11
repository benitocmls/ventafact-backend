package com.ventafact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventafact.dao.IVentaDAO;
import com.ventafact.model.Venta;
import com.ventafact.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaDAO ventaDAO;
	
	@Override
	public Venta save(Venta venta) {
		return ventaDAO.save(venta);
	}

	@Override
	public Venta update(Venta venta) {
		return ventaDAO.save(venta);
	}

	@Override
	public void delete(int id) {
		ventaDAO.delete(id);
	}

	@Override
	public Venta getById(int id) {
		return ventaDAO.findOne(id);
	}

	@Override
	public List<Venta> getAll() {
		return ventaDAO.findAll();
	}

}
