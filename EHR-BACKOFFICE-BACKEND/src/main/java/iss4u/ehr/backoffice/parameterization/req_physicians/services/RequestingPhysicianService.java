package iss4u.ehr.backoffice.parameterization.req_physicians.services;

import iss4u.ehr.backoffice.parameterization.req_physicians.entities.RequestingPhysician;

import java.util.List;
import java.util.Optional;

public interface RequestingPhysicianService {
    List<RequestingPhysician> getAllRequestingPhysicians();

    Optional<RequestingPhysician> getRequestingPhysicianById(Long id);

    RequestingPhysician saveRequestingPhysician(RequestingPhysician requestingPhysician);

    void updateRequestingPhysician(Long id, RequestingPhysician requestingPhysician);

    void deleteRequestingPhysician(Long id);
}
