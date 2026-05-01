package com.znaji.engine;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponseDecision;

public interface IncidentRuleEngine {
    ResponseDecision evaluate(Incident incident);
}
