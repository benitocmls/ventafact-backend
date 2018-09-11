package com.ventafact.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ventafact.exception.ModeloNotFoundException;
import com.ventafact.model.Venta;
import com.ventafact.service.IVentaService;

@RestController
@RequestMapping("/ventas")
public class VentaController {

	@Autowired
	private IVentaService ventaService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> getAll(){
		List<Venta> listVenta = new ArrayList<>();
		listVenta = ventaService.getAll();
		return new ResponseEntity<List<Venta>>(listVenta, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Venta> getById(@PathVariable("id") Integer id) {
		Venta cons = ventaService.getById(id);
		if (cons == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		Resource<Venta> resource = new Resource<Venta>(cons);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getById(id));
		resource.add(linkTo.withRel("Venta-resource"));
		
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Valid @RequestBody Venta venta){
		Venta cons = new Venta();
		cons = ventaService.save(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cons.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody Venta Venta) {		
		ventaService.update(Venta);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Venta cons = ventaService.getById(id);
		if (cons == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			ventaService.delete(id);
		}
	}
}
