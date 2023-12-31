package iss4u.ehr.backoffice.parameterization.medical_record.controllers;


import iss4u.ehr.backoffice.parameterization.medical_record.entities.DiseaseCode;
import iss4u.ehr.backoffice.parameterization.medical_record.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/disease")

public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;

    @PostMapping
    public ResponseEntity<DiseaseCode> createDiseaseCode(@RequestBody DiseaseCode diseaseCode) {
        diseaseService.create(diseaseCode);
        return new ResponseEntity<>(diseaseCode, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DiseaseCode>> getAllDiseaseCodes() {
        List<DiseaseCode> diseaseCodes = diseaseService.retrieveDiseaseCodes();
        return new ResponseEntity<>(diseaseCodes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiseaseCode> getDiseaseCodeById(@PathVariable("id") Integer disease_key) {
        Optional<DiseaseCode> diseaseCode = diseaseService.getDiseaseCodeByKy(disease_key);
        return diseaseCode.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DiseaseCode> updateDiseaseCode(@PathVariable("id") Integer disease_key, @RequestBody DiseaseCode diseaseCode) {
        Optional<DiseaseCode> existingDiseaseCode = diseaseService.getDiseaseCodeByKy(disease_key);
        if (existingDiseaseCode.isPresent()) {
            diseaseCode.setDisease_Key(disease_key);
            diseaseService.update(diseaseCode);
            return new ResponseEntity<>(diseaseCode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAllergy(@PathVariable("id") Integer disease_key) {
        Optional<DiseaseCode> existingDiseaseCode = diseaseService.getDiseaseCodeByKy(disease_key);
        if (existingDiseaseCode.isPresent()) {
            diseaseService.delete(disease_key);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
