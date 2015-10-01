package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;

public final class ContactBusinessService {
    private ContactBusinessService() {
        super();
    }

    public static List<Contact> findAll() {
        return ContactRepository.getAll();
    }

    public static void save(Contact contact) {
        ContactRepository.save(contact);
    }

    public static void delete(Contact contact){
        ContactRepository.delete(contact.getId());
    }
}
