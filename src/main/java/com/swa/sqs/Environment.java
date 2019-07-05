package com.swa.sqs;

import java.util.Optional;

public class Environment {

    private static final String DEFAuLT_ENDPOINT = "http://localhost:4576/";
    private static final String DEFAULT_REGION = "us-east-1";


    public static String getBaseEndpoint(){
        return Optional.ofNullable(System.getProperty("SQS_ENDPOINT"))
                .orElse(DEFAuLT_ENDPOINT);
    }

    public static String getRegion(){
        return Optional.ofNullable(System.getProperty("SQS_REGION"))
                .orElse(DEFAULT_REGION);
    }
}
