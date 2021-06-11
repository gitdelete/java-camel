package com.aman.javacamel.controller;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;


import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/javacamel")
public class CamelRouteController{

    @Autowired
    private CamelContext camelContext;

    @RequestMapping(value = "/start")
    public void startRoute() throws Exception {
       SpringCamelContext springCamelContext= camelContext instanceof SpringCamelContext ? (SpringCamelContext)camelContext: null;
       System.out.println(springCamelContext);
       springCamelContext.startRoute("fileRoute");
    }

    @RequestMapping(value = "/stop")
    public void stopRoute() throws Exception {
        SpringCamelContext springCamelContext= camelContext instanceof SpringCamelContext ? (SpringCamelContext)camelContext: null;
        System.out.println(springCamelContext);
        springCamelContext.stopRoute("fileRoute");
    }

}
