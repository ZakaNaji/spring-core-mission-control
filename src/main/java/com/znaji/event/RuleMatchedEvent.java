package com.znaji.event;

import com.znaji.domain.ResponsePlan;

public record RuleMatchedEvent(String ruleName, ResponsePlan responsePlan) {}