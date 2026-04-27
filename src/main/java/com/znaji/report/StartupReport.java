package com.znaji.report;

import org.springframework.stereotype.Component;

@Component
public class StartupReport {

    public void report() {
        System.out.println("Mission Control is starting up...");
    }
}
