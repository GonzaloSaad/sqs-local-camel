package org.poc.sqs;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import org.poc.Environment;
import org.poc.QueueURL;
import org.poc.SQSClientProvider;

public class SQSQueueCreator {
    private final String queueName;

    public SQSQueueCreator(String queueName) {
        this.queueName = queueName;
    }

    public void create() {
        AmazonSQS sqs = SQSClientProvider.getInstance().getSqsClient();

        CreateQueueRequest createQueueRequest = new CreateQueueRequest()
                .withQueueName(queueName);
        CreateQueueResult result = sqs.createQueue(createQueueRequest);
        QueueURL.getInstance().setQueueURL(result.getQueueUrl());
        System.out.println("QueueURL: " + result.getQueueUrl());

    }

    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                Environment.getBaseEndpoint(),
                Environment.getRegion());
    }
}


