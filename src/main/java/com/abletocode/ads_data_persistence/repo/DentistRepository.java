package com.abletocode.ads_data_persistence.repo;

import com.abletocode.ads_data_persistence.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistRepository extends JpaRepository<Dentist, Long> {
}
