package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByUserEmail(String userEmail);

}
