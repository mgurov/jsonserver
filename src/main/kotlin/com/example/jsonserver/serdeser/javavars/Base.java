package com.example.jsonserver.serdeser.javavars;

import java.util.Objects;

public class Base {
    public String title;
    public String description;

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
