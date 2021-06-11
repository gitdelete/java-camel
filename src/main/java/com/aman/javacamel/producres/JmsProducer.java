package com.aman.javacamel.producres;

import com.aman.javacamel.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsProducer {

    @Autowired
    JmsTemplate jmsQueueTemplate;

    @Autowired
    JmsTemplate jmsTopicTemplate;

    @Value("${active-mq.queue}")
    private String queue;

    @Value("${active-mq.topic}")
    private String topic;

    public void sendMessage(Employee message){
        try{
            jmsQueueTemplate.send(queue, m -> m.createObjectMessage(message));
            jmsTopicTemplate.send(topic, m -> m.createObjectMessage(message));
            log.info("Attempting Send message to Topic: "+ queue);
//            jmsTemplate.convertAndSend(queue, message);
        } catch(Exception e){
           log.error("Recieved Exception during send Message: ", e);
        }
    }
}