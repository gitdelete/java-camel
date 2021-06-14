package com.aman.javacamel.routes;

import com.aman.javacamel.processors.ContentProcessor;
import com.aman.javacamel.processors.FileProcessor;
import com.aman.javacamel.processors.MyFileSorter;
import com.aman.javacamel.processors.ProcessFileContent;
import lombok.AllArgsConstructor;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;



/*
* File Endpoint:
* https://camel.apache.org/components/3.4.x/file-component.html
* */
@AllArgsConstructor
public class FileRouter extends RouteBuilder {

    private String practiceFolder;
    private  String sourceFolder;
    private String destFolder;

    @Override
    public void configure() throws Exception {
        from("file://" + sourceFolder+"").routeId("fileRoute").process(new FileProcessor())
                .to("file://" + destFolder)
                .onException(IOException.class)
                .handled(true)
                .log("IOException occurred due: ${exception.message}")
                .transform().simple("Error ${exception.message}").to("mock:error");

//        from("file://" + sourceFolder+"?recursive=true&idempotent=true&amp;idempotentKey=${file:name}-${file:size}").routeId("fileRoute").process(new FileProcessor())
//                .to("file://" + destFolder+"?sortBy=reverse:file:name").onException(IOException.class)
//                .handled(true)
//                .log("IOException occurred due: ${exception.message}")
//                .transform().simple("Error ${exception.message}").to("mock:error");

//        from("file://"+practiceFolder)
//                .sort(body().tokenize("\n"), new MyFileSorter()).process(new FileProcessor())
//                .bean(ProcessFileContent.class, "processLine");

//        from("file://"+destFolder+"?sorter=#mySorter")
//                .sort(body().tokenize("\n"))
//                .to("bean:ProcessFileContent.processLine");

        //.convertBodyTo(String.class)

        from("file://"+practiceFolder+"?noop=true&idempotentKey=${file:name}-${file:modified}")     // no delete from source folder
         .convertBodyTo(String.class).process(new ContentProcessor()).to("file://" + destFolder);
    }
}
