package it.nepstherti.mscreditassessment.clients;

import it.nepstherti.mscreditassessment.domain.ClientCards;
import it.nepstherti.mscreditassessment.domain.CreditCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "ms-cards", path = "/cards")
public interface CreditCardResourceClient {
    @GetMapping(params = {"nif"})
     ResponseEntity<List<ClientCards>> getCreditCardsByClient(@RequestParam String nif);
    @GetMapping(params = {"income"})
     ResponseEntity<List<CreditCard>> getCreditCardsByIncome(@RequestParam Long income);


}
