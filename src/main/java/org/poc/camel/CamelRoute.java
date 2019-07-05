package org.poc.camel;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class CamelRoute extends RouteBuilder {

    private static final String URI_TEMPLATE = "aws-poc://%s?amazonSQSClient=#client" +
            "&receiveMessageWaitTimeSeconds=10" +
            "&messageAttributeNames=All";

    private final String queueName;
    private final Processor camelProcessor;

    public CamelRoute(String queueName, Processor camelProcessor) {
        this.queueName = queueName;
        this.camelProcessor = camelProcessor;
    }

    @Override
    public void configure() {
        from(getUri()).process(camelProcessor);
    }

    private String getUri() {
        return String.format(URI_TEMPLATE, queueName);
    }
}