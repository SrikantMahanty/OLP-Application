package com.learnSphere.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnSphere.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
