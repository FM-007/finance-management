package com.felipemoreira.financemanagement.repositories;

import com.felipemoreira.financemanagement.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

//    Optional<Person> findByUsername(String user);

    Optional<Person> findByEmailOrUsername(String email, String user);
}
