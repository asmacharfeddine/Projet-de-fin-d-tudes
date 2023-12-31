package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Doctor;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.MedicationPartRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.DoctorServiceImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.MedicationPartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/Doctors")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    @Autowired
    public DoctorController( DoctorServiceImpl doctorService ) {
        this.doctorService = doctorService;

    }
    @PostMapping("/add")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        // Pass the entire Doctor object to the service
        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/{doctorId}/patients")
    public List<Patient> getAllPatientsForDoctor(@PathVariable Integer doctorId) {
        return doctorService.getAllPatientsForDoctor(doctorId);
    }

    @PostMapping("/{doctorId}/addPatient/{patientId}")
    public void addPatientToDoctor(@PathVariable Integer doctorId, @PathVariable Integer patientId) {
        doctorService.addPatientToDoctor(doctorId, patientId);
    }

    @GetMapping("/byEmail/{userEmail}")
    public ResponseEntity<Doctor> getDoctorByEmail(@PathVariable String userEmail) {
        Doctor doctor = doctorService.getDoctorByEmail(userEmail);

        if (doctor != null) {
            return new ResponseEntity<>(doctor, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
