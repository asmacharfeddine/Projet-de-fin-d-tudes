package iss4u.ehr.backoffice.parameterization.medical_record.repositories;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface VaccinationRepository  extends JpaRepository<Vaccination, Integer> {
}
