package com.znaji.event;

import java.util.List;

public record IncidentFailedEvent(String location, List<String> errors) {}