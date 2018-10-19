package com.sdproject.controllers;


import com.sdproject.entities.Client;
import com.sdproject.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/client/")
    public Collection<Client> clients() {
        return clientRepository.findAll();
    }

    @GetMapping(value = "/client/{id}")
    public ResponseEntity<Client> getCinema(@PathVariable("id") Integer cinemaId) {
        Client client = clientRepository.findOne(cinemaId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(client);
    }

    @DeleteMapping(value = "/client/delete/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Integer clientId) {
        Client client = clientRepository.findOne(clientId);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.delete(client);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/client/")
    public Client createClient(@Valid @RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping(value = "/client/{id}")
    public ResponseEntity<Client> updateCinema(@PathVariable("id") Integer cinemaId, @Valid @RequestBody Client client) {
        Client clientExist = clientRepository.findOne(cinemaId);

        if (clientExist == null) {
            return ResponseEntity.notFound().build();
        }

        clientExist.setName(client.getName());
        clientExist.setEmail(client.getEmail());
        clientExist.setPassword(client.getPassword());
        clientExist.setReservations(client.getReservations());

        Client updatedClient = clientRepository.save(clientExist);

        return ResponseEntity.ok(updatedClient);
    }

}
