package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Patient;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 12.12.17.
 */

public interface PatientRepository extends CrudRepository<Patient, Long> {
}
