package com.contactura.contactura.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

import com.contactura.contactura.model.ContacturaUser;
import com.contactura.contactura.repository.ContacturaUserRepository;

@RestController
@RequestMapping("/user")
public class ContacturaUserController {
	
	
	@Autowired
	private ContacturaUserRepository repository;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ContacturaUser save(@RequestBody ContacturaUser user) {
		user.setPassword(criptografarSenha(user.getPassword()));
		return repository.save(user);
	}
	
	private String criptografarSenha(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}
	
	
	@GetMapping
	public List<ContacturaUser> getAll(){
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public ContacturaUser getContact(@PathVariable Integer id){
		ContacturaUser contato = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "usuario com id passado é inválido."));
		return contato;
	}
	
	@PutMapping("/{id}")
	public ContacturaUser update(@PathVariable Integer id, @RequestBody @Valid ContacturaUser user) {
		Integer CONTACT_ID = repository.findById(id)
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"id inválido.")).getId();
		user.setId(CONTACT_ID);
		user.setPassword(criptografarSenha(user.getPassword()));
		user.setAdmin(false);
		return repository.save(user);
	}
	
	@DeleteMapping(path = {"/{id}"})
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Integer id){
		return repository.findById(id)
				.map(record -> {
					repository.deleteById(id);
					return ResponseEntity.ok().body("usuario deletado.");
				}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não encontrado."));
	}

}
