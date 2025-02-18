package com.htbsc.fipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Truck {
    @JsonAlias("codigo")
    private Integer code;
    @JsonAlias("nome")
    private String name;

    public Truck(){}

    public Truck(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Truck -> Code: " + code + " Name: " + name ;
    }
}
