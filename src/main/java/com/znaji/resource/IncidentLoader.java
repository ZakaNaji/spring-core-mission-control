package com.znaji.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

@Component
public class IncidentLoader {

    private final ResourceLoader resourceLoader;

    public IncidentLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Properties load(String location) {

        Resource resource = resourceLoader.getResource(location);

        if (!resource.exists()) {
            throw new IllegalArgumentException("Resource not found at location: " + location);
        }

        try (var inputStream = resource.getInputStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties from resource: " + location, e);
        }
    }
}
