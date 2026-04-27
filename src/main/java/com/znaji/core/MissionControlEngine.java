package com.znaji.core;

import com.znaji.report.StartupReport;
import org.springframework.stereotype.Component;

@Component
public class MissionControlEngine {

    private final StartupReport startupReport;

    public MissionControlEngine(StartupReport startupReport) {
        this.startupReport = startupReport;
    }

    public void start() {
        startupReport.report();
        System.out.println("Mission Control Engine is now running.");
    }
}
