package com.znaji.engine;

import com.znaji.channel.ResponseDispatcher;
import com.znaji.domain.Incident;
import com.znaji.domain.IncidentType;
import com.znaji.domain.ResponsePlan;
import com.znaji.report.StartupReport;
import org.springframework.stereotype.Component;

@Component
public class MissionControlEngine {

    private final StartupReport startupReport;
    private final ResponseDispatcher responseDispatcher;

    public MissionControlEngine(StartupReport startupReport, ResponseDispatcher responseDispatcher) {
        this.startupReport = startupReport;
        this.responseDispatcher = responseDispatcher;
    }

    public void start() {
        startupReport.report();
        System.out.println("Mission Control Engine is now running.");

        System.out.println("Simulating incident creation...");
        createFakeIncident();
    }

    public void createFakeIncident() {
        // Simulate creating a fake incident and response plan
        Incident incident = new Incident("INC123", IncidentType.HOME_ENERGY_SPIKE, "HIGH", "kitchen-meter", 920, 700, "PREMIUM");

        responseDispatcher.dispatch(incident, ResponsePlan.PREMIUM_ENERGY_ESCALATION);
    }
}
