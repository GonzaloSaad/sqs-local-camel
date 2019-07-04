import com.swa.sqs.CamelConsumer;
import com.swa.sqs.SQSConsumer;
import org.junit.Test;

public class SQSConsumerTest {

    @Test
    public void run() throws Exception {
        SQSConsumer consumer = new SQSConsumer();
        consumer.run();
    }
}
