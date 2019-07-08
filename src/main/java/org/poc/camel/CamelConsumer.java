package org.poc.camel;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.poc.Environment;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.poc.SQSClientProvider;

public class CamelConsumer {

    private final String queueName;

    public CamelConsumer(String queueName) {
        this.queueName = queueName;
    }

    public CamelQueueResult consume() throws Exception {

        AmazonSQS client = SQSClientProvider.getInstance().getSqsClient();
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
}
