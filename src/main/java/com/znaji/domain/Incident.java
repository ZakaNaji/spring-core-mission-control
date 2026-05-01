package com.znaji.domain;

public record Incident (
     String id,
     IncidentType type,
     Severity severity,
     String source,
     double value,
     double threshold,
     CustomerTier customerTier) {}


/*
should look like this:
incident.id=INC-2025-0001
incident.type=HOME_ENERGY_SPIKE
incident.severity=HIGH
incident.source=kitchen-meter
incident.value=920
incident.threshold=700
incident.customerTier=PREMIUM
 */