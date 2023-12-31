package iss4u.ehr.backoffice.parameterization.req_physicians.services.implementation;

import iss4u.ehr.backoffice.parameterization.req_physicians.entities.RequestingPhysician;
import iss4u.ehr.backoffice.parameterization.req_physicians.repositories.RequestingPhysicianRepository;
import iss4u.ehr.backoffice.parameterization.req_physicians.services.RequestingPhysicianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RequestingPhysicianServiceImpl implements RequestingPhysicianService {
    private final RequestingPhysicianRepository requestingPhysicianRepository;

    @Autowired
    public RequestingPhysicianServiceImpl(RequestingPhysicianRepository requestingPhysicianRepository) {
        this.requestingPhysicianRepository = requestingPhysicianRepository;
    }

    @Override
    public List<RequestingPhysician> getAllRequestingPhysicians() {
        return requestingPhysicianRepository.findAll();
    }

    @Override
    public Optional<RequestingPhysician> getRequestingPhysicianById(Long id) {
        return requestingPhysicianRepository.findById(id);
    }

    @Override
    public RequestingPhysician saveRequestingPhysician(RequestingPhysician requestingPhysician) {
        return requestingPhysicianRepository.save(requestingPhysician);
    }

    @Override
    public void updateRequestingPhysician(Long id, RequestingPhysician requestingPhysician) {
        if (requestingPhysicianRepository.existsById(id)) {
            requestingPhysician.setRequestPhysKy(id); // Ensure the ID is set correctly
            requestingPhysicianRepository.save(requestingPhysician);
        } else {
            // Handle the case where the entity with the given ID doesn't exist
            throw new EntityNotFoundException("RequestingPhysician with ID " + id + " not found");
        }
    }

    @Override
    public void deleteRequestingPhysician(Long id) {
        requestingPhysicianRepository.deleteById(id);
    }
}
