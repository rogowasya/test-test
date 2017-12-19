package com.haulmont.testtask.model;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public enum PriorityEnum {
    norm("Нормальный"),
    cito("Срочный"),
    statim ("Немедленный");

    private final String description;

    PriorityEnum(String desc){
        this.description = desc;
    }

    public String getDescription() {
        return description;
    }
}
