package org.landsreyk.contactswebapp.dto;

import lombok.Data;
import org.landsreyk.contactswebapp.util.PhoneUtils;

import javax.validation.constraints.Email;


@Data
public class Contact {

    private Long id;

    private String firstName;

    private String lastName;

    @Email(message = "Please enter a valid email address")
    private String email;

    private String phone;

    public void setPhone(String phone) {
        if (!PhoneUtils.isValidPhoneNumber(phone)) {
            throw new IllegalArgumentException("%s is not a valid phone number!".formatted(phone));
        }
        this.phone = phone;
    }
}
