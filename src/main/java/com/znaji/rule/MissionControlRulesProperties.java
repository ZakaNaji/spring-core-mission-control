package com.znaji.rule;

import java.util.ArrayList;
import java.util.List;

public class MissionControlRulesProperties {

    private List<IncidentRuleConfig> rules = new ArrayList<>();

    public List<IncidentRuleConfig> getRules() {
        return rules;
    }

    public void setRules(List<IncidentRuleConfig> rules) {
        this.rules = rules;
    }
}
