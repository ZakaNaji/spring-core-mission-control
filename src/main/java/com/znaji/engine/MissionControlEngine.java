package com.znaji.engine;

import com.znaji.binding.IncidentBinder;
import com.znaji.channel.ResponseDispatcher;
import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponsePlan;
import com.znaji.report.StartupReport;
import org.springframework.stereotype.Component;

@Component
public class MissionControlEngine {

    private final StartupReport startupReport;
    private final ResponseDispatcher responseDispatcher;
    private final IncidentBinder incidentBinder;

    public MissionControlEngine(StartupReport startupReport, ResponseDispatcher responseDispatcher, IncidentBinder incidentBinder) {
        this.startupReport = startupReport;
        this.responseDispatcher = responseDispatcher;
        this.incidentBinder = incidentBinder;
    }

    public void start() {
        startupReport.report();
        System.out.println("Mission Control Engine is now running.");

        // Simulate receiving an incident
        String incidentLocation = "classpath:incidents/home-energy-spike.properties";
        String prefix = "incident.";
        IncidentCommand command = getIncident(incidentLocation, prefix);
        // no dispatch for now, just print the command
        System.out.println("Received invalid Incident Command: " + command);
    }


    public IncidentCommand getIncident(String incidentLocation, String prefix) {
        System.out.println("Handling incident from location: " + incidentLocation);
        IncidentCommand command = incidentBinder.bind(incidentLocation, prefix);
        System.out.println("Incident bound to command: " + command.getId());
        return command;
    }
}
