package edu.miu.cs.cs489.lesson7.adsapp.dto.address;

public record AddressRequest(
        String street,
        String city,
        String state,
        String zipCode
    ) {
}
