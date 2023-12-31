package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatmentCategory;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PhysicalTreatmentCategoryRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PhysicalTreatmentRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.PhysicalTreatmentCategoryImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.PhysicalTreatmentImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/categories")
public class PhysicalTreatmentsCategoryController {
    @Autowired
    private PhysicalTreatmentCategoryImpl physicalTreatmentCategoryImpl;
    // constructor that we use to initiate the 2 finals so we can use them in the method "addTreatmentToCategory" in one to many
    private final PhysicalTreatmentCategoryRepository physicalTreatmentCategoryRepository;
    private final PhysicalTreatmentImpl physicalTreatmentImpl;
    private final PhysicalTreatmentRepository physicalTreatmentRepository;
    @Autowired
    public PhysicalTreatmentsCategoryController( PhysicalTreatmentCategoryRepository physicalTreatmentCategoryRepository, PhysicalTreatmentImpl physicalTreatmentImpl, PhysicalTreatmentRepository physicalTreatmentRepository) {
        this.physicalTreatmentCategoryRepository = physicalTreatmentCategoryRepository;
        this.physicalTreatmentImpl = physicalTreatmentImpl;
        this.physicalTreatmentRepository = physicalTreatmentRepository;
    }
    @PostMapping("/category")
    public ResponseEntity <PhysicalTreatmentCategory> createPhysicalTreatmentCategory(@RequestBody PhysicalTreatmentCategory physicalTreatmentCategory){
        PhysicalTreatmentCategory savedPhysicalTreatmentCategory = physicalTreatmentCategoryImpl.createPhysicalTreatmentCategory(physicalTreatmentCategory);
        return new ResponseEntity<PhysicalTreatmentCategory>(savedPhysicalTreatmentCategory, HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public List<PhysicalTreatmentCategory> getAllPhysicalTreatmentsCategories(){ return physicalTreatmentCategoryImpl.getAllPhysicalTreatmentsCategories();}

    @PutMapping("/{id}")
    public PhysicalTreatmentCategory updatePhysicalTreatmentCategory(@RequestBody PhysicalTreatmentCategory physicalTreatmentCategory, @PathVariable ("id") Integer PhysicalTreatmentCategory_Key ) {
        return this.physicalTreatmentCategoryImpl.updatePhysicalTreatmentCategory(physicalTreatmentCategory, PhysicalTreatmentCategory_Key);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PhysicalTreatmentCategory> deletePhysicalTreatmentCategoryById(@PathVariable("id") Integer physicalTreatmentCategory_Key){
        return this.physicalTreatmentCategoryImpl.deletePhysicalTreatmentCategoryById(physicalTreatmentCategory_Key);
    }


    // this methode only works for the case when the treatment is inserted with a category from the beginning
    @PostMapping("/{categoryId}/treatmentsChildren")
    public PhysicalTreatment addTreatmentToCategory(@PathVariable Integer categoryId, @RequestBody PhysicalTreatment physicalTreatment){
        PhysicalTreatmentCategory physicalTreatmentCategory = physicalTreatmentCategoryRepository.findById(categoryId).orElse(null);
        if (physicalTreatmentCategory == null) {
            throw new ResourceNotFoundException(" Category not found with Name: " + categoryId);
        }
        physicalTreatment.setPhysicalTreatmentCategory(physicalTreatmentCategory);
        return physicalTreatmentImpl.createPhysicalTreatment(physicalTreatment);
    }

    // this method is to add a category for a treatment that already exists
    @PostMapping("/{categoryId}/treatmentsChildren/{treatmentId}")
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
    }



/*@GetMapping("/{CategoryId}/treatmentsChildren")
    public List<PhysicalTreatment> getPhysicalTreatmentsByCategoryId(@PathVariable Integer CategoryId){
    return physicalTreatmentCategoryImpl.findByCategoryId(CategoryId);
}*/

    @GetMapping("/check-name-exists")
    public ResponseEntity<Boolean> checkCategoryNameExists(@RequestParam String name) {
        boolean exists = physicalTreatmentCategoryImpl.checkCategoryNameExists(name);
        return ResponseEntity.ok(exists);
    }


    @GetMapping("/count")
    public long countCategories() {
        return physicalTreatmentCategoryImpl.countCategories();
    }
}

