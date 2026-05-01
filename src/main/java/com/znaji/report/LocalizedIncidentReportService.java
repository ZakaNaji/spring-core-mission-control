package com.znaji.report;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponseDecision;
import com.znaji.domain.ResponsePlan;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizedIncidentReportService {

    private final MessageSource messageSource;

    public LocalizedIncidentReportService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String generateReport(Incident incident, ResponseDecision decision, Locale locale) {
        String messageCode = resolveMessageCode(incident, decision);

        return messageSource.getMessage(messageCode, new Object[]{incident.source().value()}, locale);
    }

    private String resolveMessageCode(Incident incident, ResponseDecision decision) {
        return switch (incident.type()) {
            case HOME_ENERGY_SPIKE -> {
                if (decision.responsePlan() == ResponsePlan.NO_ACTION) yield "incident.energy.normal";
                yield "incident.energy.high";
            }
            case PAYMENT_FAILURE -> "incident.payment.failure";
            case SECURITY_LOGIN_ANOMALY -> "incident.security.critical";
            default -> "incident.default";
        };
    }
}
