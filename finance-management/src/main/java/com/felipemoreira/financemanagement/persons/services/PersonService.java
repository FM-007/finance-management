package com.felipemoreira.financemanagement.persons.services;

import com.felipemoreira.financemanagement.persons.domain.dtos.MessageDTO;
import com.felipemoreira.financemanagement.persons.domain.dtos.PersonDTO;
import com.felipemoreira.financemanagement.persons.domain.exceptions.PersonAlreadyExistsException;
import com.felipemoreira.financemanagement.persons.domain.exceptions.PersonNotFoundException;
import com.felipemoreira.financemanagement.persons.domain.mappers.PersonMapper;
import com.felipemoreira.financemanagement.persons.entities.Person;
import com.felipemoreira.financemanagement.persons.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

    private final static PersonMapper PERSON_MAPPER = PersonMapper.INSTANCE;
    private PersonRepository personRepository;

    public MessageDTO createdPerson(PersonDTO personDTO) {
        verifyIfExists(personDTO);
        Person person = PERSON_MAPPER.dtoToEntity(personDTO);
        Person createPerson = personRepository.save(person);

        return createMessage(createPerson);
    }

    public MessageDTO updatePerson(Long id, PersonDTO personDTO) {
        Person encontraPerson = verifyAndGetIfExists(id);

        personDTO.setPersonID(id);
        Person personToUpdate = PERSON_MAPPER.dtoToEntity(personDTO);

        personToUpdate.setCreatedBy(encontraPerson.getCreatedBy());
        personToUpdate.setCreatedDate(encontraPerson.getCreatedDate());

        Person updatedPerson = personRepository.save(personToUpdate);

        return updateMessage(updatedPerson);
    }

    public PersonDTO findByName(String namePerson) {
        Person person = personRepository.findByPersonName(namePerson)
                .orElseThrow(() -> new PersonNotFoundException(namePerson));

        return PERSON_MAPPER.entityToDTO(person);
    }

    public List<PersonDTO> findAll() {
        return personRepository.findAll()
                .stream()
                .map(PERSON_MAPPER::entityToDTO)
                .collect(Collectors.toList());
    }

    public void deletePerson(Long id) {
        verifyAndGetIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyAndGetIfExists(Long id) {
         return personRepository.findById(id)
             .orElseThrow(() -> new PersonAlreadyExistsException(id));
    }

    private void verifyIfExists(PersonDTO personDTO) {
        String email = personDTO.getEmail();
        String usuario = personDTO.getUsername();

        personRepository.findByEmailOrUsername(email, usuario)
            .ifPresent(person -> {
                throw new PersonAlreadyExistsException(personDTO.getUsername(), personDTO.getEmail());
            });
    }

    private MessageDTO createMessage(Person criarPerson) {
        return returnMessage("created", criarPerson);
    }

    private MessageDTO updateMessage(Person updatePerson) {
        return returnMessage("updated", updatePerson);
    }

    private MessageDTO returnMessage(String action, Person person) {
        Long pessoaID = person.getPersonID();
        String usuario = person.getUsername();

        return MessageDTO.builder()
                .menssagem(String.format("User %s with ID %s successfully %s", usuario, pessoaID, action))
                .build();
    }
}
