package org.landsreyk.contactswebapp.service;

import lombok.RequiredArgsConstructor;
import org.landsreyk.contactswebapp.dto.Contact;
import org.landsreyk.contactswebapp.repository.ContactRepository;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<Contact> findAllContacts() {
        return contactRepository.findAllContacts();
    }

//    @EventListener(ApplicationStartedEvent.class)
    private void loadContacts() {
        for (int i = 1; i <= 10; i++) {
            Contact contact = new Contact();
            contact.setFirstName("first_name_" + i);
            contact.setLastName("last_name_" + i);
            contact.setEmail("email_%s@email.com".formatted(i));
            contact.setPhone("+799988877%s".formatted(i));

            contactRepository.saveContact(contact);
        }
    }
}
