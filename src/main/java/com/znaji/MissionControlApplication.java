package com.znaji;

import com.znaji.config.AppConfig;
import com.znaji.engine.MissionControlEngine;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class MissionControlApplication {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(AppConfig.class);
            context.getEnvironment().setActiveProfiles("prod");
            context.refresh();

            LocaleContextHolder.setLocale(Locale.FRANCE);

            MissionControlEngine engine = context.getBean(MissionControlEngine.class);
            engine.start("classpath:incidents/home-energy-spike.properties");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
