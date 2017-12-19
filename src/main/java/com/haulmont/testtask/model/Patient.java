package com.haulmont.testtask.model;

import javax.persistence.*;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@Entity
public class Patient extends EmptyEntity {

    private String firstname;
    private String lastname;
    private String patronymic;
    private String phone;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recipe> recipes;

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public String getFio() {
        return new StringBuilder()
                .append(lastname).append(" ")
                .append(firstname.substring(0,1).toUpperCase()).append(" ")
                .append(!isEmpty(patronymic)?patronymic.substring(0,1).toUpperCase():"")
                .toString();
    }
}
