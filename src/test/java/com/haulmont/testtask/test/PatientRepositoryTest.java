package com.haulmont.testtask.test;

import com.haulmont.testtask.model.Patient;
import org.junit.Test;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public class PatientRepositoryTest extends ApplicationTestsBase{

    @Test
    public void patientRepository(){
        assertTrue(patientService.count()>0);
    }

    @Test
    public void findPatient(){
        Patient patient = patientService.findById(0L);
        assertEquals("Петр", patient.getFirstname());
        assertEquals("Петров", patient.getLastname());
        assertEquals("", patient.getPatronymic());
        assertEquals("8(845)125-54-48", patient.getPhone());
    }

    @Test
    public void addPatient(){
        Patient patient = patientService.addPatientForTest();

        assertTrue(patient.getId()>0);
        assertNotNull(patientService.findById(patient.getId()));
        assertEquals("Герасим", patient.getFirstname());
        assertEquals("Герасимов", patient.getLastname());
        assertEquals("Герасимович", patient.getPatronymic());
        assertEquals("111-11-11", patient.getPhone());
    }

    @Test
    public void updateDoctor(){
        Patient patient = patientService.addPatientForTest();
        patient.setLastname("Березкин");
        patient.setPhone("+7(948)245-14-58");
        patient = patientService.save(patient);

        assertTrue(patient.getId()>0);
        assertNotNull(patientService.findById(patient.getId()));
        assertEquals("Герасим", patient.getFirstname());
        assertEquals("Березкин", patient.getLastname());
        assertEquals("Герасимович", patient.getPatronymic());
        assertEquals("+7(948)245-14-58", patient.getPhone());
    }

    @Test
    public void removePatient(){
        Patient patient = patientService.addPatientForTest();
        assertNotNull(patient);
        patientService.remove(patient);
        assertNull(patientService.findById(patient.getId()));
    }

    @Test
    public void removePatientById(){
        Patient patient = patientService.addPatientForTest();
        assertTrue(patient.getId()>0);
        patientService.removeById(patient.getId());
        assertNull(patientService.findById(patient.getId()));
    }
}
