package com.haulmont.testtask.test;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.PriorityEnum;
import com.haulmont.testtask.model.Recipe;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public class RecipeRepositoryTest extends ApplicationTestsBase{

    private Doctor doctor;
    private Patient patient;

    @Before
    public void setUp() throws Exception {
        doctor = doctorService.addDoctorForTest();
        assertNotNull(doctor);
        patient = patientService.addPatientForTest();
        assertNotNull(patient);
    }

    @Test
    public void recipeRepository(){
        assertTrue(recipeService.count()>0);
    }

    @Test
    public void findRecipe(){
        Recipe recipe = recipeService.findById(0L);
        assertNotNull(recipe);
        assertEquals("Vitamin C", recipe.getDescription());
        assertEquals("Петр", recipe.getPatient().getFirstname());
        assertEquals("Иван", recipe.getDoctor().getFirstname());
        assertEquals(PriorityEnum.norm, recipe.getPriority());
    }

    @Test
    public void addRecipe(){
        Recipe recipe = recipeService.addRecipeForTest(doctor, patient);
        assertNotNull(recipe);
        assertTrue(recipe.getId()>0);
        assertEquals("Тестовый рецепт", recipe.getDescription());
        assertEquals("Герасим", recipe.getPatient().getFirstname());
        assertEquals("Василий", recipe.getDoctor().getFirstname());
        assertEquals(PriorityEnum.cito, recipe.getPriority());
    }

    @Test
    public void removeRecipe(){
        Recipe recipe = recipeService.addRecipeForTest(doctor, patient);;
        assertNotNull(recipe);
        recipeService.remove(recipe);
        assertNull(recipeService.findById(recipe.getId()));
    }

    @Test
    public void removeRecipeById(){
        Recipe recipe = recipeService.addRecipeForTest(doctor, patient);
        assertTrue(recipe.getId()>0);
        recipeService.removeById(recipe.getId());
        assertNull(recipeService.findById(recipe.getId()));
    }

    @Test
    public void notRemoveDoctorWithRecipes(){
        Recipe recipe = recipeService.addRecipeForTest(doctor, patient);
        assertTrue(recipe.getId()>0);
        doctorService.remove(doctor);
        assertNotNull(doctor);
    }

    @Test
    public void notRemovePatientWithRecipes(){
        Recipe recipe = recipeService.addRecipeForTest(doctor, patient);
        assertTrue(recipe.getId()>0);
        patientService.remove(patient);
        assertNotNull(patient);
    }

}
