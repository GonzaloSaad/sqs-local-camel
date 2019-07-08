package org.poc.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.poc.QueueURL;
import org.poc.SQSClientProvider;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class SQSProducer {

    private final String messageBody;
    private final Map<String, String> messageAttributes;

    public SQSProducer(String messageBody, Map<String, String> messageAttributes) {
        this.messageBody = messageBody;
        this.messageAttributes = messageAttributes;
    }

    public void produce() {
        Optional<String> queueUrl = QueueURL.getInstance().getQueueURL();
        if (queueUrl.isPresent()) {
            System.out.println("Sending message to SQS.");

            AmazonSQS sqs = SQSClientProvider.getInstance().getSqsClient();
            SendMessageRequest request = new SendMessageRequest()
                    .withQueueUrl(queueUrl.get())
                    .withMessageBody(messageBody)
                    .withMessageAttributes(getMessageAttributes(messageAttributes));
            SendMessageResult sendMessageResult = sqs.sendMessage(request);

            System.out.println("MD5: " + sendMessageResult.getMD5OfMessageAttributes());
            System.out.println("Message ID: " + sendMessageResult.getMessageId());
        }
    }

    private Map<String, MessageAttributeValue> getMessageAttributes(Map<String, String> messageAttributes) {
        return messageAttributes.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> createMessageAttribute(e.getValue())));
    }

    private MessageAttributeValue createMessageAttribute(String value) {
        return new MessageAttributeValue()
                .withStringValue(value)
                .withDataType("String");
    }
}
