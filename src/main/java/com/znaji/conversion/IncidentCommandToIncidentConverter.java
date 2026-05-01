package com.znaji.conversion;

import com.znaji.domain.Incident;
import com.znaji.domain.IncidentCommand;
import org.springframework.core.convert.converter.Converter;

public class IncidentCommandToIncidentConverter implements Converter<IncidentCommand, Incident> {

    @Override
    public Incident convert(IncidentCommand source) {
        return new Incident(
                source.getId().value(),
                source.getType(),
                source.getSeverity(),
                source.getSource().value(),
                source.getValue() != null ? source.getValue().doubleValue() : 0.0,
                source.getThreshold() != null ? source.getThreshold().doubleValue() : 0.0,
                source.getCustomerTier()
        );
    }
}
