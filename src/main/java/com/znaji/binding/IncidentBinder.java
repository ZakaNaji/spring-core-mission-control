package com.znaji.binding;

import com.znaji.domain.IncidentCommand;
import com.znaji.resource.IncidentLoader;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.util.Properties;

@Component
public class IncidentBinder {

    private final IncidentLoader incidentLoader;
    private final ConversionService conversionService;

    public IncidentBinder(IncidentLoader incidentLoader, ConversionService conversionService) {
        this.incidentLoader = incidentLoader;
        this.conversionService = conversionService;
    }

    public IncidentCommand bind(String location, String prefix) {
        MutablePropertyValues incidentProp = from(incidentLoader.load(location), prefix);
        IncidentCommand command = new IncidentCommand();

        DataBinder binder = new DataBinder(command);
        binder.setConversionService(conversionService);

        binder.bind(incidentProp);
        if (binder.getBindingResult().hasErrors()) {
            binder.getBindingResult()
                    .getAllErrors()
                    .forEach(error -> System.err.println("[Binding error]: " + error));
            throw new IllegalArgumentException("Failed to bind incident properties: " + binder.getBindingResult());
        }
        return command;
    }

    private MutablePropertyValues from(Properties properties, String prefix) {
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

