package com.znaji.lifecycle;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MissionExecutionContext {

    private final UUID executionId = UUID.randomUUID();

    public UUID getExecutionId() {
        return executionId;
    }
}
