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
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService productoService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Producto>> getAll(){
		List<Producto> producto = new ArrayList<>();
		producto = productoService.getAll();
		return new ResponseEntity<List<Producto>>(producto, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Producto> getById(@PathVariable("id") Integer id) {
		Producto esp = productoService.getById(id);
		if (esp == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		Resource<Producto> resource = new Resource<Producto>(esp);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getById(id));

		resource.add(linkTo.withRel("Producto-resource"));
		
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

	public ResponseEntity<Object> save(@Valid @RequestBody Producto Producto){
		Producto esp = new Producto();
		esp = productoService.save(Producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(esp.getIdProducto()).toUri();

		return ResponseEntity.created(location).build();		
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody Producto Producto) {		
		productoService.update(Producto);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)

	public void delete(@PathVariable Integer id) {
		Producto esp = productoService.getById(id);
		if (esp == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			productoService.delete(id);

		}
	}
}
