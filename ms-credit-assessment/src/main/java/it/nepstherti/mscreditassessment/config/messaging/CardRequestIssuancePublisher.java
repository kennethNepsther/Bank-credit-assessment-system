package it.nepstherti.mscreditassessment.config.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.nepstherti.mscreditassessment.domain.CardRequestDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardRequestIssuancePublisher {

     final RabbitTemplate rabbitTemplate;
     final Queue queueEmissaoCartoes;



    public void requestIssuance(CardRequestDetails cardRequestDetails) throws JsonProcessingException {
        var json = convertIntoJson(cardRequestDetails);
        rabbitTemplate.convertAndSend(queueEmissaoCartoes.getActualName(), json);
    }

    private String convertIntoJson(CardRequestDetails cardRequestDetails) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(cardRequestDetails);
    }
}
