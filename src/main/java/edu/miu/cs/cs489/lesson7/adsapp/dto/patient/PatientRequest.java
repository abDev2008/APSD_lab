package edu.miu.cs.cs489.lesson7.adsapp.dto.patient;

import edu.miu.cs.cs489.lesson7.adsapp.dto.address.AddressRequest;

public record PatientRequest(
        String firstName,
        String lastName,
        AddressRequest primaryAddress
) {}
