package com.aman.javacamel.configurer;

import com.aman.javacamel.routes.FileRouter;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;

@Configuration
public class JavaCamelConfigurer {

    @Value("${source.folder.path}")
    private String sourceFolder;

    @Value("${destination.folder.path}")
    private String destFolder;

    @Bean
    public CamelContext getCamelContext(){
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.start();
        return
    }

    @Bean
    public FileRouter getFileRouter(){
        return new FileRouter(sourceFolder, destFolder);
    }
}
