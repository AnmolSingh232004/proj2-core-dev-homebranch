package org.tasos.proj2.domain.activity;

// package-private because we dont want this to be visible to callers

class ActivitySubType {
    private String title;

    ActivitySubType(){}

    ActivitySubType(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

}

