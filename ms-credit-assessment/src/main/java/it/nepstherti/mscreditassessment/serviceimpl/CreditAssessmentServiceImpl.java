package it.nepstherti.mscreditassessment.serviceimpl;

import feign.FeignException;
import it.nepstherti.mscreditassessment.clients.ClientResourceClient;
import it.nepstherti.mscreditassessment.clients.CreditCardResourceClient;
import it.nepstherti.mscreditassessment.config.messaging.CardRequestIssuancePublisher;
import it.nepstherti.mscreditassessment.domain.*;
import it.nepstherti.mscreditassessment.exception.IssueCardException;
import it.nepstherti.mscreditassessment.exception.MicroserviceErrorConnectionException;
import it.nepstherti.mscreditassessment.exception.ObjectNotFoundException;
import it.nepstherti.mscreditassessment.service.CreditAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAssessmentServiceImpl implements CreditAssessmentService {

    private final ClientResourceClient clientResourceClient;
    private final CreditCardResourceClient cardResourceClient;
    private final CardRequestIssuancePublisher cardRequestIssuancePublisher;

    @Override
    public ClientStatus getClientStatus(String nif) throws MicroserviceErrorConnectionException {


        try {
            // get client card information
            ResponseEntity<List<ClientCards>> cardDataResponse = cardResourceClient.getCreditCardsByClient(nif);
            // get client details
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.clientDetails(nif);
            return ClientStatus
                    .builder()
                    .clientdata(clientDataResponse.getBody())
                    .cardsList(cardDataResponse.getBody())
                    .build();


        }catch(feign.FeignException e){

            if (e.getMessage().contains("404")){
                throw new ObjectNotFoundException("Não foi encontrada nenhuma  informação de cliente com este NIF");
            }
            //throw new MicroserviceErrorConnectionException(e.getMessage());
            throw new MicroserviceErrorConnectionException("Microservice de clientes indisponível ");

        }

    }

    @Override
    public ClientEvaluationResponse getClientEvaluation(String nif, Long income) {
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.clientDetails(nif);
            ResponseEntity<List<CreditCard>> cardResponse = cardResourceClient.getCreditCardsByIncome(income);

            List<CreditCard> cards = cardResponse.getBody();

            var approvedCards = cards.stream().map(card -> {

                var clientData =  clientDataResponse.getBody();

                BigDecimal baseline = card.getBaseline();
               // BigDecimal incomeBD = BigDecimal.valueOf(income);
                BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());

                var factor = ageBD.divide(BigDecimal.valueOf(10));
                BigDecimal baselineApproved = factor.multiply(baseline);

                var approvedCreditCard = new ApprovedCreditCard();
                approvedCreditCard.setCardName(card.getCardName());
                approvedCreditCard.setCardType(card.getCardType());
                approvedCreditCard.setApprovedBaseline(baselineApproved);

                return approvedCreditCard;
            }).toList();

            return new  ClientEvaluationResponse(approvedCards);



        }catch(FeignException e){

            if (e.getMessage().contains("404")){
                throw new ObjectNotFoundException("Não foi encontrada nenhuma  informação de cliente com este NIF");
            }

            throw new MicroserviceErrorConnectionException("Microservice de clientes indisponível ");

        }

    }

    public  IssueProtocolCard cardRequestIssuance(CardRequestDetails details){
        try{
            cardRequestIssuancePublisher.requestIssuance(details);
            var protocol = UUID.randomUUID().toString();
            return new IssueProtocolCard(protocol);
        }catch (Exception e){
            throw  new IssueCardException(e.getMessage());

        }
    }

}
