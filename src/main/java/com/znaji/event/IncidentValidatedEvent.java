package com.znaji.event;

import com.znaji.domain.IncidentCommand;

public record IncidentValidatedEvent(IncidentCommand command) {}