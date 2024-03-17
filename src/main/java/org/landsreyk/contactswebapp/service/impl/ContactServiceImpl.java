package org.landsreyk.contactswebapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.landsreyk.contactswebapp.dto.Contact;
import org.landsreyk.contactswebapp.repository.ContactRepository;
import org.landsreyk.contactswebapp.service.ContactService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Value("${app.init-contacts-enabled}")
    private boolean initContactsEnabled;

    @Value("${app.init-contacts-count}")
    private int initContactsCount;

    @Override
    public List<Contact> findAllContacts() {
        return contactRepository.findAllContacts();
    }

    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Contact with id=%s not found.".formatted(id)));
    }

    @Override
    public boolean saveContact(Contact contact) {
        int rowsAffected = contactRepository.saveContact(contact);

        return rowsAffected > 0;
    }

    @Override
    public boolean updateContact(Contact contact) {
        int rowsAffected = contactRepository.updateContact(contact);

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteContact(Long id) {
        int rowsAffected = contactRepository.deleteContact(id);

        return rowsAffected > 0;
    }

    @EventListener(ApplicationStartedEvent.class)
    private void loadContacts() {
        if (initContactsEnabled) {
            for (int i = 1; i <= initContactsCount; i++) {
                Contact contact = new Contact();
                contact.setFirstName("first_name_" + i);
                contact.setLastName("last_name_" + i);
                contact.setEmail("email_%s@email.com".formatted(i));
                contact.setPhone("+7999888" + StringUtils.leftPad(i + "", 4, '0'));

                contactRepository.saveContact(contact);
            }
        }
    }
}
