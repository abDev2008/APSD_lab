package edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.address.AddressResponse;

public record PatientResponse(
        Integer patientId,
        String firstName,
        String lastName,
        AddressResponse primaryAddress
) {}
