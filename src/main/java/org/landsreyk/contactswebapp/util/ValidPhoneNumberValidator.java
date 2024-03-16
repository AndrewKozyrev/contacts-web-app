package org.landsreyk.contactswebapp.util;


import com.google.i18n.phonenumbers.PhoneNumberUtil;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED;

@Slf4j
public class ValidPhoneNumberValidator implements ConstraintValidator<ValidPhoneNumber, String> {

    private PhoneNumberUtil phoneNumberUtil;

    @Override
    public void initialize(ValidPhoneNumber constraintAnnotation) {
        phoneNumberUtil = PhoneNumberUtil.getInstance();
    }

    @Override
    public boolean isValid(String phoneNumberString, ConstraintValidatorContext context) {
        if (phoneNumberString == null) {
            return false;
        }
        try {
            var phone = phoneNumberUtil.parse(phoneNumberString, UNSPECIFIED.name());
            return phoneNumberUtil.isValidNumber(phone);
        } catch (Exception e) {
            log.error(phoneNumberString, e);
            return false;
        }
    }
}