package org.landsreyk.contactswebapp.service;

import org.landsreyk.contactswebapp.dto.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAllContacts();

    Contact findById(Long id);

    boolean saveContact(Contact contact);

    boolean updateContact(Contact contact);

    boolean deleteContact(Long id);
}
