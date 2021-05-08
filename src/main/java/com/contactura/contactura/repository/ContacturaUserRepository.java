package com.contactura.contactura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contactura.contactura.model.ContacturaUser;

@Repository
public interface ContacturaUserRepository extends JpaRepository<ContacturaUser, Integer>{

}
