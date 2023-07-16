package com.felipemoreira.financemanagement.controllers;

import com.felipemoreira.financemanagement.domain.dtos.MessageDTO;
import com.felipemoreira.financemanagement.domain.dtos.PersonDTO;
import com.felipemoreira.financemanagement.services.PersonService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pessoas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonController {

    private PersonService personService;

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
