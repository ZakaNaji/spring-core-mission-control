package com.znaji;

import com.znaji.config.AppConfig;
import com.znaji.engine.MissionControlEngine;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MissionControlApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.getEnvironment().setActiveProfiles("prod");
            context.refresh();

            MissionControlEngine engine = context.getBean(MissionControlEngine.class);
            engine.start("classpath:incidents/home-energy-spike.properties");
            System.out.println("second Incident:");
            engine.start("classpath:incidents/invalid-inci1.properties");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
