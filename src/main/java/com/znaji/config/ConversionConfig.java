package com.znaji.config;

import com.znaji.conversion.StringToIncidentIdConverter;
import com.znaji.conversion.StringToSourceIdConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

@Configuration
public class ConversionConfig {

    @Bean
    public ConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        // Register custom converters here if needed
        conversionService.addConverter(new StringToIncidentIdConverter());
        conversionService.addConverter(new StringToSourceIdConverter());
        return conversionService;
    }
}
