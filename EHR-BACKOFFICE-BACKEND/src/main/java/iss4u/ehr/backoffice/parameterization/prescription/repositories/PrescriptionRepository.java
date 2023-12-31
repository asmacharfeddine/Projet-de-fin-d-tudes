package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
    Prescription findByPrescriptionKey(Integer prescriptionKey);

}
