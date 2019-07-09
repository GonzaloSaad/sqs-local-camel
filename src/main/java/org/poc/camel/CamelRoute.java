package org.poc.camel;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import java.util.function.Supplier;

public class CamelRoute extends RouteBuilder {

    private final Processor camelProcessor;
    private final Supplier<String> routeSupplier;

    public CamelRoute(Processor camelProcessor, Supplier<String> routeSupplier) {
        this.camelProcessor = camelProcessor;
        this.routeSupplier = routeSupplier;
    }

    @Override
    public void configure() {
        from(routeSupplier.get()).process(camelProcessor);
    }
}