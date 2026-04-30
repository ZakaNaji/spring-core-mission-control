package com.znaji.rule;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;

import java.util.Properties;
import java.util.regex.Pattern;

public class RulePropertyValueMapper {

    private RulePropertyValueMapper() {
        // Private constructor to prevent instantiation
    }

    private static final Pattern RULE_PATTERN = Pattern
            .compile("rule\\.(\\d+)\\.(name|expression|plan)");

    public static MutablePropertyValues map(Properties properties) {
        MutablePropertyValues mpv = new MutablePropertyValues();
        for (String key : properties.stringPropertyNames()) {
            var matcher = RULE_PATTERN.matcher(key);
            if (matcher.matches()) {
                String index = matcher.group(1);
                String field = matcher.group(2);
                String value = properties.getProperty(key);
                mpv.addPropertyValue(new PropertyValue("rules[" + (Integer.parseInt(index) - 1) + "]." + field, value));
            }
        }
        return mpv;
    }
}
