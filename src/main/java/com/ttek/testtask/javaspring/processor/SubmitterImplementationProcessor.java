package com.ttek.testtask.javaspring.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubmitterImplementationProcessor implements Processor {
    protected HttpClient httpClient = HttpClient.newHttpClient();

    @Value("${service-settings.remote-service-endpoint}")
    private String serviceEndpoint;

    @Override
    public void process(Exchange exchange) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(serviceEndpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(exchange.getIn().getBody().toString()))
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());
        log.debug("Got API response.statusCode() ="+ response.statusCode());
        if(response.statusCode() != 200 && response.statusCode() != 400)
            throw new Exception("API Exception");
        log.debug("Got API response ="+ response.body());
    }
}
