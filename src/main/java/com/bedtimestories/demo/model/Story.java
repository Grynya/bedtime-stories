package com.bedtimestories.demo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class Story {

    private Long id;
    private String name;
    private int rating;
    private String description;
    private String path;

    public Story(String name, int rating, String path, String description) {
        this.name = name;
        this.rating = rating;
        this.path = path;
        this.description = description;
    }
    public Story(Long id, String name, int rating, String path, String description) {
        this.id=id;
        this.name = name;
        this.rating = rating;
        this.path = path;
        this.description = description;
    }
}
