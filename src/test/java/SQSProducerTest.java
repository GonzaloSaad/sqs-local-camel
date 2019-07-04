import com.swa.sqs.SQSProducer;
import org.junit.Test;

public class SQSProducerTest {
    @Test
    public void run(){
        SQSProducer producer = new SQSProducer();
        producer.run();
    }
}
