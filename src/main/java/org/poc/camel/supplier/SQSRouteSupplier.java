package org.poc.camel.supplier;

import java.util.function.Supplier;

public class SQSRouteSupplier implements Supplier<String> {

    private final String queueName;

    private static final String URI_TEMPLATE = "aws-sqs://%s?amazonSQSClient=#client" +
            "&receiveMessageWaitTimeSeconds=10" +
            "&messageAttributeNames=All";

    public SQSRouteSupplier(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String get() {
        return String.format(URI_TEMPLATE, queueName);
    }
}
