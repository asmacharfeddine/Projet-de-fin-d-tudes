package iss4u.ehr.backoffice.parameterization.structure.repositories;

import iss4u.ehr.backoffice.parameterization.structure.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByServiceNm(String serviceNm);
}
