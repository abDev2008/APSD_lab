package edu.miu.cs.cs489.lesson7.adsapp.dto.publisher;

import edu.miu.cs.cs489.lesson7.adsapp.dto.address.AddressResponse;

public record PublisherResponse(
        Integer publisherId,
        String name,
        AddressResponse primaryAddress
        ) {
}
