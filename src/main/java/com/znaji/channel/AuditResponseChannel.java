package com.znaji.channel;

import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponsePlan;
import com.znaji.formatter.IncidentFormatter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2) // Ensure this runs after ConsoleResponseChannel
public class AuditResponseChannel implements ResponseChannel {
    private final IncidentFormatter formatter;

    public AuditResponseChannel(IncidentFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void notify(IncidentCommand incident, ResponsePlan plan) {
        String logEntry = formatter.format(incident, plan);
        // Simulate writing to an audit log
        System.out.println("AUDIT LOG: " + logEntry);
    }
}
