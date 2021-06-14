package com.aman.javacamel.configurer;

import com.aman.javacamel.processors.ProcessFileContent;
import com.aman.javacamel.routes.FileRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.Ordered;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Comparator;

@Configuration
public class JavaCamelConfigurer {

    @Value("${source.folder.path}")
    private String sourceFolder;

    @Value("${destination.folder.path}")
    private String destFolder;

    @Value("${practice.folder.path}")
    private String practiceFolder;

    /*
    *  This is with camel-core dependency without using spring boot camel starter
    * */
    /*
    @Bean
    public CamelContext getCamelContext(){

        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(getFileRouter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        camelContext.start();
        return camelContext;
    }
    */

    @Bean
    public FileRouter getFileRouter(){
        return new FileRouter(practiceFolder, sourceFolder, destFolder);
    }

    @Bean
    public ProcessFileContent processFileContent(){
        return new ProcessFileContent();
    }
}
