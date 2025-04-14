package com.abletocode.ads_data_persistence.service.surgery;

import com.abletocode.ads_data_persistence.model.Surgery;
import com.abletocode.ads_data_persistence.repo.SurgeryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SurgeryServiceImpl implements SurgeryService{

    private final SurgeryRepository surgeryRepository;
    @Override
    public Surgery createSurgery(Surgery surgery) {
        return surgeryRepository.save(surgery);
    }
}
