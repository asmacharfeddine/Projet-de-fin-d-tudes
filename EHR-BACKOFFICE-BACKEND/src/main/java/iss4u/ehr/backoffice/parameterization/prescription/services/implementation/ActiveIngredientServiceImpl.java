package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;


import iss4u.ehr.backoffice.parameterization.prescription.entities.ActiveIngredient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import iss4u.ehr.backoffice.parameterization.prescription.exceptions.ResourceNotFoundException;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.ActiveIngredientsRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.ActiveIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ActiveIngredientServiceImpl implements ActiveIngredientService {
    @Autowired
    private ActiveIngredientsRepository activeIngredientsRepository;
    //private final JdbcTemplate jdbcTemplate;
   // @Autowired
   // public ActiveIngredientServiceImpl activeIngredientService;
    public ActiveIngredient createActiveIngredient(ActiveIngredient activeIngredient){
        return activeIngredientsRepository.save(activeIngredient);
    }
    public List<ActiveIngredient> getAllActiveIngredients() {
        return this.activeIngredientsRepository.findAll();
    }
    public ActiveIngredient updateActiveIngredient(@RequestBody ActiveIngredient activeIngredient, Integer Id){
        ActiveIngredient existingActiveIngredient = this.activeIngredientsRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException(" this Active ingredient with the Active_Ingredient_Key: "+ Id + " is not found"));
        existingActiveIngredient.setValueName(activeIngredient.getValueName());
        return this.activeIngredientsRepository.save(existingActiveIngredient);
    }
    public ResponseEntity<ActiveIngredient> deleteActiveIngredientById (Integer Id){
        ActiveIngredient existingActiveIngredient = this.activeIngredientsRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Active Ingredient not found with the ActiveIngredients_Key: "+ Id));
        this.activeIngredientsRepository.delete(existingActiveIngredient);
        return ResponseEntity.ok().build();
    }


   public ActiveIngredient findActiveIngredientById(Integer Id){
        return this.activeIngredientsRepository.findById(Id)
                .orElseThrow(() -> new ResourceNotFoundException("Active Ingredient not found with ActiveIngredients_key : " + Id));
    }

   /* public Optional<ActiveIngredient> findActiveIngredientByValue(String value){
        return activeIngredientsRepository.findByValueName(value);
    }*/
    /*public ActiveIngredient findActiveIngredientById(Integer Id){
    String sql = "SELECT * FROM activeIngredients WHERE ActiveIngredients_Key =Id";
    return jdbcTemplate.query(sql, new Object[]{Id}, (resultSet, rowNum) -> {
    ActiveIngredient activeIngredient = new ActiveIngredient();
    activeIngredient.setActiveIngredients_Key(resultSet.getString("value"));
    }
    })*/

    }
