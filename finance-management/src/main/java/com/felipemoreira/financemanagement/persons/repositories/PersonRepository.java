package com.felipemoreira.financemanagement.persons.repositories;

import com.felipemoreira.financemanagement.persons.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByPersonName(String name);

    Optional<Person> findByEmailOrUsername(String email, String user);
}
