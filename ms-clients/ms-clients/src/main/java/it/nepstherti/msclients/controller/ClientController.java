package it.nepstherti.msclients.controller;

import it.nepstherti.msclients.dtos.request.ClientRequest;
import it.nepstherti.msclients.dtos.response.ClientResponse;
import it.nepstherti.msclients.model.ClientModel;
import it.nepstherti.msclients.service.IClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static it.nepstherti.msclients.util.UriUtil.addIdToCurrentUrlPath;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    final IClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll() {
        List<ClientModel> clients = clientService.findAllClients();
        return ResponseEntity.ok(clients.stream().map(ClientResponse::new).toList());
    }

    @GetMapping("/{nif}")
    public ResponseEntity<ClientResponse> findByNif(@PathVariable String nif) {
        ClientResponse client = clientService.findByNif(nif);
        return ResponseEntity.ok((client));
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody ClientRequest clientRequest) {
        ClientModel clientModel = clientService.saveClient(clientRequest.createClient());
        URI uri = addIdToCurrentUrlPath(clientModel.getNif());
        log.info("Client {} saved successfully",clientModel.getFullName());
        return ResponseEntity.created(uri).body("salvo com sucesso!");
    }

    @PutMapping
    public ResponseEntity<ClientModel> update( @Valid @RequestBody ClientRequest clientRequest) {
        var clientModel = new ClientModel();
        ClientRequest.updateClient(clientRequest,clientModel);
        return ResponseEntity.ok().body(clientService.updateClient(clientModel));
    }

    @GetMapping("/client/paginated")
    public ResponseEntity<Page<ClientModel>> clientPaginated(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.ASC )
                                                                 Pageable pageable){
        return ResponseEntity.ok(clientService.paginatedAllClient(pageable));
    }

    @DeleteMapping("/{nif}")
    public ResponseEntity<Void> delete(@PathVariable String nif) {
        clientService.deleteClient(nif);
        return ResponseEntity.noContent().build();

    }

}
