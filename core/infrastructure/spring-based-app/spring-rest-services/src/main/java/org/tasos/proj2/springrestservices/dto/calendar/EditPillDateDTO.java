package org.tasos.proj2.springrestservices.dto.calendar;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EditPillDateDTO implements Serializable {
    String actType;
    String origDate;
    String targetNewDate;

    public String getActType() {
        return actType;
    }

    public void setActType(String actType) {
        this.actType = actType;
    }

    public String getOrigDate() {
        return origDate;
    }

    public void setOrigDate(String origDate) {
        this.origDate = origDate;
    }

    public String getTargetNewDate() {
        return targetNewDate;
    }

    public void setTargetNewDate(String targetNewDate) {
        this.targetNewDate = targetNewDate;
    }
}
