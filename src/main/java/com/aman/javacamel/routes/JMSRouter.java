package com.aman.javacamel.routes;

import com.aman.javacamel.processors.JMSConsumerProcessor;
import com.aman.javacamel.processors.JmsProducerProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JMSRouter extends RouteBuilder {

    @Value("${practice.folder.path}")
    private  String practiceFolder;

    @Value("${destination.folder.path}")
    private String destFolder;

    @Override
    public void configure() throws Exception {
        from("file://" + practiceFolder + "?noop=true&filename=employee.json&idempotentKey=${file:name}-${file:modified}")
                .routeId("jmsProducerRoute")
                .convertBodyTo(String.class)
                .process(new JmsProducerProcessor())
                .choice()
                .when(header("sendToQueue").isEqualTo(true))
                .to("jms:queue:aman.javacamel.practice.queue")
                .otherwise()
                .log("In Otherwise !!!!");


        from("jms:queue:aman.javacamel.practice.queue")
                .routeId("jmsConsumerRoute")
                //.convertBodyTo(String.class)
                .process(new JMSConsumerProcessor()).
                .log("JMS testing done !!!!");

    }
}
