package com.znaji.event;


import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MissionEventListener {

    @EventListener
    public void onIncidentLoaded(IncidentLoadedEvent event) {
        System.out.println("[EVENT] IncidentLoadedEvent: " + event.location());
    }

    @EventListener
    public void onIncidentValidated(IncidentValidatedEvent event) {
        System.out.println("[EVENT] IncidentValidatedEvent: " + event.command());
    }

    @EventListener
    public void onRuleMatched(RuleMatchedEvent event) {
        System.out.println("[EVENT] RuleMatchedEvent: " + event.ruleName());
    }

    @EventListener
    public void onResponseDispatched(ResponseDispatchedEvent event) {
        System.out.println("[EVENT] ResponseDispatchedEvent: " + event.incident() + " with plan " + event.responsePlan());
    }

    @EventListener
    public void onIncidentResolved(IncidentResolvedEvent event) {
        System.out.println("[EVENT] IncidentResolvedEvent: " + event.incident() + " with decision " + event.decision());
    }

    @EventListener
    public void onIncidentFailed(IncidentFailedEvent event) {
        System.out.println("[EVENT] IncidentFailedEvent: " + event.location() + " with errors " + event.errors());
    }
}
