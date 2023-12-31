package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.PhysicalTreatmentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/treatments")
public class PhysicalTreatmentController {
@Autowired
PhysicalTreatmentImpl physicalTreatmentImpl;

    @PostMapping("/create")
    public ResponseEntity<PhysicalTreatment> createPhysicalTreatment(@RequestBody PhysicalTreatment physicalTreatment){
        PhysicalTreatment savedPhysicalTreatment = physicalTreatmentImpl.createPhysicalTreatment(physicalTreatment);
        return new ResponseEntity<PhysicalTreatment>(savedPhysicalTreatment, HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<PhysicalTreatment> getAllPhysicalTreatments(){ return physicalTreatmentImpl.getAllPhysicalTreatments();}
    @PutMapping("/{id}")
    public PhysicalTreatment updatePhysicalTreatment(@RequestBody PhysicalTreatment physicalTreatment, @PathVariable ("id") Integer physicalTreatment_Key ) {
        return this.physicalTreatmentImpl.updatePhysicalTreatment(physicalTreatment, physicalTreatment_Key);
    }


    // update physical treatment that have been added with category from the beginning == the method iam using
    @PutMapping("/treatment/{physicalTreatment_Key}")
    public ResponseEntity<PhysicalTreatment> updatePhysicalTreatmentWithCategory(
            @PathVariable Integer physicalTreatment_Key,
            @RequestBody PhysicalTreatment updatedPhysicalTreatment
    ) {

        PhysicalTreatment updatedTreatment = physicalTreatmentImpl.updatePhysicalTreatmentWithCategory(physicalTreatment_Key, updatedPhysicalTreatment);
        return ResponseEntity.ok(updatedTreatment);
    }

    /*@GetMapping("/name/{treatmentName}")
    public PhysicalTreatment getPhysicalTreatmentByName(@PathVariable String treatmentName){
        return physicalTreatmentImpl.findPhysicalTreatmentByName(treatmentName);
    }*/

    @GetMapping("/treatmentName/{treatmentName}")
    public PhysicalTreatment findPhysicalTreatmentBytreatmentName(@PathVariable String treatmentName){
        return physicalTreatmentImpl.findPhysicalTreatmentBytreatmentName(treatmentName);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PhysicalTreatment> deletePhysicalTreatmentById(@PathVariable("id") Integer PhysicalTreatment_Key){
        return this.physicalTreatmentImpl.deletePhysicalTreatmentById(PhysicalTreatment_Key);
    }


   /* @GetMapping("/categoryNameToId/{category_id}")
    public ResponseEntity<List<PhysicalTreatment>> findTreatmentsByCategoryId(@PathVariable Integer category_id){
        List<PhysicalTreatment> physicalTreatments = physicalTreatmentImpl.findTreatmentsByCategoryId(category_id);
        if(physicalTreatments.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok(physicalTreatments);
        }
    }*/

   /* @GetMapping("/{listeph}/id/{Id}")
    public  ResponseEntity<List<PhysicalTreatment>> findTreatmentsByCategoryId(@PathVariable List<PhysicalTreatment> physicalTreatments,@PathVariable Integer Id) {
        List<PhysicalTreatment> listeph = new ArrayList<>();
        listeph = physicalTreatmentImpl.findTreatmentsByCategoryId(physicalTreatments, Id);
        if (physicalTreatments.isEmpty()) {
            return ResponseEntity.noContent().build();

        } else {
            return ResponseEntity.ok(physicalTreatments);
        }

    }*/

  /*  @GetMapping("/check-name-exists/{treatmentName}")
    public ResponseEntity<Boolean> checkTreatmentNameExists(@PathVariable String treatmentName) {
        boolean exists = physicalTreatmentImpl.checkTreatmentNameExists(treatmentName);
        return ResponseEntity.ok(exists);
    }*/

    @GetMapping("/count")
    public long countPhysicalTreatments() {
        return physicalTreatmentImpl.countPhysicalTreatments();
    }
    }

