package com.haulmont.testtask.service.util;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.EmptyEntity;
import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.Recipe;
import com.haulmont.testtask.model.json.DoctorJson;
import com.haulmont.testtask.model.json.PatientJson;
import com.haulmont.testtask.model.json.RecipeJson;
import com.haulmont.testtask.service.ServiceContainer;

import java.util.Date;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 18.12.17.
 */

public class JsonUtil {

    public static RecipeJson recipeToJson(Recipe recipe) {
        RecipeJson json = new RecipeJson();
        json.setId(recipe.getId());
        json.setDescription(recipe.getDescription());
        json.setPatientId(recipe.getPatient().getId());
        json.setPatient(recipe.getPatient().getFio());
        json.setDoctorId(recipe.getDoctor().getId());
        json.setDoctor(recipe.getDoctor().getFio());
        json.setCreated(DateUtil.convertDDMMYY(recipe.getCreated()));
        json.setPriority(recipe.getPriority().getDescription());
        return json;
    }

    public static Recipe fillRecipe(ServiceContainer services, RecipeJson json) {
        if (json == null) return null;

        Doctor doctor = null;
        Patient patient = null;
        if (json.getId()>0){
            doctor = services.getDoctorService().findById(json.getDoctorId());
            patient = services.getPatientService().findById(json.getPatientId());
        }

        Recipe recipe = services.getRecipeService().findById(json.getId());
        if (doctor==null||patient==null||recipe == null) {
            recipe = new Recipe();
        }

        recipe.setDescription(json.getDescription());
        recipe.setPatient(patient);
        recipe.setDoctor(doctor);
        recipe.setPriority(json.getPriority()!=null
                ?EnumUtil.priorityByDesc(json.getPriority())
                :null
        );
        if (recipe.getCreated()==null)
            recipe.setCreated(new Date());
        return recipe;
    }

    public static DoctorJson doctorToJson(Doctor doctor) {
        DoctorJson json = new DoctorJson();
        json.setId(doctor.getId());
        json.setFirstname(doctor.getFirstname());
        json.setLastname(doctor.getLastname());
        json.setPatronymic(doctor.getPatronymic());
        json.setSpecialization(doctor.getSpecialization()!=null
                ?doctor.getSpecialization().getDescription()
                :null
        );
        return json;
    }

    public static Doctor fillDoctor(ServiceContainer services, DoctorJson json) {
        if (json == null) return null;

        Doctor doctor = services.getDoctorService().findById(json.getId());
        if (doctor == null) doctor = new Doctor();

        doctor.setFirstname(json.getFirstname());
        doctor.setLastname(json.getLastname());
        doctor.setPatronymic(doctor.getPatronymic());
        doctor.setSpecialization(json.getSpecialization()!=null
                ?EnumUtil.specializationByDesc(json.getSpecialization())
                :null
        );
        return doctor;
    }

    public static PatientJson patientToJson(Patient patient) {
        PatientJson json = new PatientJson();
        json.setId(patient.getId());
        json.setFirstname(patient.getFirstname());
        json.setLastname(patient.getLastname());
        json.setPatronymic(patient.getPatronymic());
        json.setPhone(patient.getPhone());
        return json;
    }

    public static <T extends EmptyEntity> Patient fillPatient(ServiceContainer services, PatientJson json) {
        if (json == null) return null;

        Patient patient = services.getPatientService().findById(json.getId());
        if (patient == null) patient = new Patient();

        patient.setFirstname(json.getFirstname());
        patient.setLastname(json.getLastname());
        patient.setPatronymic(patient.getPatronymic());
        patient.setPhone(patient.getPhone());
        return patient;
    }
}
