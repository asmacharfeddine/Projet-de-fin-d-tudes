package iss4u.ehr.backoffice.parameterization.prescription.services;

import iss4u.ehr.backoffice.parameterization.prescription.entities.ActiveIngredient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface ActiveIngredientService {

    ActiveIngredient createActiveIngredient(ActiveIngredient activeIngredient);

    List<ActiveIngredient> getAllActiveIngredients();
    ActiveIngredient updateActiveIngredient(@RequestBody ActiveIngredient activeIngredient, Integer Id);
    ResponseEntity<ActiveIngredient> deleteActiveIngredientById(Integer Id);
    ActiveIngredient findActiveIngredientById(Integer Id);

    //Optional<ActiveIngredient> findActiveIngredientByValue(String value);


    /*ActiveIngredient findActiveIngredientByName(String name);* actually we don't need this method
    because we're going to have a short list that will show us all the ingredients */


}
