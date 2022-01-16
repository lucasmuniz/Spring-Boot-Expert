package io.github.lucasgm.service;

import io.github.lucasgm.model.Client;
import io.github.lucasgm.repository.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientsRepository repository;

    @Autowired
    public ClientService(ClientsRepository repository) {
        this.repository = repository;
    }

    public void save(Client client) {
        validateClient(client);
        this.repository.persist(client);
    }

    public void validateClient(Client client) {
        //aplica validações antes de salvar
    }
}
