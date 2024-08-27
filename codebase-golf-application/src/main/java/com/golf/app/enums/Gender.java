package com.golf.app.enums;

public enum Gender {

    MALE("male"),
    FEMALE("female");

    private final String genderName;

    Gender(String genderName) {
        this.genderName = genderName;
    }

    public String getGenderName() {
        return genderName;
    }
}
