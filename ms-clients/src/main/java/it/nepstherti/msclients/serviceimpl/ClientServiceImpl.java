package it.nepstherti.msclients.serviceimpl;

import it.nepstherti.msclients.dtos.response.ClientResponse;
import it.nepstherti.msclients.exceptions.exception.ObjectNotFoundException;
import it.nepstherti.msclients.model.ClientModel;
import it.nepstherti.msclients.repository.IClientRepository;
import it.nepstherti.msclients.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ClientResponse findByNif(String nif) {
        return clientRepository.findClientModelByNif(nif)
                .map(client -> {
                    ClientResponse clientResponse = new ClientResponse();
                    clientResponse.setId(client.getId());
                    clientResponse.setNif(client.getNif());
                    clientResponse.setFullName(client.getFullName());
                    clientResponse.setAge(calculateAge(client.getBirthDate()));
                    return clientResponse;
                })
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Não foi encontrada nenhuma informação de cliente com este NIF " + nif));

        /*
         * ClientModel client = clientRepository.findClientModelByNif(nif)
                .orElseThrow( () -> new ObjectNotFoundException(
                        "Não foi encontrada nenhuma  informação de cliente com este NIF "+ nif));
        ClientResponse clientResponse = new ClientResponse();
        clientResponse.setId(client.getId());
        clientResponse.setNif(client.getNif());
        clientResponse.setFullName(client.getFullName());
        clientResponse.setAge(calculateAge(client.getBirthDate()));
        return clientResponse;*/

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
    public Page<ClientModel> paginatedAllClient(Pageable pageable) {
        //Sort sort = Sort.by(pageable.getSort().getOrderFor("id") != null ? pageable.getSort().getOrderFor("id").getDirection() : Sort.Direction.ASC, "id");
        return clientRepository.findAll(pageable);
    }

    public  int calculateAge(LocalDate birthDate) {
        LocalDate dataAtual = LocalDate.now();
        Period age = Period.between(birthDate, dataAtual);
        return age.getYears();


    }
}
