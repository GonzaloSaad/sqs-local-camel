package org.poc.camel.registry;

import com.amazonaws.services.sqs.AmazonSQS;
import org.apache.camel.impl.SimpleRegistry;
import org.poc.SQSClientProvider;

public class SQSRegistryProvider implements RegistryProvider {
    @Override
    public SimpleRegistry getRegistry() {
        AmazonSQS client = SQSClientProvider.getInstance().getSqsClient();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("client", client);
        return registry;
    }
}
