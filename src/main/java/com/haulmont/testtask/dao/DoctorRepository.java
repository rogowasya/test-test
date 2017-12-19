package com.haulmont.testtask.dao;

import com.haulmont.testtask.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 12.12.17.
 */

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

    Page<Doctor> findAllBy(Pageable pageable);

}
