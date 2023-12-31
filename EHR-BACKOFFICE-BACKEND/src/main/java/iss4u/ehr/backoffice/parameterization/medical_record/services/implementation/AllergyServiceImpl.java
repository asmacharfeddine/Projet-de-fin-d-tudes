package iss4u.ehr.backoffice.parameterization.medical_record.services.implementation;

import iss4u.ehr.backoffice.parameterization.medical_record.entities.Allergy;
import iss4u.ehr.backoffice.parameterization.medical_record.repositories.AllergyRepository;
import iss4u.ehr.backoffice.parameterization.medical_record.services.AllergyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllergyServiceImpl implements AllergyService {

    private final AllergyRepository allergyRepository;

    @Autowired
    public AllergyServiceImpl(AllergyRepository allergyRepository) {
        this.allergyRepository = allergyRepository;
    }


    @Override
    public void create(Allergy allergy) {
        this.allergyRepository.save(allergy);
    }

    @Override
    public List<Allergy> retrieveAllergies() {
        return this.allergyRepository.findAll();
    }

    @Override
    public Optional<Allergy> getAllergyByKy(Integer allergy_Key) {
        return this.allergyRepository.findById(allergy_Key);
    }

    @Override
    public void update(Allergy allergy) {
        this.allergyRepository.save(allergy);
    }

    @Override
    public void delete(Integer allergy_Key) {
        this.allergyRepository.deleteById(allergy_Key);
    }
}
