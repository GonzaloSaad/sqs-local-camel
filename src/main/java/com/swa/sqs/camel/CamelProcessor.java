package com.swa.sqs.camel;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CamelProcessor implements Processor {

    private String messageBody;
    private Map<String, String> messageAttributes;

    @Override
    public void process(Exchange exchange) {
        System.out.println("Reading message...");
        Message message = exchange.getMessage();

        messageBody = (String) message.getBody();
        System.out.println("Body: " + messageBody);

        messageAttributes = getMessageAttributes(message);
        System.out.println("Attributes: " + messageAttributes);
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> getMessageAttributes(Message message) {
        return Optional.ofNullable(message)
                .map(Message::getHeaders)
                .map(map -> map.get("CamelAwsSqsMessageAttributes"))
                .map(o -> (Map<String, MessageAttributeValue>) o)
                .map(Map::entrySet)
                .orElse(new HashSet<>())
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e-> e.getValue().getStringValue()));
    }

    public CamelQueueResult getResult() {
        return new CamelQueueResult(messageBody, messageAttributes);
    }
}