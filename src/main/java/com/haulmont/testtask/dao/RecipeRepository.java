package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.PriorityEnum;
import com.haulmont.testtask.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 12.12.17.
 */

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findRecipesByDescriptionLikeAndPatientAndPriority(String desc, Patient patientId, PriorityEnum priority);
    List<Recipe> findRecipesByDescriptionLikeAndPatient(String desc, Patient patientId);
    List<Recipe> findRecipesByDescriptionLikeAndPriority(String desc, PriorityEnum priority);
    List<Recipe> findRecipesByPatientAndPriority(Patient patientId, PriorityEnum priority);
    List<Recipe> findRecipesByDescriptionLike(String desc);
    List<Recipe> findRecipesByPatient(Patient patientId);
    List<Recipe> findRecipesByPriority(PriorityEnum priority);
}
