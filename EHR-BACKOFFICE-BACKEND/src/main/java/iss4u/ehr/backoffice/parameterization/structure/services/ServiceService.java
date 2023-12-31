package iss4u.ehr.backoffice.parameterization.structure.services;

import iss4u.ehr.backoffice.parameterization.structure.entities.Service;
import iss4u.ehr.backoffice.parameterization.structure.entities.ServiceArea;

import java.util.List;
import java.util.Optional;

public interface ServiceService {

    void create(Service service);

    List<Service> retrieveServices();

    Optional<Service> getServiceByKy(Long serviceKy);

    Service getServiceByNm(String serviceNm);

    String getServiceNameByKey(Long serviceKy);

    void update(Long serviceKy, Service updatedService);

    void delete(Long serviceKy);

    List<ServiceArea> getChildElements(Service service);

    void addChildElement(Service service, ServiceArea serviceArea);
}
