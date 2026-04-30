package com.znaji.binding;

import com.znaji.domain.IncidentCommand;
import com.znaji.resource.IncidentLoader;
import com.znaji.validator.IncidentCommandValidator;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.util.Properties;

import static com.znaji.utils.Helper.from;

@Component
public class IncidentBinder {

    private final IncidentLoader incidentLoader;
    private final ConversionService conversionService;
    private final IncidentCommandValidator incidentCommandValidator;

    public IncidentBinder(IncidentLoader incidentLoader, ConversionService conversionService, IncidentCommandValidator incidentCommandValidator) {
        this.incidentLoader = incidentLoader;
        this.conversionService = conversionService;
        this.incidentCommandValidator = incidentCommandValidator;
    }

    public IncidentCommand bind(String location, String prefix) {
        MutablePropertyValues incidentProp = from(incidentLoader.load(location), prefix);
        IncidentCommand command = new IncidentCommand();

        DataBinder binder = new DataBinder(command);
        binder.setConversionService(conversionService);
        binder.setValidator(incidentCommandValidator);

        binder.bind(incidentProp);
        // Validate the binding results and log any errors
        if (binder.getBindingResult().hasErrors()) {
            binder.getBindingResult()
                    .getAllErrors()
                    .forEach(error -> System.err.println("[Binding error]: " + error));
            throw new IllegalArgumentException("Failed to bind incident properties: " + binder.getBindingResult());
        }
        binder.validate();
        if (binder.getBindingResult().hasErrors()) {
            binder.getBindingResult()
                    .getAllErrors()
                    .forEach(error -> System.err.println("[Validation error]: " + error.getDefaultMessage()));
            throw new IllegalArgumentException("Failed to validate incident command: " + binder.getBindingResult());
        }
        return command;
    }


}

