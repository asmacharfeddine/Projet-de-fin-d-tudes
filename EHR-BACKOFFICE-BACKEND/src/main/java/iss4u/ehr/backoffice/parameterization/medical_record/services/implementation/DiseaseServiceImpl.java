package iss4u.ehr.backoffice.parameterization.medical_record.services.implementation;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.DiseaseCode;
import iss4u.ehr.backoffice.parameterization.medical_record.repositories.AllergyRepository;
import iss4u.ehr.backoffice.parameterization.medical_record.repositories.DiseaseRepository;
import iss4u.ehr.backoffice.parameterization.medical_record.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class DiseaseServiceImpl implements DiseaseService {
    private final DiseaseRepository diseaseRepository;

    @Autowired
    public DiseaseServiceImpl(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @Override
    public void create(DiseaseCode diseaseCode) {
        this.diseaseRepository.save(diseaseCode);
    }

    @Override
    public List<DiseaseCode> retrieveDiseaseCodes() {
        return this.diseaseRepository.findAll();
    }

    @Override
    public Optional<DiseaseCode> getDiseaseCodeByKy(Integer disease_key) {
        return this.diseaseRepository.findById(disease_key);
    }

    @Override
    public void update(DiseaseCode diseaseCode) {
         this.diseaseRepository.save(diseaseCode);
    }

    @Override
    public void delete(Integer disease_key) {
        this.diseaseRepository.deleteById(disease_key);

    }
}
