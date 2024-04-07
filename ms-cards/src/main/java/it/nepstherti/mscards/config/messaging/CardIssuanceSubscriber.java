package it.nepstherti.mscards.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class CardIssuanceSubscriber {
    @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
    public void receiveIssuanceRequest(@Payload String payload) {
        System.out.println(payload);
    }
}
