package com.aman.javacamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ContentProcessor implements Processor {


    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("ContentProcessor");
    }
}
