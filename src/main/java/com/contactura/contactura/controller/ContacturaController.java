package com.contactura.contactura.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactura.contactura.model.Contactura;
import com.contactura.contactura.repository.ContacturaRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin()
@RestController
@RequestMapping("/contactura")
public class ContacturaController {
	
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@Autowired
	private ContacturaRepository repository;

	@GetMapping
	public List<?> findAll() {
		return repository.findAll();
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());

	}
	
	@PostMapping
	public Contactura create(@RequestBody Contactura contactura){
		
		return repository.save(contactura);
		
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Contactura contactura){
		
		return repository.findById(id)
				.map(record -> {
					record.setName(contactura.getName());
					record.setEmail(contactura.getEmail());
					record.setPhone(contactura.getPhone());
					Contactura update = repository.save(record);
					return ResponseEntity.ok().body(update);
				}).orElse(ResponseEntity.notFound().build());	
		
	}
	
	@DeleteMapping(path = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body(gson.toJson("Deletado com Sucesso"));
				}).orElse(ResponseEntity.notFound().build());
		
	}
}
