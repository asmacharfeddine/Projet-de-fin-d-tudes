package iss4u.ehr.backoffice.parameterization.prescription.services;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatmentCategory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PhysicalTreatmentCategoryService {

    PhysicalTreatmentCategory createPhysicalTreatmentCategory(PhysicalTreatmentCategory physicalTreatmentCategory);
    List<PhysicalTreatmentCategory> getAllPhysicalTreatmentsCategories();
    PhysicalTreatmentCategory updatePhysicalTreatmentCategory(@RequestBody PhysicalTreatmentCategory physicalTreatmentCategory, Integer Id);
    ResponseEntity<PhysicalTreatmentCategory> deletePhysicalTreatmentCategoryById(Integer Id);
     boolean checkCategoryNameExists(String categoryName);


}
