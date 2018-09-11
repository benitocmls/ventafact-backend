package com.ventafact.controller;


import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
import com.ventafact.model.Persona;
import com.ventafact.service.IPersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

	@Autowired
	private IPersonaService personaService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Persona>> getAll() {
		List<Persona> personas = new ArrayList<>();
		personas = personaService.getAll();
		
		return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Persona> getById(@PathVariable("id") Integer id) {
		Persona persona = personaService.getById(id);
		
		if (persona == null) {
			throw new ModeloNotFoundException("ID: " + id);
		}
		
		Resource<Persona> resource = new Resource<Persona>(persona);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getById(id));

		resource.add(linkTo.withRel("Persona-resource"));
		
		return resource;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> save(@Valid @RequestBody Persona Persona) {
		Persona personaSave = new Persona();
		personaSave = personaService.save(Persona);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(personaSave.getIdPersona()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> update(@Valid @RequestBody Persona Persona) {
		personaService.update(Persona);
		

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable Integer id) {
		Persona Persona = personaService.getById(id);
		
		if (Persona == null) {
			throw new ModeloNotFoundException("ID: " + id);
		} else {
			personaService.delete(id);

		}
	}
}
