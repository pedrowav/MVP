package com.digitalhouse.mvp.Model.POJO;

public class Pokemon {
    private String name;

    public Pokemon(String name) {
        this.name = name;
    }
    public Pokemon() {}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
