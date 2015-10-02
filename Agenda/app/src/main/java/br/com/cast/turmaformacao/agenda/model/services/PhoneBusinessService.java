package br.com.cast.turmaformacao.agenda.model.services;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;
import br.com.cast.turmaformacao.agenda.model.entities.Phone;
import br.com.cast.turmaformacao.agenda.model.persistence.ContactRepository;
import br.com.cast.turmaformacao.agenda.model.persistence.PhoneRepository;

public final class PhoneBusinessService {
    private PhoneBusinessService() {
        super();
    }

    public static List<Phone> findAll() {
        return PhoneRepository.getAll();
    }

    public static void save(Phone phone) {
        PhoneRepository.save(phone);
    }

    public static void delete(Contact contact){
        PhoneRepository.delete(contact.getId());
    }
}
