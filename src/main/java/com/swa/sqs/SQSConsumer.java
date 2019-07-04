package com.swa.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;

import java.util.List;

public class SQSConsumer {

    private static final String ENDPOINT = "http://localhost:4576/";
    private static final String REGION = "us-east-1";

    public void run() {

        String queueUrl = "http://localhost:4576/queue/camelqueue";
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        for (Message m : messages) {
            System.out.println(m.getBody());
            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
        }
    }



    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION);
    }
}
