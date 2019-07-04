package com.swa.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;

import java.util.Scanner;

public class Creator {
    private static final String ENDPOINT = "http://localhost:4576/";
    private static final String REGION = "us-east-1";

    public void execute() {
        AmazonSQS sqs = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getEndpoint())
                .build();

        CreateQueueRequest createQueueRequest = new CreateQueueRequest()
                .withQueueName("camelqueue");
        CreateQueueResult result = sqs.createQueue(createQueueRequest);
        System.out.println("QueueURL: " + result.getQueueUrl());

    }

    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(ENDPOINT, REGION);
    }
}


