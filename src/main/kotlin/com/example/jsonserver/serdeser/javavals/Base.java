package com.example.jsonserver.serdeser.javavals;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Base {
    public final String title;
    public final String description;

    @JsonCreator
    public Base(@JsonProperty("title") String title, @JsonProperty("description") String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Base{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(title, base.title) && Objects.equals(description, base.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description);
    }
}
