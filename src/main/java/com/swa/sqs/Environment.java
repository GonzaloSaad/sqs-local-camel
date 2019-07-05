package com.swa.sqs;

import java.util.Optional;

public class Environment {

    private static final String DEFAULT_ENDPOINT_TEMPLATE = "http://localhost:%s/";
    private static final String DEFAULT_REGION = "us-east-1";
    private static final String DEFAULT_PORT = "4576";


    public static String getBaseEndpoint(){
        return Optional.ofNullable(System.getProperty("SQS_ENDPOINT"))
                .orElse(String.format(DEFAULT_ENDPOINT_TEMPLATE, getSQSPort()));
    }

    public static String getRegion(){
        return Optional.ofNullable(System.getProperty("SQS_REGION"))
                .orElse(DEFAULT_REGION);
    }

    public static String getSQSPort(){
        return Optional.ofNullable(System.getProperty("SQS_PORT"))
                .orElse(DEFAULT_PORT);
    }
}
