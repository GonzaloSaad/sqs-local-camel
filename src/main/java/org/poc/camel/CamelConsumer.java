package org.poc.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.poc.camel.processor.CamelProcessor;
import org.poc.camel.registry.RegistryProvider;

import java.util.function.Supplier;

public class CamelConsumer {

    private final Supplier<String> routeSupplier;
    private final CamelProcessor processor;
    private final RegistryProvider registryProvider;

    public CamelConsumer(Supplier<String> routeSupplier, CamelProcessor processor, RegistryProvider registryProvider) {
        this.routeSupplier = routeSupplier;
        this.processor = processor;
        this.registryProvider = registryProvider;
    }

    public CamelQueueResult consume() throws Exception {

        CamelContext camelContext = new DefaultCamelContext(registryProvider.getRegistry());
        camelContext.addRoutes(new CamelRoute(processor, routeSupplier));
        camelContext.start();

        Thread.sleep(3000);
        camelContext.stop();
        return processor.getResult();
    }
}
