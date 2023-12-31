package iss4u.ehr.backoffice.parameterization.medical_record.controllers;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.Allergy;
import iss4u.ehr.backoffice.parameterization.medical_record.services.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/allergy")
public class AllergyController {

    @Autowired
    private AllergyService allergyService;

    @PostMapping
    public ResponseEntity<Allergy> createAllergy(@RequestBody Allergy allergy) {
        allergyService.create(allergy);
        return new ResponseEntity<>(allergy, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Allergy>> getAllAllergies() {
        List<Allergy> allergies = allergyService.retrieveAllergies();
        return new ResponseEntity<>(allergies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Allergy> getAllergyById(@PathVariable("id") Integer allergy_key) {
        Optional<Allergy> allergy = allergyService.getAllergyByKy(allergy_key);
        return allergy.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Allergy> updateAllergy(@PathVariable("id") Integer allergy_key, @RequestBody Allergy allergy) {
        Optional<Allergy> existingAllergy = allergyService.getAllergyByKy(allergy_key);
        if (existingAllergy.isPresent()) {
            allergy.setAllergy_Key(allergy_key);
            allergyService.update(allergy);
            return new ResponseEntity<>(allergy, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAllergy(@PathVariable("id") Integer allergy_key) {
        Optional<Allergy> existingAllergy = allergyService.getAllergyByKy(allergy_key);
        if (existingAllergy.isPresent()) {
            allergyService.delete(allergy_key);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
