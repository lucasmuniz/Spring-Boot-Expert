package io.github.lucasgm.rest.controller;

import io.github.lucasgm.domain.entity.Client;
import io.github.lucasgm.domain.repository.IClientsRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private IClientsRepository clientsRepository;

    public ClientController(IClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    @GetMapping("{id}")
    public Client getClientById(@PathVariable Integer id) {
        return clientsRepository
                .findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@RequestBody Client client) {
        return clientsRepository.save(client);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientsRepository.findById(id)
                .map(client -> {
                    clientsRepository.delete(client);
                    return client;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Client client) {
        clientsRepository.findById(id).map(clientExist -> {
                    client.setId(clientExist.getId());
                    clientsRepository.save(client);
                    return client;
                })
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Client> find(Client filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filter, matcher);
        return clientsRepository.findAll(example);
    }

}
