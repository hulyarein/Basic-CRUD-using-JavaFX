package com.example.crud;



public class User {
    private int id;
    private final String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public int getId() {return id;}

}
