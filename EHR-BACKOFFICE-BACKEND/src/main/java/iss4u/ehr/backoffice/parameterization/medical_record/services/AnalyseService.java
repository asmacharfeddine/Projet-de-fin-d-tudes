package iss4u.ehr.backoffice.parameterization.medical_record.services;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.BioAnalyses;

import java.util.List;
import java.util.Optional;

public interface AnalyseService {
    void create(BioAnalyses bioAnalyses);

    List<BioAnalyses> retrieveBioAnalyses();

    Optional<BioAnalyses> getBioAnalysesByKy(Integer analyse_key);

    void update(BioAnalyses bioAnalyses);

    void delete(Integer analyse_key);
}
