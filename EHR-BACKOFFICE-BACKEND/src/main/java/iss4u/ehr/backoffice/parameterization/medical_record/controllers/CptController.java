package iss4u.ehr.backoffice.parameterization.medical_record.controllers;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.CptCode;
import iss4u.ehr.backoffice.parameterization.medical_record.services.CptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/CptCode")
public class CptController {
    @Autowired
    private CptService cptService;

    @PostMapping
    public ResponseEntity<CptCode> createCptCode(@RequestBody CptCode cptCode) {
        cptService.create(cptCode);
        return new ResponseEntity<>(cptCode, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CptCode>> getAllCptCodes() {
        List<CptCode> cptCodes = cptService.retrieveCptCodes();
        return new ResponseEntity<>(cptCodes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CptCode> getCptCodeByKy(@PathVariable("id") Integer surgical_Key) {
        Optional<CptCode> cptCode = cptService.getCptCodeByKy(surgical_Key);
        return cptCode.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CptCode> updateCptCode(@PathVariable("id") Integer surgical_Key, @RequestBody CptCode cptCode) {
        Optional<CptCode> existingCptCode = cptService.getCptCodeByKy(surgical_Key);
        if (existingCptCode.isPresent()) {
            cptCode.setSurgical_Key(surgical_Key);
            cptService.update(cptCode);
            return new ResponseEntity<>(cptCode, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCptCode(@PathVariable("id") Integer surgical_Key) {
        Optional<CptCode> existingCptCode = cptService.getCptCodeByKy(surgical_Key);
        if (existingCptCode.isPresent()) {
            cptService.delete(surgical_Key);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
