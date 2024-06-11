package com.test.devback;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import com.test.devback.controller.ClientController;
import com.test.devback.exception.ClientNotFoundException;
import com.test.devback.model.Client;
import com.test.devback.repository.ClientRepository;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientController clientController;

    @Test
    public void testGetClients() {
        LocalDate startDate1 = LocalDate.of(2024, 6, 1);
        LocalDate endDate1 = LocalDate.of(2024, 6, 10);
        LocalDate startDate2 = LocalDate.of(2024, 6, 5);
        LocalDate endDate2 = LocalDate.of(2024, 6, 15);
        Client client1 = new Client("Client1", "12345", "client1@example.com", startDate1, endDate1);
        Client client2 = new Client("Client2", "54321", "client2@example.com", startDate2, endDate2);
        List<Client> clients = Arrays.asList(client1, client2);
        when(clientRepository.findAll()).thenReturn(clients);

        List<Client> result = clientController.getClients();

        assertEquals(2, result.size());
        assertEquals(client1.getClientName(), result.get(0).getClientName());
        assertEquals(client2.getClientName(), result.get(1).getClientName());
    }

    @Test
    public void testGetClientById() {
        LocalDate startDate = LocalDate.of(2024, 6, 1);
        LocalDate endDate = LocalDate.of(2024, 6, 10);
        Client client = new Client("Client", "12345", "client@example.com", startDate, endDate);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ResponseEntity<Client> responseEntity = clientController.getClientId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(client.getClientName(), responseEntity.getBody().getClientName());
    }

    @Test
    public void testGetClientById_NotFound() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ClientNotFoundException.class, () -> clientController.getClientId(1L));
    }
}
