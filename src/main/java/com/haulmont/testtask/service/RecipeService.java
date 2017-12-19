package com.haulmont.testtask.service;

import com.haulmont.testtask.dao.RecipeRepository;
import com.haulmont.testtask.model.*;
import com.haulmont.testtask.model.json.RecipeJson;
import com.haulmont.testtask.service.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.isEmpty;


/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@Service
public class RecipeService extends ServiceBase<Recipe, RecipeRepository>{

    public Recipe addRecipeForTest(Doctor doctor, Patient patient) {
        return addRecipe(
                "Тестовый рецепт",
                new Date(),
                doctor,
                patient,
                PriorityEnum.cito
        );
    }

    public Recipe addRecipe(String description, Date created, Doctor doctor,
                            Patient patient, PriorityEnum priority) {
        Recipe recipe = new Recipe();
        recipe.setDescription(description);
        recipe.setCreated(created);
        recipe.setDoctor(doctor);
        recipe.setPatient(patient);
        recipe.setPriority(priority);
        repository.save(recipe);
        return recipe;
    }

    @Transactional
    public List<RecipeJson> findAllRecipes() {
        return findAll().stream()
                .map(JsonUtil::recipeToJson)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<RecipeJson> findRecipesByFilters(String desc, Patient patient, PriorityEnum priority) {
        int idx = 0;
        idx += !isEmpty(desc)?100:0;
        idx += patient!=null?10:0;
        idx += priority!=null?1:0;

        desc = !isEmpty(desc)?"%"+desc+"%":"";

        List<Recipe> recipes = new ArrayList<>();
        switch (idx) {
            case 111:
                recipes.addAll(repository
                        .findRecipesByDescriptionLikeAndPatientAndPriority(desc, patient, priority)
                );
                break;
            case 101:
                recipes.addAll(repository
                        .findRecipesByDescriptionLikeAndPriority(desc, priority)
                );
                break;
            case 110:
                recipes.addAll(repository
                        .findRecipesByDescriptionLikeAndPatient(desc, patient)
                );
                break;
            case 11:
                recipes.addAll(repository
                        .findRecipesByPatientAndPriority(patient, priority)
                );
                break;
            case 100:
                recipes.addAll(repository
                        .findRecipesByDescriptionLike(desc)
                );
                break;
            case 10:
                recipes.addAll(repository
                        .findRecipesByPatient(patient)
                );
                break;
            case 1:
                recipes.addAll(repository
                        .findRecipesByPriority(priority)
                );
                break;
            default:
                recipes.addAll(findAll());
                break;
        }

        return recipes.stream()
                .map(JsonUtil::recipeToJson)
                .collect(Collectors.toList());
    }
}
