package com.znaji.validator;

import com.znaji.domain.IncidentCommand;
import com.znaji.domain.IncidentType;
import com.znaji.domain.Severity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;

@Component
public class IncidentCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return IncidentCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        IncidentCommand command = (IncidentCommand) target;

        if (command.getId() == null || command.getId().value().isBlank()) {
            errors.rejectValue("id", "id.empty", "Incident ID must not be empty");
        }

        if (command.getSource() == null || command.getSource().value().isBlank()) {
            errors.rejectValue("source", "source.empty", "Incident source must not be empty");
        }

        if (command.getSeverity() == null) {
            errors.rejectValue("severity", "severity.null", "Incident severity must be specified");
        }

        if (command.getType() == null) {
            errors.rejectValue("type", "type.null", "Incident type must be specified");
        }

        if (command.getCustomerTier() == null) {
            errors.rejectValue("customerTier", "customerTier.null", "Customer tier must be specified");
        }

        if (command.getValue() != null && command.getValue().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("value", "value.negative", "Incident value must be non-negative");
        }

        if (command.getThreshold() != null && command.getThreshold().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("threshold", "threshold.negative", "Incident threshold must be non-negative");
        }

        //HOME_ENERGY_SPIKE requires value and threshold:
        if (command.getType() == IncidentType.HOME_ENERGY_SPIKE) {
            if (command.getValue() == null) {
                errors.rejectValue("value", "value.required", "Value is required for HOME_ENERGY_SPIKE incidents");
            } else if (command.getValue().compareTo(BigDecimal.ZERO) <= 0) {
                errors.rejectValue("value", "value.positive", "Value must be positive for HOME_ENERGY_SPIKE incidents");
            }

            if (command.getThreshold() == null) {
                errors.rejectValue("threshold", "threshold.required", "Threshold is required for HOME_ENERGY_SPIKE incidents");
            } else if (command.getThreshold().compareTo(BigDecimal.ZERO) <= 0) {
                errors.rejectValue("threshold", "threshold.positive", "Threshold must be positive for HOME_ENERGY_SPIKE incidents");
            }
        }

        //PAYMENT_FAILURE requires severity HIGH or CRITICAL
        if (command.getType() == IncidentType.PAYMENT_FAILURE && command.getSeverity() != null) {
            if (command.getSeverity() != Severity.HIGH && command.getSeverity() != Severity.CRITICAL) {
                errors.rejectValue("severity", "severity.invalid", "Payment failure incidents must have severity HIGH or CRITICAL");
            }
        }
    }
}
