package org.landsreyk.contactswebapp.repository.impl;

import lombok.RequiredArgsConstructor;
import org.landsreyk.contactswebapp.dto.Contact;
import org.landsreyk.contactswebapp.repository.ContactRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DatabaseContactRepository implements ContactRepository {

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

    @Override
    public List<Contact> findAllContacts() {
        String sql = "SELECT * FROM contact";

        return jdbcTemplate.query(sql, contactRowMapper);
    }

    @Override
    public int saveContact(Contact contact) {
        String sql = "INSERT INTO contact (first_name, last_name, email, phone) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(
                sql,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone()
        );
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String sql = "SELECT * FROM contact WHERE id = ?";
        Contact contact = jdbcTemplate.queryForObject(sql, contactRowMapper, id);

        return Optional.ofNullable(contact);
    }

    @Override
    public int updateContact(Contact contact) {
        String sql = "UPDATE contact SET first_name = ?, last_name = ?, email = ?, phone = ? WHERE id = ?";
        return jdbcTemplate.update(
                sql,
                contact.getFirstName(),
                contact.getLastName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getId()
        );
    }

    @Override
    public int deleteContact(Long id) {
        String sql = "DELETE FROM contact WHERE id = ?";

        return jdbcTemplate.update(sql, id);
    }
}
