package iss4u.ehr.backoffice.radiology.controllers;

import iss4u.ehr.backoffice.radiology.entities.Study;
import iss4u.ehr.backoffice.radiology.services.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/radiography/study")
public class StudyController {
    @Autowired
    private StudyService studyService;

    @PostMapping
    public ResponseEntity<Study> createStudy(@RequestBody Study study) {
        studyService.create(study);
        return new ResponseEntity<>(study, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Study>> getAllStudies() {
        List<Study> studies = studyService.retrieveStudies();
        return new ResponseEntity<>(studies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Study> getStudyById(@PathVariable("id") Long studyId) {
        Optional<Study> study = studyService.getStudyByKy(studyId);
        return study.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{studyKy}")
    public ResponseEntity<Study> updateStudy(@PathVariable Long studyKy, @RequestBody Study updatedStudy) {
        try {
            studyService.update(studyKy, updatedStudy);
            return ResponseEntity.ok().build(); // Return 200 (OK) without a body
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudy(@PathVariable("id") Long studyId) {
        Optional<Study> existingStudy = studyService.getStudyByKy(studyId);
        if (existingStudy.isPresent()) {
            studyService.delete(studyId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
