package iss4u.ehr.backoffice.parameterization.medical_record.services;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.DiseaseCode;

import java.util.List;
import java.util.Optional;

public interface DiseaseService {
    void create(DiseaseCode diseaseCode);

    List<DiseaseCode> retrieveDiseaseCodes();

    Optional<DiseaseCode> getDiseaseCodeByKy(Integer disease_key);

    void update(DiseaseCode diseaseCode);

    void delete(Integer disease_key);
}
