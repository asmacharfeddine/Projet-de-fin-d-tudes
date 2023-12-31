package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.DayTakes;
import iss4u.ehr.backoffice.parameterization.prescription.entities.MedicationPart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayTakesRepository extends JpaRepository<DayTakes, Integer> {
    //List<DayTakes> findByMedicationPart_medicationPartKey(Integer medicationPartKey);

}
