package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Doctor;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Nurse;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.DoctorServiceImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.NurseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/Nurses")
public class NurseController {
    private final NurseServiceImpl nurseService;

    @Autowired
    public NurseController( NurseServiceImpl nurseService ) {
        this.nurseService = nurseService;

    }
        @GetMapping("/byEmail/{userEmail}")
    public ResponseEntity<Nurse> getNurseByEmail(@PathVariable String userEmail) {
        Nurse nurse = nurseService.getNurseByEmail(userEmail);

        if (nurse != null) {
            return new ResponseEntity<>(nurse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{nurseId}/patients")
    public List<Patient> getAllPatientsForNurse(@PathVariable Integer nurseId) {
        return nurseService.getAllPatientsForNurse(nurseId);
    }

}
