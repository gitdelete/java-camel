package com.aman.javacamel;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class JavaCamelStarter {

    public static void main(String[] args) {

        SpringApplication.run(JavaCamelStarter.class,args);
    }

}
