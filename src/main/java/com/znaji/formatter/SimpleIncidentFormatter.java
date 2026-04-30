package com.znaji.formatter;

import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponsePlan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("simpleFormatter")
@Primary
public class SimpleIncidentFormatter implements IncidentFormatter {
    @Override
    public String format(IncidentCommand incident, ResponsePlan plan) {
        return String.format("Incident: %s | Type: %s | Plan: %s",
                incident.getId(), incident.getType(), plan);
    }
}
