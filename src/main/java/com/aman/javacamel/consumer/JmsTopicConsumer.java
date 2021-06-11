package com.aman.javacamel.consumer;

import com.aman.javacamel.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@Component
@Slf4j
public class JmsTopicConsumer implements MessageListener {

    @Value("${active-mq.topic}")
    private String topic;

    @Override
    @JmsListener(destination = "${active-mq.topic}", containerFactory = "jmsTopicListenerContainerFactory")
    public void onMessage(Message message) {
        try{
            System.out.println("heloooo Topic");
            ObjectMessage objectMessage = (ObjectMessage)message;
            Employee employee = (Employee)objectMessage.getObject();
            //do additional processing
           log.info("Received Message: "+ employee.toString());
        } catch(Exception e) {
          log.error("Received Exception : "+ e);
        }

    }
}