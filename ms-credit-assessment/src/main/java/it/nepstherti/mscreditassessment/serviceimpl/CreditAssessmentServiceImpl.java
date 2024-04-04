package it.nepstherti.mscreditassessment.serviceimpl;

import it.nepstherti.mscreditassessment.clients.ClientResourceClient;
import it.nepstherti.mscreditassessment.domain.ClientData;
import it.nepstherti.mscreditassessment.domain.ClientStatus;
import it.nepstherti.mscreditassessment.service.CreditAssessmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditAssessmentServiceImpl implements CreditAssessmentService {

    private final ClientResourceClient clientResourceClient;

    @Override
    public ClientStatus getClientStatus(String nif) {

        // get client details
        // get client card information
        ResponseEntity<ClientData> clientDataResponse = clientResourceClient.clientDetails(nif);
        return ClientStatus
                .builder()
                .clientdata(clientDataResponse.getBody())
                .build();
    }
}
