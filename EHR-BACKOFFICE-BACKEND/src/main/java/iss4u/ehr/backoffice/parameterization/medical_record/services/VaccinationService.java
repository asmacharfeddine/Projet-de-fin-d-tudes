package iss4u.ehr.backoffice.parameterization.medical_record.services;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.Vaccination;

import java.util.List;
import java.util.Optional;

public interface VaccinationService {
    void create(Vaccination vaccination);

    List<Vaccination> retrieveVaccinations();

    Optional<Vaccination> getVaccinationByKy(Integer vaccination_Key);

    void update(Vaccination vaccination);

    void delete(Integer vaccination_Key);
}
