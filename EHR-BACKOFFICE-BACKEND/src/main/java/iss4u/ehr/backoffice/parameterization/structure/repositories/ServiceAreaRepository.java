package iss4u.ehr.backoffice.parameterization.structure.repositories;

import iss4u.ehr.backoffice.parameterization.structure.entities.ServiceArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceAreaRepository extends JpaRepository<ServiceArea, Long> {
    ServiceArea findByServAreaNm(String servAreaNm);
}
