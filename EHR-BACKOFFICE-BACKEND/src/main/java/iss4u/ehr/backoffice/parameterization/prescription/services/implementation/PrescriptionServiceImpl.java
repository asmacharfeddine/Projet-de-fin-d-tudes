package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Prescription;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository) {
        this.prescriptionRepository = prescriptionRepository;
    }

    public Prescription getPrescriptionByPrescriptionKey(Integer prescriptionKey) {
        return prescriptionRepository.findByPrescriptionKey(prescriptionKey);
    }

    public List<Prescription> getAllPrescriptions(){
        return prescriptionRepository.findAll();
    }
}
