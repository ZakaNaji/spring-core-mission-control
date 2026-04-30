package com.znaji.rule;

import com.znaji.domain.ResponsePlan;

public record IncidentRule(
        String name,
        String expression,
        ResponsePlan plan
) {
}
