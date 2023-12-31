package iss4u.ehr.backoffice.parameterization.prescription.repositories;
import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalTreatmentCategoryRepository extends JpaRepository<PhysicalTreatmentCategory, Integer> {
   // PhysicalTreatmentCategory findByCategoryName(String categoryName);
   boolean existsByCategoryName(String categoryName);

}
