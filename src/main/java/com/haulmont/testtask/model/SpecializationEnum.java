package com.haulmont.testtask.model;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public enum SpecializationEnum {
    therapist("Терапевт"),
    oculist("Окулист"),
    endocrinologist("Эндокринолог"),
    surgeon("Хирург");

    private final String description;

    SpecializationEnum(String desc){
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }

    public String getId() {
        return getDescription();
    }
}
