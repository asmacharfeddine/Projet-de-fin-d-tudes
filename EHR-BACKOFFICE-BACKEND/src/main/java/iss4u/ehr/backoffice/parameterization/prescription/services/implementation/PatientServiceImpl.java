package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.*;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.*;
import iss4u.ehr.backoffice.parameterization.prescription.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Access;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private final PatientRepository patientRepository;
    @Autowired
    private final PrescriptionRepository prescriptionRepository;

    @Autowired
    private final MedicationRepository medicationRepository;
   @Autowired
    private final MedicationPartRepository medicationPartRepository;
    // Initialisation of the repository with the constructor
    public PatientServiceImpl(PatientRepository patientRepository, MedicationRepository medicationRepository, PrescriptionRepository prescriptionRepository, MedicationPartRepository medicationPartRepository) {
        this.patientRepository = patientRepository;
        this.medicationRepository = medicationRepository;
        this.prescriptionRepository = prescriptionRepository;
        this.medicationPartRepository = medicationPartRepository;
    }

    /* ----------------- Methods ---------------------- */

    // Create a patient
    @Override
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get all the patients in the databases
    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Delete a patient by it's id
    @Override
    public ResponseEntity<Patient> deletePatientById(Integer Id) {
        Patient existingPatient = this.patientRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with Medication_Key : " + Id));
        this.patientRepository.delete(existingPatient);
        return ResponseEntity.ok().build();

    }

    // Find a patient by it's Id

    public Patient findPatientById(Integer Id) {
        return patientRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id : " + Id));
    }

// prescriptions method in the patient service impl because they are related to the patient
    public void addPrescriptionToPatient(Integer patientId, Integer medicationId, Prescription prescription) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));

        // Retrieve the existing Medication by its ID
        Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with ID: " + medicationId));


        // Link the prescription to the medication
      //  prescription.setMedication(medication);
        // Link the prescription to the patient
        prescription.setPatient(patient);

        // Add the prescription to the patient's set of prescriptions
        patient.getPrescriptions().add(prescription);

        //prescriptionRepository.save(prescription);
        // Save the patient to update the relationship in the database
        patientRepository.save(patient);
    }

    @Transactional

    public void addPrescriptionToPatientWithMedicationParts(Integer patientId, Prescription prescription) {
        //Patient patient = patientRepository.findPatientByUserKey(patientId);
                //.orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));
        Patient patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));

        // Retrieve the existing Medication by its ID
      /*  Medication medication = medicationRepository.findById(medicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Medication not found with ID: " + medicationId));*/


        // Initialize medicationParts as an empty list if null
        if (prescription.getMedicationParts() == null) {
            prescription.setMedicationParts(new ArrayList<>());
        }


        // Link the prescription to the medication
       // prescription.setMedication(medication);
        // Link the prescription to the patient
        prescription.setStatus(Status.inProgress);
        prescription.setPatient(patient);


        // Loop through the medicationParts and set the prescription for each MedicationPart
        for (MedicationPart medicationPart : prescription.getMedicationParts()) {
            medicationPart.setStatus(Status.inProgress);
            medicationPart.setPrescription(prescription);
        }
