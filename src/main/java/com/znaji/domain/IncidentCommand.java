package com.znaji.domain;

import java.math.BigDecimal;

public class IncidentCommand {

    private IncidentId id;
    private IncidentType type;
    private Severity severity;
    private SourceId source;
    private BigDecimal value;
    private BigDecimal threshold;
    private CustomerTier customerTier;

    //toString:
    @Override
    public String toString() {
        return "IncidentCommand{" +
                "id=" + id +
                ", type=" + type +
                ", severity=" + severity +
                ", source=" + source +
                ", value=" + value +
                ", threshold=" + threshold +
                ", customerTier=" + customerTier +
                '}';
    }

    // getters and setters


    public IncidentId getId() {
        return id;
    }

    public void setId(IncidentId id) {
        this.id = id;
    }

    public IncidentType getType() {
        return type;
    }

    public void setType(IncidentType type) {
        this.type = type;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public SourceId getSource() {
        return source;
    }

    public void setSource(SourceId source) {
        this.source = source;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public CustomerTier getCustomerTier() {
        return customerTier;
    }

    public void setCustomerTier(CustomerTier customerTier) {
        this.customerTier = customerTier;
    }
}