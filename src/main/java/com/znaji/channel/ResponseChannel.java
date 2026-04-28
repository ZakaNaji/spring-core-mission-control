package com.znaji.channel;

import com.znaji.domain.Incident;
import com.znaji.domain.ResponsePlan;

public interface ResponseChannel {
    void notify(Incident incident, ResponsePlan plan);
}
