package com.znaji.conversion;

import com.znaji.domain.IncidentId;
import org.springframework.core.convert.converter.Converter;

public class StringToIncidentIdConverter implements Converter<String, IncidentId> {

    @Override
    public IncidentId convert(String source) {
        try {
            return new IncidentId(source.trim());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid IncidentId format: " + source, e);
        }
    }
}
