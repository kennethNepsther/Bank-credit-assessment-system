package it.nepstherti.msclients.serviceimpl;

import it.nepstherti.msclients.dtos.response.ClientResponse;
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
    /*
       @Override
       public ClientModel findById(Long clientId) {

           *Optional<ClientModel> client = clientRepository.findById(clientId);
           return client.orElseThrow(() -> new ObjectNotFoundException("Não foi definido um orçamento com este id "));

           return clientRepository.findById(clientId).orElseThrow(null);
       }*/
    @Override
    public ClientResponse findClientById(Long clientId) {
        ClientModel client = clientRepository.findById(clientId).orElseThrow(null);
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setFullName(client.getFullName());
        clientResponse.setAge(calculateAge(client.getBirthDate()));
        return clientResponse;
    }

    @Override
    public ClientResponse findByNif(String nif) {
        ClientModel client = clientRepository.findClientModelByNif(nif).orElseThrow(null);
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
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
