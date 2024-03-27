package it.nepstherti.msclients.service;

import it.nepstherti.msclients.dtos.response.ClientResponse;
import it.nepstherti.msclients.model.ClientModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClientService {
    void deleteClient(String nif);
    List<ClientModel> findAllClients();
    ClientResponse findClientById(Long clientId);
    ClientResponse findByNif(String nif);
    // ClientModel findClientById(Long clientId);
    @Transactional
    ClientModel saveClient(ClientModel clientModel);
    ClientModel updateClient(ClientModel clientModel);
    Page<ClientModel> paginatedAllClient(int page, int pageSize);
}
