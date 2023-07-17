package com.felipemoreira.financemanagement.persons.controllers.docs;

import com.felipemoreira.financemanagement.persons.domain.dtos.MessageDTO;
import com.felipemoreira.financemanagement.persons.domain.dtos.PersonDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("System People management")
public interface PersonControllerDocs {

    @ApiOperation(value = "Person creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success person creation"),
            @ApiResponse(code = 400, message = "Missing required fileds, wrong field range value or person already registered on system")
    })
    MessageDTO createPerson(PersonDTO personDTO);

    @ApiOperation(value = "Person update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success person update"),
            @ApiResponse(code = 404, message = "Person with informed ID not found in the system")
    })
    MessageDTO updatePerson(Long id, PersonDTO personDTO);

    @ApiOperation(value = "Person delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success person delete"),
            @ApiResponse(code = 404, message = "Person with informed ID not found in the system")
    })
    void deletePerson(Long id);
}
