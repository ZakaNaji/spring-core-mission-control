package com.znaji.engine;

import com.znaji.binding.RuleBinder;
import com.znaji.domain.Incident;
import com.znaji.domain.ResponseDecision;
import com.znaji.domain.ResponsePlan;
import com.znaji.rule.IncidentRule;
import jakarta.annotation.PostConstruct;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SpelIncidentRuleEngine implements IncidentRuleEngine {

    private final RuleBinder ruleBinder;
    private final static String MISSION_CONTROL_PROPERTIES = "classpath:mission-control.properties";
    private final ExpressionParser parser = new SpelExpressionParser();
    private List<IncidentRule> rules;


    public SpelIncidentRuleEngine(RuleBinder ruleBinder) {
        this.ruleBinder = ruleBinder;
    }

    @PostConstruct
    public void init() {
        this.rules = ruleBinder.bind(MISSION_CONTROL_PROPERTIES);
    }


    @Override
    public ResponseDecision evaluate(Incident incident) {

        for (IncidentRule rule : rules) {
            if (matches(rule, incident)) {
                return new ResponseDecision(rule.name(), rule.plan());
            }
        }
        return new ResponseDecision("fallback-no-action", ResponsePlan.NO_ACTION);
    }

    private boolean matches(IncidentRule rule, Incident incident) {
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable("incident", incident);
        try {
            return Boolean.TRUE.equals(
                    parser.parseExpression(rule.expression()).getValue(context, Boolean.class)
            );
        } catch (Exception e) {
            System.err.println("Error evaluating rule '" + rule.name() + "': " + e.getMessage());
            return false;
        }
    }
}
