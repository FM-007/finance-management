package com.felipemoreira.financemanagement.domain.exceptions;

import jakarta.persistence.EntityExistsException;

public class PersonAlreadyExistsException extends EntityExistsException {

    public PersonAlreadyExistsException(String usuario, String email) {
        super(String.format("Oops! Looks like the username %s or email %s is already in use.", usuario, email));
    }

    public PersonAlreadyExistsException(Long id) {
        super(String.format("Oops! It appears that the user with ID %s does not exist.", id));
    }
}
