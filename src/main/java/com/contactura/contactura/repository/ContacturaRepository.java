package com.contactura.contactura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactura.contactura.model.Contactura;

@Repository
public interface ContacturaRepository extends JpaRepository<Contactura, Integer>{

}
