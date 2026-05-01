package com.znaji.channel;

import com.znaji.domain.Incident;
import com.znaji.domain.IncidentCommand;
import com.znaji.domain.ResponsePlan;
import com.znaji.event.ResponseDispatchedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseDispatcher {

    private final List<ResponseChannel> channels;
    private final ApplicationEventPublisher applicationEvent;

    public ResponseDispatcher(List<ResponseChannel> channels, ApplicationEventPublisher applicationEvent) {
        this.channels = channels;
        this.applicationEvent = applicationEvent;
    }

    public void dispatch(Incident incident, ResponsePlan plan) {
        applicationEvent.publishEvent(new ResponseDispatchedEvent(incident, plan));
        channels.forEach(channel -> channel.notify(incident, plan));
    }
}
