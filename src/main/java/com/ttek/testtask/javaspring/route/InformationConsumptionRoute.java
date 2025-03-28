package com.ttek.testtask.javaspring.route;

import com.ttek.testtask.javaspring.processor.SubmitterImplementationProcessor;
import com.ttek.testtask.javaspring.processor.MessageLogger;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class InformationConsumptionRoute extends RouteBuilder {

    @Autowired
    private MessageLogger messageBodyLogger;

    @Value("${service-settings.data-topic}")
    private String topic;

    @Override
    public void configure() throws Exception {

        from("kafka:" + topic).routeId("kafkaRoute")
                .process(messageBodyLogger)
                .to("direct:restRoute")
                .end();
    }
}
