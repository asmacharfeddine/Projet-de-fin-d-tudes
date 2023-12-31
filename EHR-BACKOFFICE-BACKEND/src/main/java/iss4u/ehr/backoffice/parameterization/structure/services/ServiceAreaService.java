package iss4u.ehr.backoffice.parameterization.structure.services;

import iss4u.ehr.backoffice.parameterization.structure.entities.ExploitationUnit;
import iss4u.ehr.backoffice.parameterization.structure.entities.ServiceArea;

import java.util.List;
import java.util.Optional;

public interface ServiceAreaService {
    void create(ServiceArea serviceArea);

    List<ServiceArea> retrieveServiceAreas();

    Optional<ServiceArea> getServiceAreaByKy(Long serviceAreaKy);

    ServiceArea getServiceAreaByNm(String serviceAreaNm);

    String getServiceAreaNameByKey(Long serviceAreaKy);

    void update(Long serviceAreaKy, ServiceArea updatedServiceArea);

    void delete(Long serviceAreaKy);

    List<ExploitationUnit> getChildElements(ServiceArea serviceArea);

    void addChildElement(ServiceArea serviceArea, ExploitationUnit exploitationUnit);
}
