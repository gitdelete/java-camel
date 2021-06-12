package com.aman.javacamel.controller;

import com.aman.javacamel.domain.Employee;
import com.aman.javacamel.producres.JmsProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQSslConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.BrowserCallback;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.jms.*;
import java.util.List;

@RestController
@Slf4j
public class MessageController {

    @Value("${active-mq.queue}")
    private String queue;

    @Autowired
    JmsTemplate jmsQueueTemplate;

    @Autowired
    JmsProducer jmsProducer;

    @Autowired
    ConnectionFactory  activeMQConnectionFactory;

    @PostMapping(value="/employee")
    public Employee sendMessage(@RequestBody Employee employee){
        jmsProducer.sendMessage(employee);
        return employee;
    }

    @GetMapping(value="/getPendingMessages" )
    public void getAllMessages()  {

        List<Message>  mlist =jmsQueueTemplate.browse(queue, new BrowserCallback<List<Message>>() {

            @Override
            public List<Message> doInJms(Session session, QueueBrowser browser) throws JMSException {

                List<Message> list = new ArrayList<>();
                Enumeration messages = browser.getEnumeration();
                int total = 0;
                while (messages.hasMoreElements()) {
                    ObjectMessage m = (ObjectMessage)messages.nextElement();
                    System.out.println((Employee)m.getObject());
                    list.add(m);
                }
                return list;
            }
        });
        System.out.println(mlist.size());
    }

    @GetMapping(value="/getSelectedPendingMessages" )
    public void getSelectedMessages(@RequestParam String id)  {
        String messageSelector="JMSCorrelationID='"+id+"'";

        List<Message>  mlist =jmsQueueTemplate.browseSelected(queue,messageSelector, new BrowserCallback<List<Message>>() {

            @Override
            public List<Message> doInJms(Session session, QueueBrowser browser) throws JMSException {

                List<Message> list = new ArrayList<>();
                Enumeration messages = browser.getEnumeration();
                int total = 0;
                while (messages.hasMoreElements()) {
                    ObjectMessage m = (ObjectMessage)messages.nextElement();
                    System.out.println((Employee)m.getObject());
                    list.add(m);
                }
                return list;
            }
        });
        System.out.println(mlist.size());
    }
}