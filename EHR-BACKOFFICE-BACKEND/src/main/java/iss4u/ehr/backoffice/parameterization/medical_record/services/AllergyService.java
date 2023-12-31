package iss4u.ehr.backoffice.parameterization.medical_record.services;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.Allergy;

import java.util.List;
import java.util.Optional;

public interface AllergyService {

    void create(Allergy allergy);

    List<Allergy> retrieveAllergies();

    Optional<Allergy> getAllergyByKy(Integer allergy_Key);

    void update(Allergy allergy);

    void delete(Integer allergy_Key);


}
