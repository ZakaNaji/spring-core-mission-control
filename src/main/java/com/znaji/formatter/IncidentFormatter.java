package com.znaji.formatter;

import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponsePlan;

public interface IncidentFormatter {
    String format(IncidentCommand incident, ResponsePlan plan);
}
