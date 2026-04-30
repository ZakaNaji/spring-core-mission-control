package com.znaji.conversion;

import com.znaji.domain.SourceId;
import org.springframework.core.convert.converter.Converter;

public class StringToSourceIdConverter implements Converter<String, SourceId> {

    @Override
    public SourceId convert(String source) {
        try {
            return new SourceId(source);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid SourceId format: " + source, e);
        }
    }
}
