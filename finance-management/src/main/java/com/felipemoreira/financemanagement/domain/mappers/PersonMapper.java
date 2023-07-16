package com.felipemoreira.financemanagement.domain.mappers;

import com.felipemoreira.financemanagement.domain.dtos.PersonDTO;
import com.felipemoreira.financemanagement.entities.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

//    @Mapping(target = "lastModifiedDate", ignore = true)
//    @Mapping(target = "lastModifiedBy", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
    Person dtoToEntity(PersonDTO personDTO);

    PersonDTO entityToDTO(Person person);
}
