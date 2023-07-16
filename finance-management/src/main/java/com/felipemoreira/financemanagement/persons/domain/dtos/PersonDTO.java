package com.felipemoreira.financemanagement.persons.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long personID;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String personName;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    private String username;

    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String password;
}
