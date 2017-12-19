package com.haulmont.testtask.test;

import com.haulmont.testtask.model.Doctor;
import com.haulmont.testtask.model.SpecializationEnum;
import org.junit.Test;

import java.util.List;

/**
 * Created by FilatovNA(rogowasya@gmail.com) on 13.12.17.
 */

public class DoctorRepositoryTest extends ApplicationTestsBase{

    @Test
    public void doctorRepository(){
        assertTrue(doctorService.count()>0);
    }

    @Test
    public void findDoctor(){
        Doctor doctor = doctorService.findById(0L);
        assertEquals("Иван", doctor.getFirstname());
        assertEquals("Иванов", doctor.getLastname());
        assertEquals("", doctor.getPatronymic());
        assertEquals("Терапевт", doctor.getSpecialization().getDescription());
    }

    @Test
    public void findAllDoctor(){
        List<Doctor> doctors = doctorService.findAll();
        assertNotNull(doctors);
        assertTrue(doctors.size()>0);
    }

    @Test
    public void addDoctor(){
        Doctor doctor = doctorService.addDoctorForTest();

        assertTrue(doctor.getId()>0);
        assertNotNull(doctorService.findById(doctor.getId()));
        assertEquals("Василий", doctor.getFirstname());
        assertEquals("Васильев", doctor.getLastname());
        assertEquals("Васильевич", doctor.getPatronymic());
        assertEquals(SpecializationEnum.oculist, doctor.getSpecialization());
    }

    @Test
    public void updateDoctor(){
        Doctor doctor = doctorService.addDoctorForTest();
        doctor.setLastname("Березкин");
        doctor = doctorService.save(doctor);

        assertTrue(doctor.getId()>0);
        assertNotNull(doctorService.findById(doctor.getId()));
        assertEquals("Василий", doctor.getFirstname());
        assertEquals("Березкин", doctor.getLastname());
        assertEquals("Васильевич", doctor.getPatronymic());
        assertEquals(SpecializationEnum.oculist, doctor.getSpecialization());
    }

    @Test
    public void removeDoctor(){
        Doctor doctor = doctorService.addDoctorForTest();
        assertNotNull(doctor);
        doctorService.remove(doctor);
        assertNull(doctorService.findById(doctor.getId()));
    }

    @Test
    public void removeDoctorById(){
        Doctor doctor = doctorService.addDoctorForTest();
        assertTrue(doctor.getId()>0);
        doctorService.removeById(doctor.getId());
        assertNull(doctorService.findById(doctor.getId()));
    }

}
