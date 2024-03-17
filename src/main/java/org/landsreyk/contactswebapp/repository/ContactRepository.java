package org.landsreyk.contactswebapp.repository;

import org.landsreyk.contactswebapp.dto.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    List<Contact> findAllContacts();

    int saveContact(Contact contact);

    Optional<Contact> findById(Long id);

    int updateContact(Contact contact);

    int deleteContact(Long id);
}
