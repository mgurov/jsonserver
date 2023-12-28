package com.example.jsonserver.serdeser.javavars;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Objects;

public class Holder {
    public String details;
    @JsonUnwrapped
    public Base base;

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
