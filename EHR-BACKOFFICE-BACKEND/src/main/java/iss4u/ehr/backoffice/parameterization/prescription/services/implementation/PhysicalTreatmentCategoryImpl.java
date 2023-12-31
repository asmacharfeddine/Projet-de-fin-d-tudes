package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatmentCategory;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PhysicalTreatmentCategoryRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.PhysicalTreatmentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PhysicalTreatmentCategoryImpl implements PhysicalTreatmentCategoryService {
    @Autowired
    private PhysicalTreatmentCategoryRepository physicalTreatmentCategoryRepository;
   /* private final PhysicalTreatmentRepository physicalTreatmentRepository;



    public PhysicalTreatmentCategoryImpl(PhysicalTreatmentCategoryRepository physicalTreatmentCategoryRepository, PhysicalTreatmentRepository physicalTreatmentRepository){
        this.physicalTreatmentCategoryRepository = physicalTreatmentCategoryRepository;
        this.physicalTreatmentRepository = physicalTreatmentRepository;
    }*/

    public PhysicalTreatmentCategory createPhysicalTreatmentCategory(PhysicalTreatmentCategory physicalTreatmentCategory){
        return physicalTreatmentCategoryRepository.save(physicalTreatmentCategory);
    }

    public List<PhysicalTreatmentCategory> getAllPhysicalTreatmentsCategories(){
        return physicalTreatmentCategoryRepository.findAll();
    }

    public PhysicalTreatmentCategory updatePhysicalTreatmentCategory(@RequestBody PhysicalTreatmentCategory physicalTreatmentCategory, Integer Id){
        PhysicalTreatmentCategory existingPhysicalTreatmentCategory = this.physicalTreatmentCategoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Physical treatment category not found with PhysicalTreatmentCategory_Key : " + Id));
        existingPhysicalTreatmentCategory.setCategoryName(physicalTreatmentCategory.getCategoryName());
        existingPhysicalTreatmentCategory.setCategoryDescription(physicalTreatmentCategory.getCategoryDescription());
        return this.physicalTreatmentCategoryRepository.save(existingPhysicalTreatmentCategory);
    }


    public ResponseEntity<PhysicalTreatmentCategory> deletePhysicalTreatmentCategoryById(Integer Id){
        PhysicalTreatmentCategory existingPhysicalTreatmentCategory = this.physicalTreatmentCategoryRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Physical Treatment Category not found with PhysicalTreatmentCategory_Key : "+ Id));
        this.physicalTreatmentCategoryRepository.delete(existingPhysicalTreatmentCategory);
        return ResponseEntity.ok().build();

    }

   /* public void saveCategoryWithTreatments(PhysicalTreatmentCategory physicalTreatmentCategory, List<PhysicalTreatment> physicalTreatments){
        for(PhysicalTreatment physicalTreatment : physicalTreatments) {
            physicalTreatment.setPhysicalTreatmentCategory(physicalTreatmentCategory);
        }
        physicalTreatmentCategory.setPhysicalTreatments(physicalTreatments);
        physicalTreatmentCategoryRepository.save(physicalTreatmentCategory);
    }*/


    public boolean checkCategoryNameExists(String categoryName) {
        return physicalTreatmentCategoryRepository.existsByCategoryName(categoryName);
    }


    public long countCategories() {
        return physicalTreatmentCategoryRepository.count();
    }
}
