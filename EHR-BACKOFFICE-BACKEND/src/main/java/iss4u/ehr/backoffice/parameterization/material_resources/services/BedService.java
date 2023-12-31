package iss4u.ehr.backoffice.parameterization.material_resources.services;


import iss4u.ehr.backoffice.parameterization.material_resources.entities.Bed;

import java.util.List;
import java.util.Optional;

public interface BedService {
    void create(Bed bed);

    List<Bed> retrieveBeds();

    Optional<Bed> getBedByKy(Long bedKy);

    void update(Long bedKy, Bed updatedBed);

    void delete(Long bedKy);
}
