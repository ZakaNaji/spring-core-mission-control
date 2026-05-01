package com.znaji.lifecycle;

import com.znaji.engine.IncidentRuleEngine;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class MissionBeanDefinitionValidator implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] ruleEngines = beanFactory.getBeanNamesForType(IncidentRuleEngine.class, true, false);
        if (ruleEngines.length == 0) {
            throw new BeanCreationException(
                    "No IncidentRuleEngine bean found"
            );
        }

        System.out.println("[BFPP] Bean definitions validated");
    }
}
