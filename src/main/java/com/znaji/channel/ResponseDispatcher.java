package com.znaji.channel;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponseDispatcher {

    private final List<ResponseChannel> channels;

    public ResponseDispatcher(List<ResponseChannel> channels) {
        this.channels = channels;
    }

    public void dispatch(Incident incident, ResponsePlan plan) {
        channels.forEach(channel -> channel.notify(incident, plan));
    }
}
