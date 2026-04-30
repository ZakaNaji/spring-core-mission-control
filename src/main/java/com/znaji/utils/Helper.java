package com.znaji.utils;

import org.springframework.beans.MutablePropertyValues;

import java.util.Properties;

public class Helper {

    private Helper() {
        // Private constructor to prevent instantiation
    }

    public static MutablePropertyValues from(Properties properties, String prefix) {
        MutablePropertyValues propertyValues = new MutablePropertyValues();
        for (String key : properties.stringPropertyNames()) {
            if (key.startsWith(prefix)) {
                String propertyKey = key.substring(prefix.length());
                String propertyValue = properties.getProperty(key);
                propertyValues.add(propertyKey, propertyValue);
            }
        }
        return propertyValues;
    }
}
