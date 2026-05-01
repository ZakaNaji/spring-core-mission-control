package com.znaji.engine;

import com.znaji.IncidentValidationException;
import com.znaji.binding.IncidentBinder;
import com.znaji.channel.ResponseDispatcher;
import com.znaji.conversion.IncidentMapper;
import com.znaji.domain.Incident;
import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponseDecision;
import com.znaji.event.IncidentFailedEvent;
import com.znaji.event.IncidentLoadedEvent;
import com.znaji.event.IncidentResolvedEvent;
import com.znaji.event.ResponseDispatchedEvent;
import com.znaji.lifecycle.MissionExecutionContext;
import com.znaji.report.LocalizedIncidentReportService;
import com.znaji.report.StartupReport;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MissionControlEngine {

    private final StartupReport startupReport;
    private final ResponseDispatcher responseDispatcher;
    private final IncidentBinder incidentBinder;
    private final IncidentRuleEngine ruleEngine;
    private final ConversionService conversionService;
    private final ApplicationEventPublisher eventPublisher;
    private final LocalizedIncidentReportService reportService;
    private final ObjectProvider<MissionExecutionContext> executionContextProvider;

    public MissionControlEngine(StartupReport startupReport, ResponseDispatcher responseDispatcher, IncidentBinder incidentBinder, IncidentRuleEngine ruleEngine, ConversionService conversionService, ApplicationEventPublisher eventPublisher, LocalizedIncidentReportService reportService, ObjectProvider<MissionExecutionContext> executionContextProvider) {
        this.startupReport = startupReport;
        this.responseDispatcher = responseDispatcher;
        this.incidentBinder = incidentBinder;
        this.ruleEngine = ruleEngine;
        this.conversionService = conversionService;
        this.eventPublisher = eventPublisher;
        this.reportService = reportService;
        this.executionContextProvider = executionContextProvider;
    }

    public void start(String incidentLocation) {
        startupReport.report();
        System.out.println("[Execution ] Mission execution context ID: " + executionContextProvider.getIfAvailable().getExecutionId());

        // Simulate receiving an incident
        String prefix = "incident.";
        try {
            IncidentCommand command = getIncident(incidentLocation, prefix);
            // no dispatch for now, just print the command

            Incident incident = IncidentMapper.toDomain(command);
            ResponseDecision responseDecision = ruleEngine.evaluate(incident);
            responseDispatcher.dispatch(incident, responseDecision.responsePlan());

            eventPublisher.publishEvent(new ResponseDispatchedEvent(incident, responseDecision.responsePlan()));
            eventPublisher.publishEvent(new IncidentResolvedEvent(incident, responseDecision));

            System.out.println("[REPORT] " + reportService.generateReport(incident, responseDecision, Locale.FRENCH));
        } catch (IncidentValidationException e) {
            eventPublisher.publishEvent(new IncidentFailedEvent(incidentLocation, e.getErrors()));
            return;
        }

    }


    public IncidentCommand getIncident(String incidentLocation, String prefix) {
        System.out.println("Handling incident from location: " + incidentLocation);
        IncidentCommand command = incidentBinder.bind(incidentLocation, prefix);
        System.out.println("Incident bound to command: " + command.getId());
        return command;
    }
}
