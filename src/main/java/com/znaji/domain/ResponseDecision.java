package com.znaji.domain;

public record ResponseDecision(
        String matchedRuleName,
        ResponsePlan responsePlan
) {
}