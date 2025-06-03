package org.tasos.proj2.domain.java8Ttests.optionalTests.classesCompositionNullChecks;

import java.util.Optional;

public class Mobile {
    private long id;
    private String brand;
    private String name;
    private Optional<DisplayFeatures>  displayFeatures;
    // Likewise we can see Memory Features, Camera Features etc.

    public Mobile(long id, String brand, String name,
                  Optional<DisplayFeatures>  displayFeatures){
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.displayFeatures = displayFeatures;
    }

    public long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public Optional<DisplayFeatures>  getDisplayFeatures() {
        return displayFeatures;
    }
}
