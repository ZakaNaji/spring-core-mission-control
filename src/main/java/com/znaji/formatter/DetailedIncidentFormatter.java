package com.znaji.formatter;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;
import org.springframework.stereotype.Component;

@Component("detailedFormatter")
public class DetailedIncidentFormatter implements IncidentFormatter {
    @Override
    public String format( Incident incident, ResponsePlan plan) {
        return String.format("Detailed Incident Report:\n" +
                        "ID: %s\n" +
                        "Type: %s\n" +
                        "Source: %s\n" +
                        "Severity: %s\n" +
                        "Response Plan: %s",
                incident.id(),
                incident.type(),
                incident.source(),
                incident.severity(),
                plan);
    }
}
