package com.works.model;

public class AuthorizedUser {
    private final String name;
    private final String password;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public AuthorizedUser(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
