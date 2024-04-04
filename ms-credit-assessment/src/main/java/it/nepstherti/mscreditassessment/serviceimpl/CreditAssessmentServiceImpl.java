package it.nepstherti.mscreditassessment.serviceimpl;

import it.nepstherti.mscreditassessment.clients.ClientResourceClient;
import it.nepstherti.mscreditassessment.clients.CreditCardResourceClient;
import it.nepstherti.mscreditassessment.domain.ClientCards;
import it.nepstherti.mscreditassessment.domain.ClientData;
import it.nepstherti.mscreditassessment.domain.ClientStatus;
import it.nepstherti.mscreditassessment.service.CreditAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAssessmentServiceImpl implements CreditAssessmentService {

    private final ClientResourceClient clientResourceClient;
    private final CreditCardResourceClient cardResourceClient;

    @Override
    public ClientStatus getClientStatus(String nif) {
        // get client card information
        ResponseEntity<List<ClientCards>> cardDataResponse = cardResourceClient.getCreditCardsByClient(nif);
        // get client details
        ResponseEntity<ClientData> clientDataResponse = clientResourceClient.clientDetails(nif);
        return ClientStatus
                .builder()
                .clientdata(clientDataResponse.getBody())
                .cardsList(cardDataResponse.getBody())
                .build();
    }
}
