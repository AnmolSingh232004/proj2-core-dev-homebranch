package org.tasos.proj2.domain.activity;

// package-private because we dont want this to be visible to callers

public class ActivityType {

    private Long id;
    private String title;

    private String description;

    ActivityType(){}

    ActivityType(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

}

