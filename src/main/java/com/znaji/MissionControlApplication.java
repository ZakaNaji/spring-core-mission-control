package com.znaji;

import com.znaji.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MissionControlApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.getEnvironment().setActiveProfiles("dev");
            context.refresh();

            String appName = context.getBean("appName", String.class);
            System.out.println("Application Name: " + appName);
        }
    }
}
