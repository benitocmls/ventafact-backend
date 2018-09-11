package com.ventafact.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ventafact.dto.VentaDTO;
import com.ventafact.exception.ModeloNotFoundException;
import com.ventafact.model.Persona;
import com.ventafact.model.Venta;
import com.ventafact.service.IVentaService;

@RestController
@RequestMapping("/venta")
public class VentaController {
	@Autowired
	private IVentaService service;
		
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Venta>> listar(){
		List<Venta> consecialidad = new ArrayList<>();
		consecialidad = service.listar();
		return new ResponseEntity<List<Venta>>(consecialidad, HttpStatus.OK);
	}
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> registrar(@Valid @RequestBody Venta venta){
		Venta med =  service.registrar(venta);
		//System.out.println(venta.getDetalleVenta().get(0).getVenta().getIdVenta());
		System.out.println("12321 "+med.getDetalleVenta().get(0).getVenta().getIdVenta());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(med.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();		
	}
	
	
	
	
	
	
}
