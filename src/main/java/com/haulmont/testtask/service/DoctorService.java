package com.haulmont.testtask.service;

import com.haulmont.testtask.dao.DoctorRepository;
import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.SpecializationEnum;
import com.haulmont.testtask.model.json.DoctorJson;
import com.haulmont.testtask.service.util.JsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@Service
public class DoctorService extends ServiceBase<Doctor, DoctorRepository> {


    public Doctor updateDoctor(Doctor doctor, String firstname, String lastname,
                               String patronymic, SpecializationEnum specialization) {

        doctor.setFirstname(firstname);
        doctor.setLastname(lastname);
        doctor.setPatronymic(patronymic);
        doctor.setSpecialization(specialization);

        return save(doctor);
    }

    public Doctor addDoctor(String firstname, String lastname, String patronymic,
                            SpecializationEnum specialization) {
        Doctor doctor = new Doctor();
        return updateDoctor(doctor,firstname,lastname,patronymic,specialization);
    }

    public Doctor addDoctorForTest() {
        return addDoctor(
                "Василий",
                "Васильев",
                "Васильевич",
                SpecializationEnum.oculist
        );
    }

    @Transactional
    public List<DoctorJson> findAllDoctors() {
        return findAll().stream()
                .map(JsonUtil::doctorToJson)
                .collect(Collectors.toList());
    }

}
