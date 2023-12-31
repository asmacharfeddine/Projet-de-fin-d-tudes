package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Doctor;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Nurse;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.NurseRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NurseServiceImpl {
    private final NurseRepository nurseRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public NurseServiceImpl(NurseRepository nurseRepository, PatientRepository patientRepository){
        this.nurseRepository = nurseRepository;
        this.patientRepository = patientRepository;
    }

    @Transactional
    public Nurse addNurse(Nurse nurse) {
        // Save the doctor to the database
        return nurseRepository.save(nurse);
    }

    public List<Patient> getAllPatientsForNurse(Integer nurseId) {
        Nurse nurse = nurseRepository.findById(nurseId)
                .orElseThrow(() -> new RuntimeException("nurse not found with id: " + nurseId));

        // Assuming you have a 'getPatients' method in the Doctor class
        return new ArrayList<>(nurse.getPatients());
    }

    @Transactional
    public void addPatientToNurse(Integer nurseId, Integer patientId) {
        Nurse nurse = nurseRepository.findById(nurseId)
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + nurseId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        nurse.getPatients().add(patient);
        patient.getNurses().add(nurse);
    }

    public Nurse getNurseByEmail(String userEmail) {
        return nurseRepository.findByUserEmail(userEmail);
    }
}
