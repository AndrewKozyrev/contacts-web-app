package org.landsreyk.contactswebapp.util;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import lombok.extern.slf4j.Slf4j;

import static com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED;

@Slf4j
public class PhoneUtils {

    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

     public static boolean isValidPhoneNumber(String phoneNumberString) {
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
