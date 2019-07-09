package org.poc.camel.supplier;

import java.util.function.Supplier;

public class JMSRouteSupplier implements Supplier<String> {

    private final String queueName;

    private static final String URI_TEMPLATE = "jms:queue:%s?connectionFactory=#sqsConnectionFactory" +
            "&asyncConsumer=true&maxConcurrentConsumers=6&cacheLevelName=CACHE_NONE";

    public JMSRouteSupplier(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String get() {
        return String.format(URI_TEMPLATE, queueName);
    }
}
