package com.professional.dummy.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

//    MALE, FEMALE;
}
