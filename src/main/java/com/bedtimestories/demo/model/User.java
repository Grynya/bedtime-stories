package com.bedtimestories.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;
    private boolean enabled;

    public User(Long id, String name, String password, boolean enabled) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

    public User(String name, String password, boolean enabled) {
        this.name = name;
        this.password = password;
        this.enabled = enabled;
    }

}