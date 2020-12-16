package com.teaching.codefinder;

public class Student {

    private String number;
    private String name;
    private String code;

    /** Praise the class constructor. */
    public Student(String number, String name, String code){
        this.number = number;
        this.name = name;
        this.code = code;
    }

    /** Praise the class constructor. */
    public Student(String number, String name){
        this.number = number;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
