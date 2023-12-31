package iss4u.ehr.backoffice.parameterization.prescription.repositories;

import iss4u.ehr.backoffice.parameterization.prescription.entities.ActiveIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActiveIngredientsRepository extends JpaRepository<ActiveIngredient, Integer> {
  //  Optional<ActiveIngredient> findByValueName(String valueName);
}
