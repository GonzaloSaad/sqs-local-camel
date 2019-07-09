import org.junit.Ignore;
import org.poc.camel.CamelConsumer;
import org.poc.camel.CamelQueueResult;
import org.poc.camel.processor.JMSCamelProcessor;
import org.poc.camel.processor.SQSCamelProcessor;
import org.poc.camel.registry.JMSRegistryProvider;
import org.poc.camel.registry.SQSRegistryProvider;
import org.poc.camel.supplier.JMSRouteSupplier;
import org.poc.camel.supplier.SQSRouteSupplier;
import org.poc.sqs.SQSProducer;
import org.poc.sqs.SQSQueueCreator;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CamelConsumerPoC {

    @Test
    public void runPoCWithSQS() throws Exception {
        String queueName = "camelQueue";
        String messageBody = "SuperMario rocks!";
        String attributeName = "TEAM";
        String attributeValue = "SUPER_MARIO";
        Map<String, String> attributes = getAttributes(attributeName, attributeValue);

        SQSQueueCreator queueCreator = new SQSQueueCreator(queueName);
        queueCreator.create();

        SQSProducer producer = new SQSProducer(messageBody, attributes);
        producer.produce();

        System.out.println("Consuming with SQS...");
        CamelConsumer camelConsumer = new CamelConsumer(new SQSRouteSupplier(queueName),
                new SQSCamelProcessor(), new SQSRegistryProvider());

        CamelQueueResult camelQueueResult = camelConsumer.consume();

        assertEquals(messageBody, camelQueueResult.getMessageBody());

        Map<String, String> messageAttributes = camelQueueResult.getMessageAttributes();
        assertEquals(attributeValue, messageAttributes.get(attributeName));
    }

    @Test
    public void runPoCWithJMS() throws Exception {
        String queueName = "camelQueue";
        String messageBody = "SuperMario rocks!";
        String attributeName = "TEAM";
        String attributeValue = "SUPER_MARIO";
        Map<String, String> attributes = getAttributes(attributeName, attributeValue);

        SQSQueueCreator queueCreator = new SQSQueueCreator(queueName);
        queueCreator.create();

        SQSProducer producer = new SQSProducer(messageBody, attributes);
        producer.produce();

        System.out.println("Consuming with JMS...");
        CamelConsumer camelConsumer = new CamelConsumer(
                new JMSRouteSupplier(queueName),
                new JMSCamelProcessor(),
                new JMSRegistryProvider());

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
