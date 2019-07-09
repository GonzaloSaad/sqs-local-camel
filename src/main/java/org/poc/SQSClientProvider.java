package org.poc;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public class SQSClientProvider {

    private static SQSClientProvider instance;

    private SQSClientProvider(){

    }

    public static SQSClientProvider getInstance() {
        if(instance == null){
            instance = new SQSClientProvider();
        }
        return instance;
    }

    public AmazonSQS getSqsClient() {
        return AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(getEndpoint())
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("AWS_KEY", "AWS_SECRET")))
                .build();
    }

    private AwsClientBuilder.EndpointConfiguration getEndpoint() {
        return new AwsClientBuilder.EndpointConfiguration(
                Environment.getBaseEndpoint(),
                Environment.getRegion()
        );
    }
}
