package iss4u.ehr.backoffice.parameterization.health_insurance.controllers;


import iss4u.ehr.backoffice.parameterization.health_insurance.entities.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import iss4u.ehr.backoffice.parameterization.health_insurance.services.InsuranceService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/healthInsurance")
public class InsuranceController {

    @Autowired
    private InsuranceService insuranceService;

    public InsuranceController() {
    }

    @PostMapping
    public ResponseEntity<Insurance> createInsurance(@RequestBody Insurance insurance) {
        this.insuranceService.create(insurance);
        return new ResponseEntity<>(insurance, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Insurance>> getAllInsurances() {
        List<Insurance> insurances = insuranceService.retrieveInsurances();
        return new ResponseEntity<>(insurances, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Insurance> getInsurancebyky(@PathVariable("id") Long insKy) {
        Optional<Insurance> insurance = this.insuranceService.getInsuranceByKy(insKy);
        return (ResponseEntity) insurance.map((value) -> {
            return new ResponseEntity(value, HttpStatus.OK);
        }).orElseGet(() -> {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insurance> updateInsurance(@PathVariable("id") Long insKy, @RequestBody Insurance insurance) {
        Optional<Insurance> existingInsurance = insuranceService.getInsuranceByKy(insKy);
        if (existingInsurance.isPresent()) {
            insurance.setInsKy(insKy);
            this.insuranceService.update(insurance);
            return new ResponseEntity<>(insurance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInsurance(@PathVariable("id") Long insKy) {
        Optional<Insurance> existingInsurance = this.insuranceService.getInsuranceByKy(insKy);
        if (existingInsurance.isPresent()) {
            this.insuranceService.delete(insKy);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}





