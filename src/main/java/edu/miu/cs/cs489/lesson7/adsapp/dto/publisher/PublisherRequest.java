package edu.miu.cs.cs489.lesson7.adsapp.dto.publisher;

import edu.miu.cs.cs489.lesson7.adsapp.dto.address.AddressRequest;

public record PublisherRequest(
        String name,
        AddressRequest primaryAddress
) {
}
