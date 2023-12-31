package iss4u.ehr.backoffice.parameterization.prescription.repositories;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Status;
import iss4u.ehr.backoffice.parameterization.prescription.entities.MedicationPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MedicationPartRepository extends JpaRepository<MedicationPart, Integer> {
    List<MedicationPart> findByPrescription_PrescriptionKey(Integer prescriptionKey);
    boolean existsByStatus(Status status);


}
