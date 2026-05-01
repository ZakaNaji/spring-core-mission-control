package com.znaji.event;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponseDecision;

public record IncidentResolvedEvent(Incident incident, ResponseDecision decision) {}
