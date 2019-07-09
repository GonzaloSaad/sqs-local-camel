package org.poc.camel.registry;

import org.apache.camel.impl.SimpleRegistry;

public interface RegistryProvider {
    SimpleRegistry getRegistry();
}
