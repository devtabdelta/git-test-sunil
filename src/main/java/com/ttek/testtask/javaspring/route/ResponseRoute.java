package com.ttek.testtask.javaspring.route;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class ResponseRoute extends RouteBuilder {

    @Value("${service-settings.remote-service-endpoint}")
    private String remoteServiceEndpoint;

    @Override
    public void configure() throws Exception {
        from("direct:responseRoute")
                .process(exchange -> {
                    int statusCode = (int) exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE);
                    if(statusCode == HttpStatus.OK.value()){
                        String response = new String((byte[]) exchange.getMessage().getBody(), StandardCharsets.UTF_8);
                        log.info("Success URL:{}, Body:{}, Status:{}", remoteServiceEndpoint, response, HttpStatus.OK.getReasonPhrase());
                    } else if (statusCode == HttpStatus.BAD_REQUEST.value()) {
                        log.error("Failure URL:{}, Status:{}", remoteServiceEndpoint, HttpStatus.BAD_REQUEST.getReasonPhrase());
                    }else {
                        log.error("Failure URL:{}, statusCode:{}", remoteServiceEndpoint, statusCode);
                        throw new Exception(String.valueOf(statusCode));
                    }
                })
                .end();
    }
}