// this is added
        for (MedicationPart medicationPart : prescription.getMedicationParts()) {
            if (medicationPart.getDayTakes() == null) {
                medicationPart.setDayTakes(new ArrayList<>());
            }
        }

        // Add the prescription to the patient's set of prescriptions
        patient.getPrescriptions().add(prescription);

        prescriptionRepository.save(prescription);
        // Save the patient to update the relationship in the database
        patientRepository.save(patient);
    }



    public ResponseEntity<Prescription> deletePrescriptionById(Integer user_Key){
        Prescription existingPrescription = this.prescriptionRepository.findById(user_Key)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with prescriptionKey : "+ user_Key));
        this.prescriptionRepository.delete(existingPrescription);
        return ResponseEntity.ok().build();

    }

    public Prescription createPrescription(Prescription prescription) {
        return prescriptionRepository.save(prescription);
    }

    public Patient getById(Integer id) {
        return patientRepository.findById(id).orElse(null);
    }

       public List<Prescription> getAllPrescriptions(){return prescriptionRepository.findAll();}

    /*public void updatePrescriptionStatusIfAllDone(Integer prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));

        List<MedicationPart> medicationParts = prescription.getMedicationParts();

        // Check if all medication parts are in "done" status
        boolean allDone = medicationParts.stream()
                .allMatch(medicationPart -> Status.done==(medicationPart.getStatus()));

        if (allDone) {
            prescription.setStatus(Status.done);
            prescriptionRepository.save(prescription);
        }
    }*/


    /*public void updatePrescriptionStatusIfAllDone(Integer prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));

        List<MedicationPart> medicationParts = prescription.getMedicationParts();

        // Check if all medication parts are in "done" status
        boolean allDone = medicationParts.stream()
                .allMatch(medicationPart -> Status.done.equals(medicationPart.getStatus()));

        // Check if there is only one medication part with "blocked" status
        boolean onlyOneBlocked = medicationParts.stream()
                .filter(medicationPart -> Status.blocked.equals(medicationPart.getStatus()))
                .count() == 1;

        if (allDone) {
            prescription.setStatus(Status.done);
        } else if (onlyOneBlocked) {
            prescription.setStatus(Status.blocked);
        }

        prescriptionRepository.save(prescription);
    }*/


    public void updatePrescriptionStatusIfAllDone(Integer prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with id: " + prescriptionId));

        List<MedicationPart> medicationParts = prescription.getMedicationParts();

        // Check if only one medication part with  "done" status
        boolean anyDone = medicationParts.stream()
                .anyMatch(medicationPart -> Status.done.equals(medicationPart.getStatus()));


        // Check if there is only one medication part with "blocked" status
        boolean onlyOneBlocked = medicationParts.stream()
                .filter(medicationPart -> Status.blocked.equals(medicationPart.getStatus()))
                .count() == 1;

        if (anyDone) {
            prescription.setStatus(Status.done);
        } else if (onlyOneBlocked) {
            prescription.setStatus(Status.blocked);
        }

        prescriptionRepository.save(prescription);
    }




    @Transactional
    public void updatePrescriptionForPatientWithMedicationParts(Integer patientId, Integer prescriptionId, Prescription updatedPrescription) {
        // Retrieve the patient by its ID
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + patientId));

        // Retrieve the existing prescription by its ID
        Prescription existingPrescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription not found with ID: " + prescriptionId));

        // Check if the existing prescription belongs to the patient
        if (!patient.getPrescriptions().contains(existingPrescription)) {
            throw new ResourceNotFoundException("Prescription with ID " + prescriptionId + " does not belong to patient with ID " + patientId);
        }

        // Set the prescription fields from the updated prescription
        existingPrescription.setStatus(updatedPrescription.getStatus());
        existingPrescription.setTreatmentDuration(updatedPrescription.getTreatmentDuration());
        existingPrescription.setDosage(updatedPrescription.getDosage());
        existingPrescription.setSpecialInstructions(updatedPrescription.getSpecialInstructions());
        existingPrescription.setPrescriptionDate(updatedPrescription.getPrescriptionDate());
        existingPrescription.setPrescriptionTime(updatedPrescription.getPrescriptionTime());


        // Set other fields as needed

        // Loop through the medicationParts and update them
        for (MedicationPart updatedMedicationPart : updatedPrescription.getMedicationParts()) {
            MedicationPart existingMedicationPart = medicationPartRepository.findById(updatedMedicationPart.getMedicationPartKey())
                    .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with ID: " + updatedMedicationPart.getMedicationPartKey()));

            // Check if the existing medicationPart belongs to the existing prescription
            if (!existingPrescription.getMedicationParts().contains(existingMedicationPart)) {
                throw new ResourceNotFoundException("MedicationPart with ID " + existingMedicationPart.getMedicationPartKey() +
                        " does not belong to prescription with ID " + prescriptionId);
            }
            // Retrieve the existing dayTakes for the medicationPart
            List<DayTakes> existingDayTakes = existingMedicationPart.getDayTakes();

            // Update the fields of the existing medicationPart
            existingMedicationPart.setStatus(existingMedicationPart.getStatus());
            existingMedicationPart.setPeriod(updatedMedicationPart.getPeriod());
            existingMedicationPart.setTakes(updatedMedicationPart.getTakes());
            existingMedicationPart.setQuantity(updatedMedicationPart.getQuantity());
            existingMedicationPart.setMedication(updatedMedicationPart.getMedication());
            existingMedicationPart.setStartDate(updatedMedicationPart.getStartDate());
            existingMedicationPart.setEndDate(updatedMedicationPart.getEndDate());
            existingMedicationPart.setNotes(updatedMedicationPart.getNotes());
            existingMedicationPart.setTotalCount(existingMedicationPart.getTotalCount());



            // Set other fields as needed
        }

        // Save the updated prescription and medicationParts
        prescriptionRepository.save(existingPrescription);
        // You may need to save each updated medicationPart individually if needed
    }

}
