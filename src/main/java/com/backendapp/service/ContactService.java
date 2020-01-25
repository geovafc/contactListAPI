package com.backendapp.service;

import com.backendapp.model.Contact;
import com.backendapp.repository.ContactRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    ContactRepository contactRepository;
    
    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public Contact save (Contact contact){
        return contactRepository.save(contact);
    }

    List<Contact> findByIdPeople(Long id){
        return contactRepository.findByIdPeople(id);
    }
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }
}
