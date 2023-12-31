package iss4u.ehr.backoffice.parameterization.prescription.services.implementation;

import iss4u.ehr.backoffice.parameterization.prescription.entities.Doctor;
import iss4u.ehr.backoffice.parameterization.prescription.entities.Patient;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.DayTakesRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.DoctorRepository;
import iss4u.ehr.backoffice.parameterization.prescription.repositories.PatientRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository,PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

   /* @Transactional
    public Doctor addDoctor(String userFirstName, String userLastName, String userEmail, String userPassword, String gender, String mobile) {
        // Create a new Doctor instance
        Doctor doctor = new Doctor();
        doctor.setUserFirstName(userFirstName);
        doctor.setUserLastName(userLastName);
        doctor.setUserEmail(userEmail);
        doctor.setUserPassword(userPassword);
        doctor.setGender(gender);
        doctor.setMobile(mobile);

        // Save the doctor to the database
        return doctorRepository.save(doctor);
    }*/


    @Transactional
    public Doctor addDoctor(Doctor doctor) {
        // Save the doctor to the database
        return doctorRepository.save(doctor);
    }

    public List<Patient> getAllPatientsForDoctor(Integer doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        // Assuming you have a 'getPatients' method in the Doctor class
        return new ArrayList<>(doctor.getPatients());
    }

    @Transactional
    public void addPatientToDoctor(Integer doctorId, Integer patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + patientId));

        doctor.getPatients().add(patient);
        patient.getDoctors().add(doctor);
    }

    //@Transactional(readOnly = true)
    public Doctor getDoctorByEmail(String userEmail) {
        return doctorRepository.findByUserEmail(userEmail);
    }
}
