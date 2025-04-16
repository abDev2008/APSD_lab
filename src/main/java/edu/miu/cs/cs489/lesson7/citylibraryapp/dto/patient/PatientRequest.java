package edu.miu.cs.cs489.lesson7.citylibraryapp.dto.patient;

import edu.miu.cs.cs489.lesson7.citylibraryapp.dto.address.AddressRequest;

public record PatientRequest(
        String firstName,
        String lastName,
        AddressRequest primaryAddress
) {}
