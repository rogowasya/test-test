package com.haulmont.testtask.model.json;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class PatientJson extends JsonBase {
    private String firstname;
    private String lastname;
    private String patronymic;
    private String phone;

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
