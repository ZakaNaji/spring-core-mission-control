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
            //engine.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
