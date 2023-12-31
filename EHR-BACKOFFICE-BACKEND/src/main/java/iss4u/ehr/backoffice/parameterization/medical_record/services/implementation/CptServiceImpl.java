package iss4u.ehr.backoffice.parameterization.medical_record.services.implementation;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.CptCode;
import iss4u.ehr.backoffice.parameterization.medical_record.repositories.CptRepository;
import iss4u.ehr.backoffice.parameterization.medical_record.services.CptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class CptServiceImpl implements CptService {
    private final CptRepository cptRepository;

    @Autowired
    public CptServiceImpl(CptRepository cptRepository) {
        this.cptRepository = cptRepository;
    }

    @Override
    public void create(CptCode cptCode) {
        this.cptRepository.save(cptCode);
    }

    @Override
    public List<CptCode> retrieveCptCodes() {
        return this.cptRepository.findAll();
    }

    @Override
    public Optional<CptCode> getCptCodeByKy(Integer surgical_Key) {
        return this.cptRepository.findById(surgical_Key);
    }

    @Override
    public void update(CptCode cptCode) {
        this.cptRepository.save(cptCode);
    }

    @Override
    public void delete(Integer surgical_Key) {
        this.cptRepository.deleteById(surgical_Key);
    }
}
