package com.contactura.contactura;

import java.util.stream.LongStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.contactura.contactura.model.Contactura;
import com.contactura.contactura.repository.ContacturaRepository;

@SpringBootApplication
public class ContacturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContacturaApplication.class, args);
	}
	
//	@Bean
//	CommandLineRunner init(ContacturaRepository repository) {
//		return args ->{
//		LongStream.range(1, 3)
//		.mapToObj(i -> {
//			Contactura contact = new Contactura();
//			contact.setName("Contactura user "+i);
//			contact.setEmail("contactura "+ i + "@gmail.com");
//			contact.setPhone("(081)9 "+i+i+i+i+"-"+i+i+i+i);
//			return contact;
//		}).map(m -> repository.save(m))
//		.forEach(System.out::println);
//	};
//	}

}
