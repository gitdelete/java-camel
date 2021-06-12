package com.aman.javacamel.producres;

import com.aman.javacamel.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

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
            MessageCreator mc = new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    Message m =  session.createObjectMessage(message);
                    m.setJMSCorrelationID(message.getEmpId());
                    return m;
                }
            };

            jmsQueueTemplate.send(queue, mc);
            jmsTopicTemplate.send(topic, mc);
            log.info("Attempting Send message to Topic: "+ queue);
//            jmsTemplate.convertAndSend(queue, message);
        } catch(Exception e){
           log.error("Recieved Exception during send Message: ", e);
        }
    }
}