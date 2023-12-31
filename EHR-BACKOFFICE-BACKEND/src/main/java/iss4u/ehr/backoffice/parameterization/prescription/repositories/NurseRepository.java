package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Doctor;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NurseRepository extends JpaRepository<Nurse, Integer> {
    Nurse findByUserEmail(String userEmail);

}
