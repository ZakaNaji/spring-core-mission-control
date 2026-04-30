package com.znaji.channel;

import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponsePlan;
import com.znaji.formatter.IncidentFormatter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class ConsoleResponseChannel implements ResponseChannel{
    private final IncidentFormatter formatter;

    public ConsoleResponseChannel(IncidentFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void notify(IncidentCommand incident, ResponsePlan plan) {
        String message = formatter.format(incident, plan);
        System.out.println("CONSOLE NOTIFICATION: " + message);
    }
}
