package iss4u.ehr.backoffice.parameterization.medical_record.services.implementation;


import iss4u.ehr.backoffice.parameterization.medical_record.entities.Vaccination;

import iss4u.ehr.backoffice.parameterization.medical_record.repositories.VaccinationRepository;
import iss4u.ehr.backoffice.parameterization.medical_record.services.VaccinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class VaccinationServiceImpl implements VaccinationService {
    private final VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationServiceImpl(VaccinationRepository vaccinationRepository) {
        this.vaccinationRepository = vaccinationRepository;
    }


    @Override
    public void create(Vaccination vaccination) {
        this.vaccinationRepository.save(vaccination);
    }

    @Override
    public List<Vaccination> retrieveVaccinations() {
        return this.vaccinationRepository.findAll();
    }

    @Override
    public Optional<Vaccination> getVaccinationByKy(Integer vaccination_key) {
        return this.vaccinationRepository.findById(vaccination_key);
    }

    @Override
    public void update(Vaccination vaccination) {
        this.vaccinationRepository.save(vaccination);
    }

    @Override
    public void delete(Integer vaccination_Key) {
        this.vaccinationRepository.deleteById(vaccination_Key);
    }
}

