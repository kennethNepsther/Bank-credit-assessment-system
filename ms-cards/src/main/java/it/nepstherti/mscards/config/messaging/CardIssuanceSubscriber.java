package it.nepstherti.mscards.config.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.nepstherti.mscards.model.CardRequestDetails;
import it.nepstherti.mscards.model.ClientCardModel;
import it.nepstherti.mscards.repository.IClientCardRepository;
import it.nepstherti.mscards.repository.ICreditCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {
    final ICreditCardRepository creditCardRepository;
    final IClientCardRepository clientCardRepository;
    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveIssuanceRequest(@Payload String payload) {
        try {
       var mapper = new ObjectMapper();
        CardRequestDetails data = mapper.readValue(payload, CardRequestDetails.class);
           var card = creditCardRepository.findById(data.getIdCard()).orElseThrow();

           var clientCard = new ClientCardModel();
            clientCard.setCreditCard(card);
            clientCard.setNif(data.getNif());
            clientCard.setBaselineApproved(data.getBaselineApproved());

            clientCardRepository.save(clientCard);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
