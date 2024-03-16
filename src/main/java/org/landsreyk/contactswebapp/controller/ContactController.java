package org.landsreyk.contactswebapp.controller;

import lombok.RequiredArgsConstructor;
import org.landsreyk.contactswebapp.dto.Contact;
import org.landsreyk.contactswebapp.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
