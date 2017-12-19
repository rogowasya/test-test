package com.haulmont.testtask.model.json;

import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.PriorityEnum;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 19.12.17.
 */

public class RecipeFilterJson {
    private String descript;
    private Patient patient;
    private PriorityEnum priority;

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return descript;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

    public PriorityEnum getPriority() {
        return priority;
    }
}
