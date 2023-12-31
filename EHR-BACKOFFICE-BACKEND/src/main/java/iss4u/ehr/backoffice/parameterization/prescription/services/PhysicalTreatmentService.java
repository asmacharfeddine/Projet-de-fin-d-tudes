package iss4u.ehr.backoffice.parameterization.prescription.services;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PhysicalTreatmentService {
    PhysicalTreatment createPhysicalTreatment(PhysicalTreatment physicalTreatment);
    List<PhysicalTreatment> getAllPhysicalTreatments();

    PhysicalTreatment findPhysicalTreatmentBytreatmentName(String treatmentName);
    //     PhysicalTreatment getPhysicalTreatmentByCategory(PhysicalTreatmentCategory Category);
    /*getPhysicalTreatmentByCategory to search how it could be done, is it
    by getting the ids of the physical treatments that the corresponds to the id of the selected category*/
    //PhysicalTreatment getPhysicalTreatmentByCategory(String category);

    PhysicalTreatment updatePhysicalTreatment(@RequestBody PhysicalTreatment physicalTreatment, Integer physicalTreatment_Key);
    ResponseEntity<PhysicalTreatment> deletePhysicalTreatmentById(Integer Id);
    boolean checkTreatmentNameExists(String treatmentName);


    //List<PhysicalTreatment>GetTreatmentsByCategoryName(String categoryName, List<PhysicalTreatment> physicalTreatments );

    //List<PhysicalTreatment> findTreatmentsByCategoryId(Integer category_id);

}
