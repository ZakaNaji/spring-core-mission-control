package com.znaji.event;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;

public record ResponseDispatchedEvent(Incident incident, ResponsePlan responsePlan) {}
