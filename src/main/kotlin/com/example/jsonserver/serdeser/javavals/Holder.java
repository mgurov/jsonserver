package com.example.jsonserver.serdeser.javavals;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Objects;

public class Holder {
    public final String details;
    @JsonUnwrapped
    public final Base base;

    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public Holder(@JsonProperty("details") String details) {
        this.details = details;
        this.base = null;
    }

    public Holder(String details, Base base) {
        this.details = details;
        this.base = base;
    }

    @Override
    public String toString() {
        return "Holder{" +
                "details='" + details + '\'' +
                ", base=" + base +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Holder holder = (Holder) o;
        return Objects.equals(details, holder.details) && Objects.equals(base, holder.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(details, base);
    }
}
