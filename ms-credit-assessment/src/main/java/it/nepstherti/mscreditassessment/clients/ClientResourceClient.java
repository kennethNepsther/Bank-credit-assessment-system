package it.nepstherti.mscreditassessment.clients;

import it.nepstherti.mscreditassessment.domain.ClientData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "ms-clients", path = "/clients")
public interface ClientResourceClient {
    @GetMapping("/{nif}")
     ResponseEntity<ClientData> clientDetails(@PathVariable String nif);
}
