import org.poc.camel.CamelConsumer;
import org.poc.camel.CamelQueueResult;
import org.poc.sqs.SQSProducer;
import org.poc.sqs.SQSQueueCreator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CamelConsumerPoC {

    @Test
    public void runPoC() throws Exception {
        String queueName = "camelQueue";
        String messageBody = "SuperMario rocks!";
        String attributeName = "TEAM";
        String attributeValue = "SUPER_MARIO";
        Map<String, String> attributes = getAttributes(attributeName, attributeValue);

        SQSQueueCreator queueCreator = new SQSQueueCreator(queueName);
        queueCreator.create();

        SQSProducer producer = new SQSProducer(messageBody, attributes);
        producer.produce();

        CamelConsumer camelConsumer = new CamelConsumer(queueName);
        CamelQueueResult camelQueueResult = camelConsumer.consume();

        assertEquals(messageBody, camelQueueResult.getMessageBody());

        Map<String, String> messageAttributes = camelQueueResult.getMessageAttributes();
        assertEquals(attributeValue, messageAttributes.get(attributeName));
    }

    private Map<String, String> getAttributes(String key, String value) {
        Map<String, String> attributeMap = new HashMap<>();
        attributeMap.put(key, value);
        return attributeMap;
    }
}
