package com.contactura.contactura.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.contactura.contactura.model.Contactura;
import com.contactura.contactura.repository.ContacturaRepository;

@RestController
@RequestMapping("/contactura")
public class ContacturaController {
	
	
	@Autowired
	private ContacturaRepository repository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Contactura save(@RequestBody Contactura contactura) {
		return repository.save(contactura);
	}
	
	
	@GetMapping
	public List<Contactura> getAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Contactura getContact(@PathVariable Integer id){
		Contactura contato = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato com id passado é inválido."));
		return contato;
	}
	
	@PutMapping("/{id}")
	public Contactura update(@PathVariable Integer id, @RequestBody @Valid Contactura contactura) {
		Integer CONTACT_ID = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id inválido.")).getId();
		contactura.setId(CONTACT_ID);
		return repository.save(contactura);
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable Integer id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("Contato deletado.");
				}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado."));
	}

}
