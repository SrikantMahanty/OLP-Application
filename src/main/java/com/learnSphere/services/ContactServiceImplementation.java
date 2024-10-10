package com.learnSphere.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learnSphere.entity.Contact;
import com.learnSphere.repository.ContactRepository;

@Service
public class ContactServiceImplementation implements ContactServices{
	
	@Autowired
	private ContactRepository contactRep;

	@Override
	public String sentMessage(Contact contact) {
		contactRep.save(contact);
		return "Your message has been sent successfully!";
	}

}
