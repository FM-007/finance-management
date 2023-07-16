package com.felipemoreira.financemanagement.persons.domain.exceptions;

import jakarta.persistence.EntityExistsException;

public class PersonNotFoundException extends EntityExistsException {

    public PersonNotFoundException(String name) {
        super(String.format("Person with name %s not exists", name));
    }

    public PersonNotFoundException(Long id) {
        super(String.format("Person with id %s not exists!", id));
    }
}
