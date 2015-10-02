package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Email;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.EmailRepository;


public final class EmailBusinessService {
    private EmailBusinessService() {
        super();
    }

    public static List<Email> findAll() {
        return EmailRepository.getAll();
    }

    public static void save(Email email) {
        EmailRepository.save(email);
    }

    public static void delete(Contact contact){
        EmailRepository.delete(contact.getId());
    }
}
