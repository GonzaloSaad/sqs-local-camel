package org.poc.camel.registry;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import org.apache.camel.impl.SimpleRegistry;
import org.poc.SQSClientProvider;

public class JMSRegistryProvider implements RegistryProvider {
    @Override
    public SimpleRegistry getRegistry() {
        SQSConnectionFactory connectionFactory = new SQSConnectionFactory(
                new ProviderConfiguration(),
                SQSClientProvider.getInstance().getSqsClient());

        SimpleRegistry registry = new SimpleRegistry();
        registry.put("sqsConnectionFactory", connectionFactory);
        return registry;
    }
}
