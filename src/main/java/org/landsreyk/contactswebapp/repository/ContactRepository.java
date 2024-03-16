package org.landsreyk.contactswebapp.repository;

import lombok.RequiredArgsConstructor;
import org.landsreyk.contactswebapp.dto.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ContactRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Contact> contactRowMapper = (rs, rowNum) -> {
        Contact contact = new Contact();
        contact.setId(rs.getLong("id"));
        contact.setFirstName(rs.getString("first_name"));
        contact.setLastName(rs.getString("last_name"));
        contact.setEmail(rs.getString("email"));
        contact.setPhone(rs.getString("phone"));

        return contact;
    };

    public List<Contact> findAllContacts() {
        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, contactRowMapper);
    }

    public int saveContact(Contact contact) {
        String sql = "INSERT INTO contact (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getEmail(), contact.getPhone());
    }
}
