package iss4u.ehr.backoffice.parameterization.req_physicians.repositories;

import iss4u.ehr.backoffice.parameterization.req_physicians.entities.RequestingPhysician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestingPhysicianRepository extends JpaRepository<RequestingPhysician, Long> {
}
