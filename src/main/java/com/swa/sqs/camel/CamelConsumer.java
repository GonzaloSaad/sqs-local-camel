package com.swa.sqs.camel;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.swa.sqs.Environment;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CamelConsumer {

    private final String queueName;

    public CamelConsumer(String queueName) {
        this.queueName = queueName;
    }

    public CamelQueueResult consume() throws Exception {

        AmazonSQS client = getSqsClient();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("client", client);

        CamelProcessor camelProcessor = new CamelProcessor();

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new CamelRoute(queueName, camelProcessor));
        camelContext.start();

        Thread.sleep(3000);
        camelContext.stop();
        return camelProcessor.getResult();
    }

    private AmazonSQS getSqsClient() {
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getEndpoint())
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                Environment.getBaseEndpoint(),
                Environment.getRegion()
        );
    }
}
