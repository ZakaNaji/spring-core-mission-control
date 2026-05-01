package com.znaji.conversion;

import com.znaji.domain.Incident;
import com.znaji.domain.IncidentCommand;
import org.springframework.core.convert.converter.Converter;

public class IncidentMapper {

    private IncidentMapper () {
    }

    public static Incident toDomain(IncidentCommand source) {
        return new Incident(
                source.getId(),
                source.getType(),
                source.getSeverity(),
                source.getSource(),
                source.getValue(),
                source.getThreshold(),
                source.getCustomerTier()
        );
    }
}
