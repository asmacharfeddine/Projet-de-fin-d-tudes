package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.*;

import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.MedicationPartRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iss4u.ehr.backoffice.parameterization.prescription.entities.MedicationPart;

import java.util.ArrayList;
import java.util.List;
@Service
public class MedicationPartServiceImpl {
    private final MedicationPartRepository medicationPartRepository;
    private final PrescriptionServiceImpl prescriptionService;

    private final PatientServiceImpl patientService;
    private final PrescriptionRepository prescriptionRepository;
    @Autowired
    public MedicationPartServiceImpl(MedicationPartRepository medicationPartRepository,
                                     PrescriptionServiceImpl prescriptionService,
                                     PatientServiceImpl patientService,
                                     PrescriptionRepository prescriptionRepository) {
        this.medicationPartRepository = medicationPartRepository;
        this.prescriptionService = prescriptionService;
        this.patientService = patientService;
        this.prescriptionRepository = prescriptionRepository;
    }


    public List<MedicationPart> getMedicationPartsByPrescriptionKey(Integer prescriptionKey) {
        return medicationPartRepository.findByPrescription_PrescriptionKey(prescriptionKey);
    }



/* -------------------- DayTakes Methods -------------------------*/
   public void addDayTakeToMedicationPart(Integer medicationPartId, DayTakes dayTake) {
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartId)
                .orElseThrow(() -> new ResourceNotFoundException("medicationPart not found with ID: " + medicationPartId));




        if (medicationPart.getDayTakes() == null) {
            medicationPart.setDayTakes(new ArrayList<>());
        }



        //prescription.setStatus(Status.inProgress);
        // link the dayTake to the medicationPart
        dayTake.setMedicationPart(medicationPart);


        // Loop through the DayTakes and set the medicationPart for each DayTake
        for (DayTakes dt: medicationPart.getDayTakes()) {
            dt.setMedicationPart(medicationPart);
        }
        // Add the dayTake to the medicationPart's set of dauTake
        medicationPart.getDayTakes().add(dayTake);
        medicationPart.incrementTotalCount();
       if (medicationPart.getPeriod() != null) {

           if (medicationPart.getTotalCount()==medicationPart.getTakes()*medicationPart.getPeriod()){
            medicationPart.setStatus(Status.done);

            // Get the prescription key and update the prescription status
            Integer prescriptionKey = medicationPart.getPrescriptionKeyForMedicationPart();
            if (prescriptionKey != null) {
                Prescription prescription = this.prescriptionService.getPrescriptionByPrescriptionKey(prescriptionKey);
                this.patientService.updatePrescriptionStatusIfAllDone(prescription.getPrescriptionKey());
            }
        }
       }

        medicationPartRepository.save(medicationPart);

    }
//my methodddd to update
    /*public void updateMedicationPartStatus(Integer medicationPartKey, Status newStatus) {
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartKey)
                .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with ID: " + medicationPartKey));


        // Check if the new status is "blocked"
        if (newStatus == Status.blocked) {
            // Check if there is any MedicationPart with status "blocked"
            boolean isAnyMedicationPartBlocked = medicationPartRepository.existsByStatus(Status.blocked);

            // If any MedicationPart is blocked, update the associated Prescription status to "blocked"
            if (isAnyMedicationPartBlocked) {
                Integer prescriptionKey = medicationPart.getPrescriptionKeyForMedicationPart();
                if (prescriptionKey != null) {
                    Prescription prescription = prescriptionService.getPrescriptionByPrescriptionKey(prescriptionKey);
                    prescription.setStatus(Status.blocked);
                    prescriptionRepository.save(prescription);
                }
            }

        }


        // Update the status
        medicationPart.setStatus(newStatus);
        medicationPartRepository.save(medicationPart);
    }*/



    public void updateMedicationPartStatus(Integer medicationPartKey, Status newStatus) {
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartKey)
                .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with ID: " + medicationPartKey));

        // Check if the new status is "blocked"
        if (newStatus == Status.blocked) {
            // Check if there is any MedicationPart with status "blocked"
            boolean isAnyMedicationPartBlocked = medicationPartRepository.existsByStatus(Status.blocked);

            // If any MedicationPart is blocked, update the associated Prescription status to "blocked"
            if (isAnyMedicationPartBlocked) {
                Integer prescriptionKey = medicationPart.getPrescriptionKeyForMedicationPart();
                if (prescriptionKey != null) {
                    Prescription prescription = prescriptionService.getPrescriptionByPrescriptionKey(prescriptionKey);
                    prescription.setStatus(Status.blocked);
                    prescriptionRepository.save(prescription);
                }
            }
        } else if (newStatus == Status.done) {
            // Check if there is any MedicationPart with status "done"
            boolean isAnyMedicationPartDone = medicationPartRepository.existsByStatus(Status.done);

            // If any MedicationPart is done, update the associated Prescription status to "done"
            if (isAnyMedicationPartDone) {
                Integer prescriptionKey = medicationPart.getPrescriptionKeyForMedicationPart();
                if (prescriptionKey != null) {
                    Prescription prescription = prescriptionService.getPrescriptionByPrescriptionKey(prescriptionKey);
                    prescription.setStatus(Status.done);
                    prescriptionRepository.save(prescription);
                }
            }
        }

        // Update the status of the current MedicationPart
        medicationPart.setStatus(newStatus);
        medicationPartRepository.save(medicationPart);
    }


        public MedicationPart getMedicationPartById(Integer medicationPartId) {
        return medicationPartRepository.findById(medicationPartId)
                .orElseThrow(() -> new RuntimeException("MedicationPart with ID " + medicationPartId + " not found"));
    }
    }



