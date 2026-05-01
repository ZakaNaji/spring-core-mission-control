package com.znaji.formatter;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;

public interface IncidentFormatter {
    String format(Incident incident, ResponsePlan plan);
}
