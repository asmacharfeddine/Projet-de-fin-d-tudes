package iss4u.ehr.backoffice.parameterization.prescription.controllers;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Prescription;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.MedicationPartRepository;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.MedicationPartServiceImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.PatientServiceImpl;
import iss4u.ehr.backoffice.parameterization.prescription.services.implementation.PrescriptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // combination between "@Controller" and "@Body response"
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    PrescriptionServiceImpl prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionServiceImpl prescriptionService) {
        this.prescriptionService=prescriptionService;

    }
    @GetMapping("/{prescriptionKey}")
    public Prescription getPrescriptionByPrescriptionKey(@PathVariable Integer prescriptionKey) {
        return prescriptionService.getPrescriptionByPrescriptionKey(prescriptionKey);
    }

    @GetMapping("/all")
    public List<Prescription> getAllPrescriptions(){
        return prescriptionService.getAllPrescriptions();
    }
}


