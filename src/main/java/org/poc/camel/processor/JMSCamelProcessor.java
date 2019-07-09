package org.poc.camel.processor;

import com.amazonaws.services.sqs.model.MessageAttributeValue;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.poc.camel.CamelQueueResult;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class JMSCamelProcessor implements CamelProcessor {

    private String messageBody;
    private Map<String, String> messageAttributes;

    @Override
    public CamelQueueResult getResult() {
        return new CamelQueueResult(messageBody, messageAttributes);
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        Message message = exchange.getMessage();
        messageBody = (String) message.getBody();
        System.out.println("Body: " + messageBody);

        messageAttributes = getMessageAttributes(message);
        System.out.println("Attributes: " + messageAttributes);
    }

    private Map<String, String> getMessageAttributes(Message message) {
        return Optional.ofNullable(message)
                .map(Message::getHeaders)
                .map(Map::entrySet)
                .orElseGet(HashSet::new)
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> Optional.ofNullable(e.getValue()).map(Object::toString).orElse("")
                ));
    }
}
