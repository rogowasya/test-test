package com.haulmont.testtask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@Component
public class ServiceContainer {

    @Autowired
    protected DoctorService doctorService;
    @Autowired
    protected PatientService patientService;
    @Autowired
    protected RecipeService recipeService;

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public RecipeService getRecipeService() {
        return recipeService;
    }
}
