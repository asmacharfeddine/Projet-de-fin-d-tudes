package iss4u.ehr.backoffice.parameterization.medical_record.services.implementation;


import iss4u.ehr.backoffice.parameterization.medical_record.entities.BioAnalyses;

import iss4u.ehr.backoffice.parameterization.medical_record.repositories.AnalyseRepository;
import iss4u.ehr.backoffice.parameterization.medical_record.services.AnalyseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AnalyseServiceImpl implements AnalyseService {
    private final AnalyseRepository analyseRepository;

    @Autowired
    public AnalyseServiceImpl(AnalyseRepository analyseRepository) {
        this.analyseRepository = analyseRepository;
    }


    @Override
    public void create(BioAnalyses bioAnalyses) {
        this.analyseRepository.save(bioAnalyses);
    }

    @Override
    public List<BioAnalyses> retrieveBioAnalyses() {
        return this.analyseRepository.findAll();
    }

    @Override
    public Optional<BioAnalyses> getBioAnalysesByKy(Integer analyse_key) {
        return this.analyseRepository.findById(analyse_key);
    }

    @Override
    public void update(BioAnalyses bioAnalyses) {
        this.analyseRepository.save(bioAnalyses);
    }

    @Override
    public void delete(Integer analyse_key) {
        this.analyseRepository.deleteById(analyse_key);
    }
}


