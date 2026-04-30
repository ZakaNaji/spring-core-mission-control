package com.znaji.resource;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class IncidentLoader {

    private final ResourceLoader resourceLoader;

    public IncidentLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public Properties load(String location) {

        try {
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return properties;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load incident properties from " + location, e);
        }
    }
}
