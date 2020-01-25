package com.backendapp.repository;

import com.backendapp.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("peopleRepository")
public interface PeopleRepository extends JpaRepository<People, Long> {

}
