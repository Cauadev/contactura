package com.contactura.contactura.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.contactura.contactura.model.ContacturaUser;
import com.contactura.contactura.repository.ContacturaUserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@CrossOrigin()
@RestController
@RequestMapping("/user")
public class ContacturaUserController {
	
	
	@Autowired
	private ContacturaUserRepository repository;
	
	public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@RequestMapping("/login")
	@GetMapping
	public ResponseEntity<?> login(HttpServletRequest request){
		String decode = new String(Base64.getDecoder().decode(request.getHeader("Authorization").split(" ")[1]));
		boolean isAdmin = repository.findByUsername(decode.split(":")[0]).isAdmin();
		String token = request.getHeader("Authorization")
				.substring("Basic".length()).trim();
		return ResponseEntity.ok().body(gson.toJson(token+":"+isAdmin));
	}
	
	@GetMapping
	public List<?> findAll(){
		return repository.findAll();
		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		
		return repository.findById(id)
				.map(user -> ResponseEntity.ok().body(user))
				.orElse(ResponseEntity.notFound().build());			
		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ContacturaUser create(@RequestBody ContacturaUser user){
		user.setPassword(criptografarSenha(user.getPassword()));
		return repository.save(user);
		
	}
	
	@PutMapping(value = "{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody ContacturaUser user){
		
		return repository.findById(id)
				.map(record -> {
					record.setName(user.getName());
					record.setUsername(user.getUsername());
					record.setPassword(criptografarSenha(user.getPassword()));
					record.setAdmin(user.isAdmin());
					ContacturaUser update = repository.save(record);
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
	
	public String criptografarSenha(String password){
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwordParaCriptografar = passwordEncoder.encode(password);
		return passwordParaCriptografar;
	}

}
