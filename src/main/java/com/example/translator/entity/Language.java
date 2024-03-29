package com.example.translator.entity;

import java.util.Objects;

public class Language {

    public String code;
    public String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language languages = (Language) o;
        return Objects.equals(code, languages.code) && Objects.equals(name, languages.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, name);
    }

    @Override
    public String toString() {
        return "Languages{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
