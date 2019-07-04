package com.swa.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;

import java.util.HashMap;
import java.util.Map;

public class SQSProducer {
    public void run(){
        String queueUrl = "http://localhost:4576/queue/camelqueue";

        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody("Amir rocks!")
                .withMessageAttributes(getMessageAttribues());
        SendMessageResult sendMessageResult = sqs.sendMessage(request);
        System.out.println(sendMessageResult.getMD5OfMessageAttributes());
        System.out.println(sendMessageResult.getMessageId());
    }

    private static Map<String, MessageAttributeValue> getMessageAttribues(){
        Map<String, MessageAttributeValue> map = new HashMap<>();
        MessageAttributeValue value = new MessageAttributeValue()
                .withStringValue("Mary rocks!")
                .withDataType("String");
        map.put("TL", value);
        return map;
    }
}
