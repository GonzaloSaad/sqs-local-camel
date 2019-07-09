package org.poc.camel.processor;

import org.apache.camel.Processor;
import org.poc.camel.CamelQueueResult;

public interface CamelProcessor extends Processor {
    CamelQueueResult getResult();
}
