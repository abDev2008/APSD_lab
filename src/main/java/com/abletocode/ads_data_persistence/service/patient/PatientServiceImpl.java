package com.abletocode.ads_data_persistence.service.patient;

import com.abletocode.ads_data_persistence.model.Patient;
import com.abletocode.ads_data_persistence.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class PatientServiceImpl implements  PatientService{
    private final PatientRepository patientRepository;

    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
