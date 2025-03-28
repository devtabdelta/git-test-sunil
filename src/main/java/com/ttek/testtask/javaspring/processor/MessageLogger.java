package com.ttek.testtask.javaspring.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageLogger implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log(exchange.getMessage().getBody().toString());
    }

    private void log(final Object messageBody) {
        log.debug("Polled information: {}", messageBody);
    }

}
