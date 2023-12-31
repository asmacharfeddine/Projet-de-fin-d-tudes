package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalTreatmentRepository extends JpaRepository<PhysicalTreatment, Integer> {

    PhysicalTreatment findPhysicalTreatmentBytreatmentName(String treatmentName);
    boolean existsByTreatmentName(String treatmentName);


    // pourquoi j'ai choisi la requete : -> parce que si on utili
    //
               //* ***************************************************************************************************           */
              //     this request depends on the id sended from the front to the back through clicking on the category name
             //@Query(value = "SELECT * FROM PhysicalTreatment pt WHERE pt.category_id = :category_id ", nativeQuery = true)
            // List<PhysicalTreatment> findTreatmentsByCategoryId(Integer category_id);
}
