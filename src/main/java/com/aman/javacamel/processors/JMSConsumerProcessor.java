package com.aman.javacamel.processors;

import com.aman.javacamel.domain.Employee;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JMSConsumerProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {

        Employee o = (Employee)exchange.getIn().getBody();
        System.out.println(o);

    }
}
