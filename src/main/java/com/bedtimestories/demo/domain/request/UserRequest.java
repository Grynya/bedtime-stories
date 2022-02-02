package com.bedtimestories.demo.domain.request;

public class UserRequest {
   private String name;
   private String password;

    public UserRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
