package com.swa.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CamelConsumer {

    private static final String ENDPOINT = "http://localhost:4576";
    private static final String REGION = "us-east-1";

    public void run() throws Exception {

        AmazonSQS client = getSqsClient();
        SimpleRegistry registry = new SimpleRegistry();
        registry.put("client", client);

        CamelContext camelContext = new DefaultCamelContext(registry);
        camelContext.addRoutes(new CamelRoute());
        camelContext.start();

        System.out.println("CamelContext about to stop.");
        Thread.sleep(3000);
        camelContext.stop();
    }

    private AmazonSQS getSqsClient() {
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getEndpoint())
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION);
    }

    private class CamelRoute extends RouteBuilder {



        @Override
        public void configure() {
            System.out.println("CamelContext routers about to add.");
            from("aws-sqs://camelqueue?amazonSQSClient=#client&receiveMessageWaitTimeSeconds=10&messageAttributeNames=All")
                    .log("We've got a message")
                    .process(new CamelProcessor());
        }
    }

    private class CamelProcessor implements Processor{

        @Override
        public void process(Exchange exchange) {
            Message message = exchange.getMessage();
            System.out.println("Body: " + message.getBody());
            System.out.println("TL: " + message.getHeader("TL"));
        }
    }
}
