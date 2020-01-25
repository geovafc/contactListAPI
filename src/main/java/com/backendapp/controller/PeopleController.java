package com.backendapp.controller;

import com.backendapp.model.People;
import com.backendapp.service.PeopleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping({"/peoples"})
public class PeopleController {
    private PeopleService peopleService;

    PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping(path ="/{id}")
    public Optional<People> peopleById(@PathVariable("id")  long id){
        return peopleService.findById(id);
    }

    @GetMapping
    public ResponseEntity <List <People>> findAll(){
        return new ResponseEntity<>(peopleService.findAll(), HttpStatus.OK);    }

    @PostMapping
    ResponseEntity <People> create (@RequestBody People people) {
        return new ResponseEntity <> (peopleService.save(people), HttpStatus.CREATED);
    }

    @PutMapping(path ="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody People people) {
        Optional<People> updated = peopleService.update(id,people);
        return Optional.ofNullable(ResponseEntity.ok().body(updated))
                .orElse(ResponseEntity.notFound().build());
    }

    //@DeleteMapping(path ={"/{id}"})
   // public ResponseEntity<?> delete(@PathVariable long id) {
   //     peopleService.delete(id);
   //     return ResponseEntity.ok().build();
   // }
}
