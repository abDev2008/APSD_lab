package edu.miu.cs.cs489.lesson7.citylibraryapp.repository;

import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> findPatientsByFirstNameContainingOrLastNameContaining(String firstName, String lastName);
}
