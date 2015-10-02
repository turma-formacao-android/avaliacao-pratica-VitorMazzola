package br.com.cast.turmaformacao.agenda.controllers.asyncTask;

import br.com.cast.turmaformacao.agenda.model.entities.Address;

public interface AddressInterfaceTask {

    void afterSearchZipCode(Address address);
}