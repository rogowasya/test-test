package com.haulmont.testtask.test;

import com.haulmont.testtask.service.DoctorService;
import com.haulmont.testtask.service.PatientService;
import com.haulmont.testtask.service.RecipeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class ApplicationTestsBase extends Assert{

	@Autowired
	protected DoctorService doctorService;
	@Autowired
	protected PatientService patientService;
	@Autowired
	protected RecipeService recipeService;

	@Test
	public void contextLoads() {
	}

}
