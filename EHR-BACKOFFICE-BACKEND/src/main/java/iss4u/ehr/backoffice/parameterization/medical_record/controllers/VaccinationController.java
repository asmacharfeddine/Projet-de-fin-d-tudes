package iss4u.ehr.backoffice.parameterization.medical_record.controllers;


import iss4u.ehr.backoffice.parameterization.medical_record.entities.Vaccination;

import iss4u.ehr.backoffice.parameterization.medical_record.services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vaccination")
public class VaccinationController {
    @Autowired
    private VaccinationService vaccinationService;

    @PostMapping
    public ResponseEntity<Vaccination> createVaccination(@RequestBody Vaccination vaccination) {
        vaccinationService.create(vaccination);
        return new ResponseEntity<>(vaccination, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Vaccination>> getAllVaccinations() {
        List<Vaccination> vaccinations = vaccinationService.retrieveVaccinations();
        return new ResponseEntity<>(vaccinations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaccination> getVaccinationById(@PathVariable("id") Integer vaccination_key) {
        Optional<Vaccination> vaccination = vaccinationService.getVaccinationByKy(vaccination_key);
        return vaccination.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaccination> updateVaccination(@PathVariable("id") Integer vaccination_key, @RequestBody Vaccination vaccination) {
        Optional<Vaccination> existingVaccination = vaccinationService.getVaccinationByKy(vaccination_key);
        if (existingVaccination.isPresent()) {
            vaccination.setVaccination_Key(vaccination_key);
            vaccinationService.update(vaccination);
            return new ResponseEntity<>(vaccination, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteVaccination(@PathVariable("id") Integer vaccination_key) {
        Optional<Vaccination> existingVaccination = vaccinationService.getVaccinationByKy(vaccination_key);
        if (existingVaccination.isPresent()) {
            vaccinationService.delete(vaccination_key);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
