package com.znaji;

import com.znaji.binding.RuleBinder;
import com.znaji.config.AppConfig;
import com.znaji.domain.Incident;
import com.znaji.domain.IncidentCommand;
import com.znaji.domain.IncidentType;
import com.znaji.domain.ResponsePlan;
import com.znaji.engine.MissionControlEngine;
import com.znaji.rule.IncidentRule;
import com.znaji.utils.Helper;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Properties;

public class MissionControlApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.getEnvironment().setActiveProfiles("prod");
            context.refresh();

            MissionControlEngine engine = context.getBean(MissionControlEngine.class);
            //engine.start();

            System.out.println("Mission Control Properties:");
            RuleBinder ruleBinder = context.getBean(RuleBinder.class);
            var rules = ruleBinder.bind("classpath:mission-control.properties");
            System.out.println("Loaded Rules:");
            for (IncidentRule rule : rules) {
                System.out.println("- " + rule.name() + ": " + rule.expression() + " -> " + rule.plan());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
