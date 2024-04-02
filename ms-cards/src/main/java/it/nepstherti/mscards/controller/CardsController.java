package it.nepstherti.mscards.controller;

import it.nepstherti.mscards.dto.ClientCardResponse;
import it.nepstherti.mscards.dto.CreditCardSaveRequest;
import it.nepstherti.mscards.model.ClientCardModel;
import it.nepstherti.mscards.model.CreditCardModel;
import it.nepstherti.mscards.service.IClientCardService;
import it.nepstherti.mscards.service.ICreditCardService;
import it.nepstherti.mscards.serviceimpl.ClientCardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/cards")
public class CardsController {

    final ICreditCardService ICreditCardService;
    final IClientCardService clientCardService;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreditCardSaveRequest request) {
        CreditCardModel creditCard = ICreditCardService.saveCreditCard(request.saveCard());
        log.info("Card {} saved successfully",creditCard.getCardName());
        return ResponseEntity.status(HttpStatus.CREATED).body("salvo com sucesso!");
    }


    @GetMapping
    //@GetMapping(params = {"income"})
    public ResponseEntity<List<CreditCardModel>> getCreditCardsByIncome(@RequestParam("income") Long income) {
        List<CreditCardModel> cardList = ICreditCardService.getCreditCardsIncomeLessThanEquals(income);
        return ResponseEntity.ok(cardList);
    }
    @GetMapping(params = {"nif"})
    public ResponseEntity<List<ClientCardResponse>> getCreditCardsByClient(@RequestParam String nif){
        List<ClientCardModel> clientCards = clientCardService.listCreditCardsByNif(nif);
        return ResponseEntity.ok(clientCards.stream().map(ClientCardResponse::new).toList());


    }

}
