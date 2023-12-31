package iss4u.ehr.backoffice.parameterization.medical_record.services;


import iss4u.ehr.backoffice.parameterization.medical_record.entities.CptCode;

import java.util.List;
import java.util.Optional;

public interface CptService {
    void create(CptCode cptCode);

    List<CptCode> retrieveCptCodes();

    Optional<CptCode> getCptCodeByKy(Integer surgical_Key);

    void update(CptCode cptCode);

    void delete(Integer surgical_Key);
}
