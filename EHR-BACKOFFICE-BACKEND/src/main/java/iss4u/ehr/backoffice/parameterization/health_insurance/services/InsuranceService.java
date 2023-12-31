package iss4u.ehr.backoffice.parameterization.health_insurance.services;

import iss4u.ehr.backoffice.parameterization.health_insurance.entities.Insurance;

import java.util.List;
import java.util.Optional;

public interface InsuranceService {

    void create(Insurance insurance);

    List<Insurance> retrieveInsurances();

    Optional<Insurance> getInsuranceByKy(Long insKy);

    void update(Insurance insurance);

    void delete(Long insuranceKy);

    }







