package com.znaji.channel;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;
import com.znaji.formatter.IncidentFormatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Order(3)
public class FakeEmailResponseChannel implements ResponseChannel {
    private final IncidentFormatter formatter;

    public FakeEmailResponseChannel(@Qualifier("simpleFormatter") IncidentFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void notify(Incident incident, ResponsePlan plan) {
        String emailContent = formatter.format(incident, plan);
        // Simulate sending an email by printing to console
        System.out.println("FAKE EMAIL SENT: " + emailContent);
    }
}
