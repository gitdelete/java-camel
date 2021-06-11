package com.aman.javacamel.routes;

import com.aman.javacamel.processors.FileProcessor;
import lombok.AllArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;


@AllArgsConstructor
public class FileRouter extends RouteBuilder {

    private  String sourceFolder;
    private String destFolder;

    @Override
    public void configure() throws Exception {
        from("file://" + sourceFolder).routeId("fileRoute").process(new FileProcessor())
                .to("file://" + destFolder);

    }
}
