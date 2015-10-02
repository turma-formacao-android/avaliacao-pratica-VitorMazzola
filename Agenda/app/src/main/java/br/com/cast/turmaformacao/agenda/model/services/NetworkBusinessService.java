package br.com.cast.turmaformacao.agenda.model.services;

import android.net.Network;

import java.util.List;

import br.com.cast.turmaformacao.agenda.model.entities.Contact;

import br.com.cast.turmaformacao.agenda.model.entities.SocialNetwork;
import br.com.cast.turmaformacao.agenda.model.persistence.NetworkRepository;


public final class NetworkBusinessService {
    private NetworkBusinessService() {
        super();
    }

    public static List<SocialNetwork> findAll() {
        return NetworkRepository.getAll();
    }

    public static void save(SocialNetwork network) {
        NetworkRepository.save(network);
    }

    public static void delete(Contact contact){
        NetworkRepository.delete(contact.getId());
    }
}
