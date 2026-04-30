package com.znaji.rule;

import com.znaji.domain.ResponsePlan;
import org.springframework.core.Ordered;

public class IncidentRuleConfig implements Ordered {

    private String name;
    private String expression;
    private ResponsePlan plan;
    private int order;

    public IncidentRule toIncidentRule() {
        return new IncidentRule(name, expression, plan);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public ResponsePlan getPlan() {
        return plan;
    }

    public void setPlan(ResponsePlan plan) {
        this.plan = plan;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
