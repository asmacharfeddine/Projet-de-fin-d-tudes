package iss4u.ehr.backoffice.parameterization.prescription.controllers;


import iss4u.ehr.backoffice.parameterization.prescription.entities.ActiveIngredient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Medication;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.ActiveIngredientsRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.MedicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/medication")
public class MedicationController {

    //Get HTTP Method

    // http://localhost:8080/medication
    // http request convert java to json object
   /* @GetMapping("/medications")
    public Medication getMedication() {
        return new Medication("rrr","FirstMedication","t1",2,"Orale") ;
    }*/

    @Autowired
    private MedicationServiceImpl medicationService;
    @Autowired
    private ActiveIngredientsRepository activeIngredientRepository;
    // get all medications done
    @GetMapping("/list")
    public List<Medication> getAllMedications(){return medicationService.getAllMedications();}


    // create medication with service done but missing obligatory fields annotation ( @NotEmpty)
    @PostMapping("/create")
    public ResponseEntity<Medication> createMedicationService(@RequestBody Medication medication){
        Medication savedMedication = medicationService.createMedication(medication);
        return new ResponseEntity<Medication>(savedMedication, HttpStatus.CREATED);
    }


    // update user done
   @PutMapping("/{id}")
    public Medication updateMedication(@RequestBody Medication medication, @PathVariable ("id") Integer Medication_Key ) {
      return this.medicationService.updateMedication(medication, Medication_Key);
    }

    // delete medication by id done
    @DeleteMapping("/{id}")
    public ResponseEntity<Medication> deleteMedicationById(@PathVariable("id") Integer Medication_Key){
        return this.medicationService.deleteMedicationById(Medication_Key);
    }


    @GetMapping("/id/{id}")
   public Medication getMedicationById(@PathVariable Integer id){
       return medicationService.getMedicationById(id);
   }

   @GetMapping("/name/{name}")
    public Medication findMedicationByName(@PathVariable String name){
        return medicationService.findMedicationByName(name);
    }

   @GetMapping("/code/{code}")
    public Medication findMedicationByCode(@PathVariable String code){
        return medicationService.findMedicationByCode(code);
    }
   @GetMapping("/dosageForm/{dosageForm}")
    public List<Medication> findMedicationByDosageForm(@PathVariable String dosageForm){
        return medicationService.findMedicationByDosageForm(dosageForm);
    }

    @GetMapping("/force/{force}")
    public List<Medication> findMedicationByForce(@PathVariable Float force){
        return medicationService.findMedicationByForce(force);
    }
    // Add an active ingredient to an existing medication
   @PostMapping("/{medicationId}/activeIngredients/{activeIngredient_id}")
    public ResponseEntity<String> addActiveIngredientToMedication(@PathVariable Integer medicationId, @PathVariable Integer activeIngredient_id){
        try {
            medicationService.addActiveIngredientToMedication(medicationId, activeIngredient_id);
            return ResponseEntity.ok("active ingredient added to medication successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Failed to add active ingredient to medication: " + e.getMessage());
        }
    }
    @DeleteMapping("/{medication_id}/activeIngredients/{activeIngredient_id}")
    public ResponseEntity<String> removeActiveIngredientFromMedication(@PathVariable Integer medication_id, @PathVariable Integer activeIngredient_id){
        try {
            medicationService.removeActiveIngredientFromMedication(medication_id,activeIngredient_id);
            return ResponseEntity.ok("activeIngredient removed from medication successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Failed to remove activeIngredient from medication: " + e.getMessage());
        }
        }


       /* @PostMapping("/{medication_Key}/activeIngredients/{value}"){
        public Medication addMedicationWithActiveIngredients(@PathVariable Integer medication_Key, @PathVariable Integer value){
                ActiveIngredient activeIngredient = activeIngredientRepository.findBy
            }
        }*/

        /* @PostMapping("/{categoryId}/treatmentsChildren/{treatmentId}")
    public PhysicalTreatment addTreatmentToCategoryWithIds(@PathVariable Integer categoryId, @PathVariable Integer treatmentId){
        PhysicalTreatmentCategory physicalTreatmentCategory = physicalTreatmentCategoryRepository.findById(categoryId).orElse(null);
        if (physicalTreatmentCategory == null) {
            throw new ResourceNotFoundException(" Category not found with ID: " + categoryId);
        }
        PhysicalTreatment physicalTreatment = physicalTreatmentRepository.findById(treatmentId).orElse(null);
        if(physicalTreatment == null) {
            throw new ResourceNotFoundException(" treatment not found with ID: " + categoryId);
        }
        physicalTreatment.setPhysicalTreatmentCategory(physicalTreatmentCategory);
        return physicalTreatmentImpl.createPhysicalTreatment(physicalTreatment);
    }*/




        @PostMapping("/addMedication")
        public ResponseEntity<Medication> addMedication(
                @RequestBody Medication medication,
                @RequestParam("activeIngredientIds") List<Integer> activeIngredientIds) {
            Medication addedMedication = medicationService.addMedication(medication, activeIngredientIds);
            return ResponseEntity.ok(addedMedication);
        }

    //Integer medication_Key, Medication updatedMedication
    @PutMapping("/updateMedicationWithAcIng/{medication_Key}")
    public ResponseEntity<Medication> updateMedicationWithActiveIngredients(
            @PathVariable Integer medication_Key,
            @RequestBody Medication updatedMedication) {
        try {
            Medication updated = medicationService.updatedMedicationwithActiveIngredients(medication_Key, updatedMedication);
            return ResponseEntity.ok(updated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count")
    public long countMedications() {
        return medicationService.countMedications();
    }
    }
