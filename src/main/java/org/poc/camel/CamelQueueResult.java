package org.poc.camel;

import java.util.Map;

public class CamelQueueResult {
    private final String messageBody;
    private final Map<String, String> messageAttributes;

    public CamelQueueResult(String messageBody, Map<String, String> messageAttribute) {
        this.messageBody = messageBody;
        this.messageAttributes = messageAttribute;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public Map<String, String> getMessageAttributes() {
        return messageAttributes;
    }
}