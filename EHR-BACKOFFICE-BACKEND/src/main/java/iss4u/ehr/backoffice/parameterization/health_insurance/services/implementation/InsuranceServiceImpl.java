package iss4u.ehr.backoffice.parameterization.health_insurance.services.implementation;

import iss4u.ehr.backoffice.parameterization.health_insurance.entities.Insurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iss4u.ehr.backoffice.parameterization.health_insurance.repositories.InsuranceRepository;
import iss4u.ehr.backoffice.parameterization.health_insurance.services.InsuranceService;

import java.util.List;
import java.util.Optional;

@Service
public class InsuranceServiceImpl implements InsuranceService {
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public InsuranceServiceImpl(InsuranceRepository insuranceRepository) {
        this.insuranceRepository = insuranceRepository;
    }
    @Override
    public void create(Insurance insurance) {
        insuranceRepository.save(insurance);

    }

    @Override
    public List<Insurance> retrieveInsurances() {

        return insuranceRepository.findAll();
    }
    @Override
    public Optional<Insurance> getInsuranceByKy(Long insKy) {
        return insuranceRepository.findById(insKy);
    }
    @Override
    public void update(Insurance insurance) {
        insuranceRepository.save(insurance);

    }

    @Override
    public void delete(Long insuranceKy) {
        insuranceRepository.deleteById(insuranceKy);

    }
}
