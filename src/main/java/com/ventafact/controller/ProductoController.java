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
import com.ventafact.model.Producto;
import com.ventafact.service.IProductoService;

@RestController
@RequestMapping("/producto")
public class ProductoController {
	@Autowired
	private IProductoService service;
		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> listar(){
		List<Producto> Productos = new ArrayList<>();
		Productos = service.listar();
		return new ResponseEntity<List<Producto>>(Productos, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Producto> listarId(@PathVariable("id") int id) {
		Producto per = service.listarId(id);
		if (per == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		Resource<Producto> resource = new Resource<Producto>(per);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarId(id));
		resource.add(linkTo.withRel("Producto-resource"));
		
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Producto Producto){
		Producto med = new Producto();
		med = service.registrar(Producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(med.getIdProducto()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> actualizar(@Valid @RequestBody Producto Producto) {		
		service.modificar(Producto);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminar(@PathVariable Integer id) {
		Producto med = service.listarId(id);
		if (med == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			service.eliminar(id);
		}
	}
}
