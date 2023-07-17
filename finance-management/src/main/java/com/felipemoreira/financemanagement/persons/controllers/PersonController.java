package com.felipemoreira.financemanagement.persons.controllers;

import com.felipemoreira.financemanagement.persons.controllers.docs.PersonControllerDocs;
import com.felipemoreira.financemanagement.persons.domain.dtos.MessageDTO;
import com.felipemoreira.financemanagement.persons.domain.dtos.PersonDTO;
import com.felipemoreira.financemanagement.persons.services.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController implements PersonControllerDocs {

    private PersonService personService;

    @GetMapping("/{name}")
    public PersonDTO findByName(@PathVariable String name) {
        return personService.findByName(name);
    }

    @GetMapping
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createdPerson(personDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageDTO updatePerson(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) {
        return personService.updatePerson(id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
    }
}
