package com.abletocode.ads_data_persistence.service.dentist;


import com.abletocode.ads_data_persistence.model.Dentist;
import com.abletocode.ads_data_persistence.repo.DentistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DentistServiceImpl implements DentistService{

    private final DentistRepository dentistRepository; ;

    @Override
    public Dentist createDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }
}
