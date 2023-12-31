package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.DayTakes;
import iss4u.ehr.backoffice.parameterization.prescription.entities.MedicationPart;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Prescription;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Status;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.MedicationPartRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.MedicationPartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication-parts")
public class MedicationPartController {
    private final MedicationPartRepository medicationPartRepository;
    private final MedicationPartServiceImpl medicationPartService;

    @Autowired
    public MedicationPartController(MedicationPartRepository medicationPartRepository ,MedicationPartServiceImpl medicationPartService) {
        this.medicationPartRepository=medicationPartRepository;
        this.medicationPartService = medicationPartService;

    }


    @GetMapping("/prescription/{prescriptionKey}")
    public List<MedicationPart> getMedicationPartsForPrescription(@PathVariable Integer prescriptionKey) {
        return medicationPartService.getMedicationPartsByPrescriptionKey(prescriptionKey);
    }
   /* @PostMapping("/{medicationPartId}/incrementTotalCount")
    public ResponseEntity<MedicationPart> incrementTotalCount(@PathVariable Integer medicationPartId) {
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with id: " + medicationPartId));

        // Call the incrementCheckboxCount method to increment the count
        medicationPart.incrementTotalCount();

        // Save the updated entity to the database
        MedicationPart updatedMedicationPart = medicationPartRepository.save(medicationPart);

        return ResponseEntity.ok(updatedMedicationPart);
    }*/

    @PostMapping("/{medicationPartId}/decrementTotalCount")
    public ResponseEntity<MedicationPart> decrementTotalCount(@PathVariable Integer medicationPartId) {
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with id: " + medicationPartId));

        // Call the incrementCheckboxCount method to increment the count
        medicationPart.decrementTotalCount();

        // Save the updated entity to the database
        MedicationPart updatedMedicationPart = medicationPartRepository.save(medicationPart);

        return ResponseEntity.ok(updatedMedicationPart);
    }

    // Define a method to increment total count for a MedicationPart
    @PostMapping("/{medicationPartKey}/increment-total-count")
    public MedicationPart incrementTotalCount(@PathVariable Integer medicationPartKey) {
        // Retrieve the MedicationPart from your service or repository
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartKey)
        .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with id: " + medicationPartKey));


        // Call the incrementTotalCount method on the MedicationPart
        medicationPart.incrementTotalCount();

        // Save the updated MedicationPart (if necessary)
        medicationPartRepository.save(medicationPart);

        // Return the updated MedicationPart (optional)
        return medicationPart;
    }

   @PostMapping("/addDayTakeToMedicationPart/{medicationPartId}")
    public ResponseEntity<?> addDayTakeToMedicationPart(
            @PathVariable Integer medicationPartId,
            @RequestBody DayTakes dayTake
    ){
          medicationPartService.addDayTakeToMedicationPart(medicationPartId,dayTake);
        return ResponseEntity.ok("dayTake added to the medicationPart successfully.");

    }



    @GetMapping("/{medicationPartId}/prescription-key")
    public ResponseEntity<Integer> getPrescriptionKeyForMedicationPart(@PathVariable Integer medicationPartId) {
        MedicationPart medicationPart = medicationPartRepository.findById(medicationPartId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicationPart not found with id: " + medicationPartId));


        if (medicationPart == null) {
            return ResponseEntity.notFound().build();
        }

        Integer prescriptionKey = medicationPart.getPrescriptionKeyForMedicationPart();

        if (prescriptionKey != null) {
            return ResponseEntity.ok(prescriptionKey);
        } else {
            return ResponseEntity.noContent().build(); // Handle the case when MedicationPart has no associated prescription
        }
    }

    // increment totalCount




    /*----------------------- DayTakes methods -------------------------*/
    /* @GetMapping("/prescription/{prescriptionKey}")
    public List<MedicationPart> getMedicationPartsForPrescription(@PathVariable Integer prescriptionKey) {
        return medicationPartService.getMedicationPartsByPrescriptionKey(prescriptionKey);
    }*/


    @GetMapping("/{medicationPartId}")
    public MedicationPart getMedicationPartById(@PathVariable Integer medicationPartId) {
        return medicationPartService.getMedicationPartById(medicationPartId);
    }
    @PutMapping("/{medicationPartKey}/update-status")
    public ResponseEntity<String> updateMedicationPartStatus(
            @PathVariable Integer medicationPartKey,
            @RequestParam Status newStatus) {

        try {
            medicationPartService.updateMedicationPartStatus(medicationPartKey, newStatus);
            return ResponseEntity.ok("MedicationPart status updated successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("MedicationPart not found with ID: " + medicationPartKey);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating MedicationPart status");
        }
    }
}
