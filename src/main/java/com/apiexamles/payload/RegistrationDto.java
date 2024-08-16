package com.apiexamles.payload;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegistrationDto {

    private Long id;

    @Size(min=2, message = "Should be more than 2 characters")
    private String name;

    private String email;

    private String mobile;

}