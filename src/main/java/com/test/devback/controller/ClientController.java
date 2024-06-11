package com.test.devback.controller;

import com.test.devback.exception.ClientNotFoundException;
import com.test.devback.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.devback.repository.ClientRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/clientes")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @PostMapping("/cliente")
    public Client createClients(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<Client> getClientId(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("El Cliente no existe: " + id));
        return ResponseEntity.ok(client);
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<Client> updateClients(@PathVariable Long id, @RequestBody Client clientDetail) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("El Cliente no existe: " + id));

        client.setClientName(clientDetail.getClientName());
        client.setNumber(clientDetail.getNumber());
        client.setEmail(clientDetail.getEmail());
        client.setStartDate(clientDetail.getStartDate());
        client.setEndDate(clientDetail.getEndDate());

        Client updateClient = clientRepository.save(client);
        return ResponseEntity.ok(updateClient);
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteClients(@PathVariable Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("El Cliente no existe: " + id));

        clientRepository.delete(client);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Cliente eliminado", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
