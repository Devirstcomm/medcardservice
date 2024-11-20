package com.example.medcardservice.model.enums;

import com.example.medcardservice.exception.PatientGenderNotExistException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    MALE("мужчина"),
    FEMALE("женщина");

    private final String russianName;

    Gender(String russianName) {
        this.russianName = russianName;
    }

    @JsonValue
    public String getDisplayName() {
        return russianName;
    }

    @JsonCreator
    public static Gender fromRussianName(String name) {
        for (Gender gender : Gender.values()) {
            if (gender.russianName.equalsIgnoreCase(name)) {
                return gender;
            }
        }
        throw new PatientGenderNotExistException();
    }

    @Override
    public String toString() {
        return russianName;
    }
}
