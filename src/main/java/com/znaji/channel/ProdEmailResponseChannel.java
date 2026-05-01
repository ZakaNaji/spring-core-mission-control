package com.znaji.channel;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;
import com.znaji.formatter.IncidentFormatter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@Order(4)
public class ProdEmailResponseChannel implements ResponseChannel {

    private final IncidentFormatter formatter;

    public ProdEmailResponseChannel(@Qualifier("detailedFormatter") IncidentFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public void notify(Incident incident, ResponsePlan plan) {
        // In a real production environment, this would send an actual email.
        String emailContent = formatter.format(incident, plan);
        // Simulate sending an email by printing to console
        System.out.println("PROD EMAIL SENT: " + emailContent);
    }
}
