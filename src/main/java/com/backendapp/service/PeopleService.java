package com.backendapp.service;

import com.backendapp.model.Contact;
import com.backendapp.model.People;
import com.backendapp.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {
    private PeopleRepository peopleRepository;

    @Autowired
    private ContactService contactService;

    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository; }

    public People save (People people){
        peopleRepository.save(people);
        if (people.getContactList() != null){
            people.getContactList().forEach(c ->{
                c.setPeople(people);
                System.out.println("id"+people.getId());
                contactService.save(c);
            });
        }

        return people;
    }
    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public void deleteAll() {
        peopleRepository.deleteAll();
    }

    public double count() {
        return peopleRepository.count();
    }

    public Optional<People> update(Long id, People update) {
        return peopleRepository.findById(id)
                .map(p -> {
                    p.setName(update.getName());
                    p.setAge(update.getAge());
                    p.setGenre(update.getGenre());
                    People updated = peopleRepository.save(p);
                    return updated;
                });
    }

    public void delete(long id) {
        peopleRepository.deleteById(id);
    }

    public Optional<People> findById(long id) {
        List<Contact> contacts = contactService.findByIdPeople(id);
        Optional<People> people = peopleRepository.findById(id);
        people.get().setContactList(contacts);
        return people;
    }


}
