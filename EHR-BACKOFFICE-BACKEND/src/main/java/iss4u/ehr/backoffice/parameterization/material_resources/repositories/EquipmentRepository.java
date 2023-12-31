package iss4u.ehr.backoffice.parameterization.material_resources.repositories;

import iss4u.ehr.backoffice.parameterization.material_resources.entities.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Equipment findByEquipmentLabel(String equipmentLabel);
}
