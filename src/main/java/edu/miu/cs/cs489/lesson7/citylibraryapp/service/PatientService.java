package edu.miu.cs.cs489.lesson7.citylibraryapp.service;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientResponse;

import java.util.List;

public interface PatientService {
    List<PatientResponse> getAllPatients();
    PatientResponse getPatientById(Integer patientId);
    PatientResponse addNewPatient(PatientRequest patientRequest);
    PatientResponse updatePatient(Integer patientId, PatientRequest patientRequest);
    void deletePatient(Integer patientId);
    List<PatientResponse> searchPatients(String searchString);
}
