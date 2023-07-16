package com.felipemoreira.financemanagement.persons.domain.mappers;

import com.felipemoreira.financemanagement.persons.domain.dtos.PersonDTO;
import com.felipemoreira.financemanagement.persons.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person dtoToEntity(PersonDTO personDTO);

    PersonDTO entityToDTO(Person person);
}
