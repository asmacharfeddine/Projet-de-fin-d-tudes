package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// this will provide crud operation for our medication entity
@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    //@Query(value = "SELECT * FROM medications m WHERE m.name = :name ", nativeQuery = true)

    Medication findMedicationByName(String name );

    Medication findMedicationByCode(String code);
    List<Medication> findMedicationByDosageForm(String dosageForm);

   /* @Query(value = "SELECT * FROM medications m WHERE m.force = :force", nativeQuery = true)*/
    List<Medication> findMedicationByForce(Float force);



}


