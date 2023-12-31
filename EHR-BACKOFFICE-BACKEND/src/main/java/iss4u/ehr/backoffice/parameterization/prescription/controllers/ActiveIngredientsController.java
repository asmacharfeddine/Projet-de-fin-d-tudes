package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.ActiveIngredient;
import iss4u.ehr.backoffice.parameterization.prescription.entities.PhysicalTreatment;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.ActiveIngredientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/project/activeIngredient")
public class ActiveIngredientsController {
    @Autowired
    private ActiveIngredientServiceImpl activeIngredientService;

    @PostMapping("/create")
    public ResponseEntity<ActiveIngredient> createActiveIngredient(@RequestBody ActiveIngredient activeIngredient){
        ActiveIngredient savedActiveIngredient = activeIngredientService.createActiveIngredient(activeIngredient) ;
        return new ResponseEntity<ActiveIngredient>(savedActiveIngredient, HttpStatus.CREATED);
    }
    //Get all Active Ingredients with medications
    @GetMapping("/allWithMedications")
    public List<ActiveIngredient> getAllActiveIngredientsWithMedications(){
        return activeIngredientService.getAllActiveIngredients();
    }

    //Get all ActiveIngredients without medications
    @GetMapping("/all")
    public List<ActiveIngredient> getAllActiveIngredients(){
        List<ActiveIngredient> listeAi = new ArrayList<>();
        listeAi = activeIngredientService.getAllActiveIngredients();
        for (ActiveIngredient activeIngredient : listeAi){
            activeIngredient.setMedications(null);
        }
        return listeAi;
    }

    @PutMapping("/{id}")
    public ActiveIngredient updateActiveIngredient(@RequestBody ActiveIngredient activeIngredient, @PathVariable("id") Integer ActiveIngredients_Key){
        return this.activeIngredientService.updateActiveIngredient(activeIngredient, ActiveIngredients_Key);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ActiveIngredient> deleteActiveIngredientById(@PathVariable("id") Integer ActiveIngredient_Key) {
        return this.activeIngredientService.deleteActiveIngredientById(ActiveIngredient_Key);

    }
    @GetMapping("/id/{id}")
    public ActiveIngredient findActiveIngredientById(@PathVariable Integer id){
        return this.activeIngredientService.findActiveIngredientById(id);
    }

    /*@GetMapping("/{value}")
    public ResponseEntity<ActiveIngredient> findActiveIngredientByValue(@PathVariable String value){
        Optional<ActiveIngredient> optionalActiveIngredient=activeIngredientService.findActiveIngredientByValue(value);
        if (optionalActiveIngredient.isPresent()){
            ActiveIngredient activeIngredient = optionalActiveIngredient.get();
            return ResponseEntity.ok(activeIngredient);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }*/

}
