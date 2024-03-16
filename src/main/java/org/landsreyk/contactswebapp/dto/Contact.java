package org.landsreyk.contactswebapp.dto;

import lombok.Data;
import org.landsreyk.contactswebapp.util.ValidPhoneNumber;

import javax.validation.constraints.Email;


@Data
public class Contact {

    private Long id;

    private String firstName;

    private String lastName;

    @Email(message = "Please enter a valid email address")
    private String email;

    @ValidPhoneNumber
    private String phone;
}
