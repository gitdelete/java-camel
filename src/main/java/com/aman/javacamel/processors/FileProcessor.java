package com.aman.javacamel.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


import javax.lang.model.element.TypeElement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class FileProcessor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {

        String originalFileName = exchange.getIn().getHeader(Exchange.FILE_NAME, String.class);
        System.out.println(originalFileName);
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH-mm-ss");
        String changedFileName = dateFormat.format(date) + originalFileName;
        exchange.getIn().setHeader(Exchange.FILE_NAME, changedFileName);

    }
}
