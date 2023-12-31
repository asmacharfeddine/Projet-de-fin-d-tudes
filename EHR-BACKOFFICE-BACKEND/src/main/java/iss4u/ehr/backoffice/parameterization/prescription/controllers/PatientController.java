package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Medication;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Prescription;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.PrescriptionServiceException;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.MedicationServiceImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Set;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    PatientServiceImpl patientService;

    // Create a patient
    @PostMapping("/createPatient")
    public Patient createPatient (@RequestBody Patient patient){
        return patientService.createPatient(patient);}
    @GetMapping("/patientsList")
    public List<Patient> getAllPatients(){return patientService.getAllPatients();}



    @DeleteMapping("/deletePatient/{id}")
    public ResponseEntity<Patient> deletePatientById(@PathVariable("id") Integer user_Key) {
        return this.patientService.deletePatientById(user_Key);
    }

    @GetMapping("/id/{id}")
    public Patient getPatientById(@PathVariable Integer id){
        return patientService.findPatientById(id);
    }


    @PostMapping("/{patientId}/addPrescription")
    public  ResponseEntity<?> addPrescriptionToPatient      (
            @PathVariable Integer patientId,
            @RequestParam Integer medicationId, // Medication ID
            @RequestBody Prescription prescription){

            patientService.addPrescriptionToPatient(patientId,medicationId, prescription);
            return ResponseEntity.ok("Prescription added to the patient successfullyyyyyyyyyyy.");
}

// get the prescriptions of a patient
    @GetMapping("/{id}/prescriptions")
    public Set<Prescription> getAllPrescriptionsForPatient(@PathVariable Integer id) {
        Patient patient = patientService.getById(id);

        if (patient != null) {
            return patient.getAllPrescriptions();
        } else {
            return null;
        }
    }

    @PostMapping("/createPrescription")
    public Prescription createPrescription (@RequestBody Prescription prescription){
        return patientService.createPrescription(prescription);}

    @GetMapping("/getAllPrescriptions")
    public List<Prescription> getAllPrescriptions() {
        return patientService.getAllPrescriptions();
    }

    @PostMapping("/{patientId}/addPrescriptionWithMedicationParts")
    public ResponseEntity<?> addPrescriptionToPatientWithMedicationParts(
            @PathVariable Integer patientId,
           // @RequestParam Integer medicationId, // Medication ID
            @RequestBody Prescription prescription) {
//Prescription addedPrescription =
        try {
            patientService.addPrescriptionToPatientWithMedicationParts(patientId, prescription);
            return ResponseEntity.ok("Prescription added to the patient successfully.");
        } catch (PrescriptionServiceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }


    @DeleteMapping("/prescription/{id}")
    public ResponseEntity<Prescription> deletePrescriptionById(@PathVariable("id") Integer prescriptionKey){
        return this.patientService.deletePrescriptionById(prescriptionKey);
    }

    @PutMapping("/updateStatusIfAllDone/{prescriptionId}")
    public void updatePrescriptionStatus(@PathVariable  Integer prescriptionId) {
        patientService.updatePrescriptionStatusIfAllDone(prescriptionId);
    }


    @PutMapping("/update/{patientId}/{prescriptionId}")
    public ResponseEntity<String> updatePrescriptionForPatientWithMedicationParts(
            @PathVariable Integer patientId,
            @PathVariable Integer prescriptionId,
            @RequestBody Prescription updatedPrescription) {
        try {
            patientService.updatePrescriptionForPatientWithMedicationParts(patientId, prescriptionId, updatedPrescription);
            return new ResponseEntity<>("Prescription updated successfully", HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the prescription", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

