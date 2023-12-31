package iss4u.ehr.backoffice.parameterization.prescription.services;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Medication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface MedicationService {
    Medication createMedication(Medication medication);
    List<Medication> getAllMedications();
     Medication updateMedication(@RequestBody Medication medication, Integer Id);
    ResponseEntity<Medication> deleteMedicationById(Integer Id);
    Medication getMedicationById(@PathVariable Integer id);
   // Medication findMedicationById(Integer Id);
    Medication findMedicationByName(String name);
    Medication findMedicationByCode(String code);
    List<Medication>  findMedicationByDosageForm(String dosageForm);
    List<Medication> findMedicationByForce(Float force);
}
