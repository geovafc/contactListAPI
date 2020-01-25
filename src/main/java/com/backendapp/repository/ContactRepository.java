package com.backendapp.repository;

import com.backendapp.model.Contact;
import com.backendapp.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {
    @Query("select c from Contact c, People p where c.people.id =:id ")
    List<Contact> findByIdPeople(@Param("id") Long id);
}
