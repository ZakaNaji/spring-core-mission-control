package com.znaji.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class MissionControlLifecycleProbe implements InitializingBean, DisposableBean {

    @PostConstruct
    public void postConstruct() {
        System.out.println("[LIFECYCLE] @PostConstruct method called. Mission Control is initializing...");
    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("[LIFECYCLE] @PreDestroy method called. Mission Control is shutting down...");
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("[LIFECYCLE] afterPropertiesSet() called. Mission Control is initializing...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("[LIFECYCLE] destroy() called. Mission Control is shutting down...");
    }
}
