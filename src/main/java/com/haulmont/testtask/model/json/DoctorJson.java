package com.haulmont.testtask.model.json;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class DoctorJson extends JsonBase{

    private String firstname;
    private String lastname;
    private String patronymic;
    private String specialization;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }
}
