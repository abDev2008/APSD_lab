package com.abletocode.ads_data_persistence.repo;

import com.abletocode.ads_data_persistence.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
