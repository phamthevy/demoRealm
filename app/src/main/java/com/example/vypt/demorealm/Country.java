package com.example.vypt.demorealm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Country extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    private int population;

    public Country(){}

    @PrimaryKey
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
