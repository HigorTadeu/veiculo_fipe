package com.htbsc.fipe.model;

public class Motocicle {
    private Integer code;
    private String name;

    public Motocicle(Integer code, String name){
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
