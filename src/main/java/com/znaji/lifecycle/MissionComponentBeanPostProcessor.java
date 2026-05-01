package com.znaji.lifecycle;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MissionComponentBeanPostProcessor implements BeanPostProcessor{

    @Override
    public @Nullable Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(MissionComponent.class)) {
            MissionComponent annotation = bean.getClass().getAnnotation(MissionComponent.class);
            String componentName = annotation.value();
            System.out.println("[BPP] Initialized: " + componentName + " (" + bean.getClass().getSimpleName() + ")");
        }
        return bean;
    }
}
