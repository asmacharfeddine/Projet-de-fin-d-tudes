package iss4u.ehr.backoffice.parameterization.prescription.services;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Medication;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {

    public Patient createPatient(Patient patient);
    public List<Patient> getAllPatients();

    public ResponseEntity<Patient> deletePatientById(Integer Id);
    public Patient findPatientById(Integer Id);


}
