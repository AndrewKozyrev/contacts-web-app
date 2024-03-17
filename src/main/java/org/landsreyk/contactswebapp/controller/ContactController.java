package org.landsreyk.contactswebapp.controller;

import lombok.RequiredArgsConstructor;
import org.landsreyk.contactswebapp.dto.Contact;
import org.landsreyk.contactswebapp.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contacts")
    public String getContacts(Model model) {
        List<Contact> contacts = contactService.findAllContacts();
        model.addAttribute("contacts", contacts);
        return "index";
    }

    @GetMapping("/delete-contact/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
        return "redirect:/contacts";
    }

    @GetMapping("/edit-contact/{id}")
    public String editContact(@PathVariable Long id, Model model) {
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "contact";
    }

    @PostMapping("/update-contact")
    public String updateContact(@ModelAttribute Contact contact) {
        contactService.updateContact(contact);
        return "redirect:/contacts";
    }
}
