package org.tasos.proj2.springrestservices.dto.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityTypeCalendarDisplayDTO implements Serializable {

    private String title;
    private LocalDate start;

    private String color;

    public ActivityTypeCalendarDisplayDTO() {
    }

    public ActivityTypeCalendarDisplayDTO(String title, LocalDate date) {                      
        this(title, date, "#A020F0");
        this.title = title;
        this.start = date;
    }

    public ActivityTypeCalendarDisplayDTO(String title, LocalDate date, String color) {
        this.title = title;
        this.start = date;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityTypeCalendarDisplayDTO that = (ActivityTypeCalendarDisplayDTO) o;
        return
                Objects.equals(title, that.title) &&
                        Objects.equals(start, that.start);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, start);
    }

}