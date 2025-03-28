package com.ttek.testtask.javaspring.route;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.annotation.DirtiesContext;

@CamelSpringBootTest
@EnableAutoConfiguration
@DirtiesContext
@SpringBootTest(classes = {RestRoute.class, ResponseRoute.class, RestRouteTest.TestConfig.class})
class RestRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @EndpointInject("mock:test")
    MockEndpoint mockEndpoint;

    @Configuration
    static class TestConfig {

        @Bean
        RoutesBuilder route() {
            return new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("direct:test")
                            .to("direct:restRoute")
                            .to("mock:test");
                }
            };
        }
    }

    @Test
    void remoteServiceEndpoint200() throws InterruptedException {
        mockEndpoint.setExpectedMessageCount(1);
        mockEndpoint.expectedBodiesReceived("Success");
        producerTemplate.sendBody("direct:test", "{\"name\":\"John\", \"age\":30, \"car\":null}");
        mockEndpoint.assertIsSatisfied();
    }

    @Test
    void remoteServiceEndpoint400() throws InterruptedException {
        mockEndpoint.setExpectedMessageCount(0);
        producerTemplate.sendBody("direct:test", "Test");
        mockEndpoint.assertIsNotSatisfied();
    }
}
