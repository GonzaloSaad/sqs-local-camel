import com.swa.sqs.CamelConsumer;
import org.junit.Test;

import java.util.function.Consumer;

public class CamelConsumerTest {

    @Test
    public void run() throws Exception {
        CamelConsumer consumer = new CamelConsumer();
        consumer.run();
    }
}
