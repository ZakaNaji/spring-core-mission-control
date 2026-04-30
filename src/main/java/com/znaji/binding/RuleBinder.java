package com.znaji.binding;

import com.znaji.resource.IncidentLoader;
import com.znaji.rule.IncidentRule;
import com.znaji.rule.IncidentRuleConfig;
import com.znaji.rule.MissionControlRulesProperties;
import com.znaji.rule.RulePropertyValueMapper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;

import java.util.List;
import java.util.Properties;

@Component
public class RuleBinder {

    private final IncidentLoader incidentLoader;
    private final ConversionService conversionService;

    public RuleBinder(IncidentLoader incidentLoader, ConversionService conversionService) {
        this.incidentLoader = incidentLoader;
        this.conversionService = conversionService;
    }

    public List<IncidentRule> bind(String location) {
        Properties ruleProps = incidentLoader.load(location);
        MutablePropertyValues rulePropValues = RulePropertyValueMapper.map(ruleProps);

        MissionControlRulesProperties target = new MissionControlRulesProperties();
        DataBinder binder = new DataBinder(target);
        binder.setConversionService(conversionService);

        binder.setAutoGrowNestedPaths(true);
        binder.setAutoGrowCollectionLimit(100); // Set a reasonable limit for collection growth
        binder.setAllowedFields("rules[*].name", "rules[*].expression", "rules[*].plan");
        binder.bind(rulePropValues);

        if (binder.getBindingResult().hasErrors()) {
            binder.getBindingResult()
                    .getAllErrors()
                    .forEach(error -> System.err.println("[Binding error]: " + error));
            throw new IllegalArgumentException("Failed to bind rule properties: " + binder.getBindingResult());
        }

        return target.getRules()
                .stream()
                .map(IncidentRuleConfig::toIncidentRule)
                .toList();

    }
}
