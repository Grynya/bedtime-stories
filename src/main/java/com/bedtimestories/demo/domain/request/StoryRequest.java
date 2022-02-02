package com.bedtimestories.demo.domain.request;

public class StoryRequest {
    private String name;
    private String path;
    private String description;

    public StoryRequest(String name, String path, String description) {
        this.name = name;
        this.path = path;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getDescription(){return description;}
}
