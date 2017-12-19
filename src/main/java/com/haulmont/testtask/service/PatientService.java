package com.haulmont.testtask.service;

import com.haulmont.testtask.dao.PatientRepository;
import com.haulmont.testtask.model.Patient;
import com.haulmont.testtask.model.json.PatientJson;
import com.haulmont.testtask.service.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

@Service
public class PatientService  extends ServiceBase<Patient, PatientRepository>{

    public Patient addPatient(String firstname, String lastname, String patronymic, String phone) {
        Patient patient = new Patient();
        patient.setFirstname(firstname);
        patient.setLastname(lastname);
        patient.setPatronymic(patronymic);
        patient.setPhone(phone);
        repository.save(patient);
        return patient;
    }

    public Patient addPatientForTest() {
        return addPatient(
                "Герасим",
                "Герасимов",
                "Герасимович",
                "111-11-11"
        );
    }

    public List<PatientJson> findAllPatient() {
        return findAll().stream()
                .map(JsonUtil::patientToJson)
                .collect(Collectors.toList());
    }

}
