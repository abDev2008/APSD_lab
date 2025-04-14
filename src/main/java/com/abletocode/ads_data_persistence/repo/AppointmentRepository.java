package com.abletocode.ads_data_persistence.repo;

import com.abletocode.ads_data_persistence.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
