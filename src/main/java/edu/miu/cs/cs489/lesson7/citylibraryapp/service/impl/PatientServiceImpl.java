package edu.miu.cs.cs489.lesson7.citylibraryapp.service.impl;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.address.AddressResponse;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientRequest;
import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient.PatientResponse;
import edu.miu.cs.cs489.lesson7.citylibraryapp.exception.PatientNotFoundException;
import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Address;
import edu.miu.cs.cs489.lesson7.citylibraryapp.model.Patient;
import edu.miu.cs.cs489.lesson7.citylibraryapp.repository.PatientRepository;
import edu.miu.cs.cs489.lesson7.citylibraryapp.service.PatientService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAll(Sort.by("lastName"))
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public PatientResponse getPatientById(Integer patientId) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));
        return mapToResponse(patient);
    }


    @Override
    public PatientResponse addNewPatient(PatientRequest patientRequest) {
        var newPatient = new Patient(null,
                patientRequest.firstName(),
                patientRequest.lastName(),
                new Address(null,
                        patientRequest.primaryAddress().street(),
                        patientRequest.primaryAddress().city(),
                        patientRequest.primaryAddress().state(),
                        patientRequest.primaryAddress().zipCode()));
        return mapToResponse(patientRepository.save(newPatient));
    }

    @Override
    public PatientResponse updatePatient(Integer patientId, PatientRequest patientRequest) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient with ID " + patientId + " not found"));

        patient.setFirstName(patientRequest.firstName());
        patient.setLastName(patientRequest.lastName());

        if (patient.getPrimaryAddress() != null) {
            var address = patient.getPrimaryAddress();
            address.setStreet(patientRequest.primaryAddress().street());
            address.setCity(patientRequest.primaryAddress().city());
            address.setState(patientRequest.primaryAddress().state());
            address.setZipCode(patientRequest.primaryAddress().zipCode());
        } else {
            patient.setPrimaryAddress(new Address(null,
                    patientRequest.primaryAddress().street(),
                    patientRequest.primaryAddress().city(),
                    patientRequest.primaryAddress().state(),
                    patientRequest.primaryAddress().zipCode()));
        }
        return mapToResponse(patientRepository.save(patient));
    }


    @Override
    public void deletePatient(Integer patientId) {
        if (!patientRepository.existsById(patientId)) {
            throw new PatientNotFoundException("Patient with ID " + patientId + " not found");
        }
        patientRepository.deleteById(patientId);
    }


    @Override
    public List<PatientResponse> searchPatients(String searchString) {
        return patientRepository
                .findPatientsByFirstNameContainingOrLastNameContaining(searchString, searchString)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private PatientResponse mapToResponse(Patient p) {
        return new PatientResponse(
                p.getPatientId(),
                p.getFirstName(),
                p.getLastName(),
                new AddressResponse(
                        p.getPrimaryAddress().getAddressId(),
                        p.getPrimaryAddress().getStreet(),
                        p.getPrimaryAddress().getCity(),
                        p.getPrimaryAddress().getState(),
                        p.getPrimaryAddress().getZipCode()
                )
        );
    }
}
