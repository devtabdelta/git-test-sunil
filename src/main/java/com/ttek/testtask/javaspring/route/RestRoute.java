package com.ttek.testtask.javaspring.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .maximumRedeliveries("{{service-settings.retries-delivery}}")
                .redeliveryDelay("{{service-settings.retries-delay}}")
                .retryAttemptedLogLevel(LoggingLevel.WARN)
                .handled(true)
                .setExchangePattern(ExchangePattern.InOnly)
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(400))
                .setBody(exceptionMessage())
                .to("direct:responseRoute")
                .end();

        from("direct:restRoute").id("_restRoute")
                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .setBody(simple("${body}"))
                .log("restRoute: ${body}")
                .to("{{service-settings.remote-service-endpoint}}")
                .to("direct:responseRoute")
                .end();
    }
}
