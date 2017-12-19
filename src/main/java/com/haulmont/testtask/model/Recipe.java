package com.haulmont.testtask.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@Entity
public class Recipe extends EmptyEntity {

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    private Date created;

    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    public PriorityEnum getPriority() {
        return priority;
    }
    public void setPriority(PriorityEnum priority) {
        this.priority = priority;
    }

}
