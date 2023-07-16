package com.felipemoreira.financemanagement.services;

import com.felipemoreira.financemanagement.domain.dtos.MessageDTO;
import com.felipemoreira.financemanagement.domain.dtos.PersonDTO;
import com.felipemoreira.financemanagement.domain.exceptions.PersonAlreadyExistsException;
import com.felipemoreira.financemanagement.domain.mappers.PersonMapper;
import com.felipemoreira.financemanagement.entities.Person;
import com.felipemoreira.financemanagement.repositories.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Person encontraPerson = verifyAndGetIfExists(personDTO.getPersonID());

        personDTO.setPersonID(id);
        Person personToUpdate = PERSON_MAPPER.dtoToEntity(personDTO);

        personToUpdate.setCreatedBy(encontraPerson.getCreatedBy());
        personToUpdate.setCreatedDate(encontraPerson.getCreatedDate());

        Person updatedPerson = personRepository.save(personToUpdate);

        return updateMessage(updatedPerson);
    }

    public void deletePerson(Long id) {
        verifyAndGetIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyAndGetIfExists(Long id) {
         return personRepository.findById(id)
             .orElseThrow(() -> {
                 throw new PersonAlreadyExistsException(id);
             });
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
