package org.tasos.proj2.springrestservices.dto.activity.extras;

public class CreateActivityDTO {

    private String actTypeId;
    private String newActName;
    private String selectedSubType;
    private String newSubType;

    private String userName;

    public String getActTypeId() {
        return actTypeId;
    }

    public void setActTypeId(String actTypeId) {
        this.actTypeId = actTypeId;
    }

    public String getNewActName() {
        return newActName;
    }

    public void setNewActName(String newActName) {
        this.newActName = newActName;
    }

    public String getSelectedSubType() {
        return selectedSubType;
    }

    public void setSelectedSubType(String selectedSubType) {
        this.selectedSubType = selectedSubType;
    }

    public String getNewSubType() {
        return newSubType;
    }

    public void setNewSubType(String newSubType) {
        this.newSubType = newSubType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
