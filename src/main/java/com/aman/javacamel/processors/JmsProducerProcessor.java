package com.aman.javacamel.processors;

import com.aman.javacamel.domain.Employee;
import com.google.gson.Gson;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class JmsProducerProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
       Gson g = new Gson();
//        Employee s = g.fromJson(exchange.getIn().getBody().toString(), Employee.class);
        String s= (String)exchange.getIn().getBody();
        Employee e = g.fromJson(s, Employee.class);
        exchange.getIn().setBody(e);
        if(e.getEmpName().equals("Aman"))
            exchange.getIn().setHeader("sendToQueue", true);
    }
}
