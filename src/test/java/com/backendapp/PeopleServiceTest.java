package com.backendapp;

import com.backendapp.model.Contact;
import com.backendapp.model.People;
import com.backendapp.repository.PeopleRepository;
import com.backendapp.service.PeopleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PeopleServiceTest {


    @Autowired
    //private PeopleRepository peopleRepository;
    private PeopleService peopleService;

    @Test
    void getAllPeoples() {
        People peopleSample = new People(1L, "Pedro", 40, "male",null);
        Contact contact1 = new Contact(1L,"whatsapp","91998362314","",null);
        Contact contact2 = new Contact(2L,"phone","9132973939","",null);
        Contact contact3 = new Contact(3L,"email","","geo@email.com",null);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        peopleSample.setContactList(contacts);
        peopleService.save(peopleSample);
        //PeopleService peopleService = new PeopleService(peopleRepository);

        People firstResult = peopleService.findAll().get(0);

        assertEquals(peopleSample.getName(), firstResult.getName());
        assertEquals(peopleSample.getAge(), firstResult.getAge());
        assertEquals(peopleSample.getGenre(), firstResult.getGenre());
        assertEquals(peopleSample.getId(), firstResult.getId());
    }
/*
    @AfterEach
    void tearDown () {
        peopleService.deleteAll();
    }
*/
    @Test
    void saveAPeople() {
        //PeopleService peopleService = new PeopleService(peopleRepository);
        People peopleSample = new People(1L, "Carol", 40, "feminine",null);
        Contact contact1 = new Contact(1L,"whatsapp","91998362314","",null);
        Contact contact2 = new Contact(2L,"phone","9132973939","",null);
        Contact contact3 = new Contact(3L,"email","","geo@email.com",null);
        List<Contact> contacts = new ArrayList<>();
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        peopleSample.setContactList(contacts);

        peopleService.save(peopleSample);

        assertEquals(1.0, peopleService.count());
    }

    @Test
    void updateAPeople() {
        People peopleSample = new People(2L, "Carol", 40, "feminine",new ArrayList<>());
        peopleService.save(peopleSample);

        People update = new People(2L, "Carol Fernandes", 40, "feminine",new ArrayList<>());
        People updated = peopleService.update(update.getId(),update).get();

        assertEquals(update.toString(), updated.toString());
    }

}