package edu.miu.cs.cs489.lesson7.adsapp.dto.address;

import edu.miu.cs.cs489.lesson7.adsapp.dto.publisher.PublisherResponse2;

public record AddressResponse2(
        Integer addressId,
        String street,
        String city,
        String state,
        String zipCode,
        PublisherResponse2 publisher
) {
}
