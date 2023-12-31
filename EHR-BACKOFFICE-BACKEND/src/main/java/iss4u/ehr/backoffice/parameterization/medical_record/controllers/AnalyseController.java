package iss4u.ehr.backoffice.parameterization.medical_record.controllers;
import iss4u.ehr.backoffice.parameterization.medical_record.entities.BioAnalyses;
import iss4u.ehr.backoffice.parameterization.medical_record.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/BioAnalyses")
public class AnalyseController {
    @Autowired
    private AnalyseService analyseService;

    @PostMapping
    public ResponseEntity<BioAnalyses> createBioAnalyses(@RequestBody BioAnalyses bioAnalyses) {
        analyseService.create(bioAnalyses);
        return new ResponseEntity<>(bioAnalyses, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BioAnalyses>> getAllAnalyses() {
        List<BioAnalyses> Analyses = analyseService.retrieveBioAnalyses();
        return new ResponseEntity<>(Analyses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BioAnalyses> getBioAnalysesById(@PathVariable("id") Integer analyse_key) {
        Optional<BioAnalyses> bioAnalyses = analyseService.getBioAnalysesByKy(analyse_key);
        return bioAnalyses.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BioAnalyses> updateBioAnalyses(@PathVariable("id") Integer analyse_key, @RequestBody BioAnalyses bioAnalyses) {
        Optional<BioAnalyses> existingBioAnalyses = analyseService.getBioAnalysesByKy(analyse_key);
        if (existingBioAnalyses.isPresent()) {
            bioAnalyses.setAnalyse_Key(analyse_key);
            analyseService.update(bioAnalyses);
            return new ResponseEntity<>(bioAnalyses, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBioAnalyses(@PathVariable("id") Integer analyse_key) {
        Optional<BioAnalyses> existingBioAnalyses = analyseService.getBioAnalysesByKy(analyse_key);
        if (existingBioAnalyses.isPresent()) {
            analyseService.delete(analyse_key);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


