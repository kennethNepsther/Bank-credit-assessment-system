package it.nepstherti.msclients.serviceimpl;

import it.nepstherti.msclients.dtos.response.ClientResponse;
import it.nepstherti.msclients.exceptions.exception.ObjectNotFoundException;
import it.nepstherti.msclients.model.ClientModel;
import it.nepstherti.msclients.repository.IClientRepository;
import it.nepstherti.msclients.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService {
    final IClientRepository clientRepository;
    @Override
    public void deleteClient(String nif) {
        findByNif(nif);
        clientRepository.deleteByNif(nif);

    }

    @Override
    public List<ClientModel> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public ClientResponse findClientById(Long clientId) {
        ClientModel client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ObjectNotFoundException("Não foi encontrada nenhuma  informação de cliente com este NIF"));
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setFullName(client.getFullName());
        clientResponse.setAge(calculateAge(client.getBirthDate()));
        return clientResponse;
    }

    @Override
    public ClientResponse findByNif(String nif) {
        ClientModel client = clientRepository.findClientModelByNif(nif)
                .orElseThrow( () -> new ObjectNotFoundException(
                        "Não foi encontrada nenhuma  informação de cliente com este NIF "+ nif));
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setNif(client.getNif());
        clientResponse.setFullName(client.getFullName());
        clientResponse.setAge(calculateAge(client.getBirthDate()));
        return clientResponse;
    }

    @Override
    public ClientModel saveClient(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    @Override
    public ClientModel updateClient(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    @Override
    public Page<ClientModel> paginatedAllClient(int page, int pageSize) {
        return clientRepository.findAll(PageRequest.of(page, pageSize));
    }

    public  int calculateAge(LocalDate birthDate) {
        LocalDate dataAtual = LocalDate.now();
        Period age = Period.between(birthDate, dataAtual);
        return age.getYears();


    }
}
