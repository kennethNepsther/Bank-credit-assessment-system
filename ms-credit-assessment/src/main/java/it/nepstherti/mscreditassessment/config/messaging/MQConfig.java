package it.nepstherti.mscreditassessment.config.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    @Value("${mq.queues.emissao-cartoes}")
    private String cardIssueQueueName;
    @Bean
     Queue queueEmissaoCartoes() {
        return new Queue(cardIssueQueueName, true);
    }


}
