package com.contactura.contactura.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Contactura {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank(message = "Campo nome não pode ser vazio.")
	private String name;
	@NotBlank(message = "Campo Email não pode ser vazio.")
	private String email;
	@NotBlank(message = "Campo telefone não pode ser vazio.")
	private String phone;
	
}
