package iss4u.ehr.backoffice.parameterization.human_resources.services;


import iss4u.ehr.backoffice.parameterization.human_resources.entities.Staff;
import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrp;

import java.util.List;
import java.util.Optional;

public interface StaffGrpService {
    void create(StaffGrp staffGrp);

    List<StaffGrp> retrieveStaffGrps();

    Optional<StaffGrp> getStaffGrpByKy(Long staffGrpKy);

    void update(Long staffGrpKy, StaffGrp updatedStaffGrp);

    void delete(Long staffGrpKy);

    List<Staff> getChildElements(StaffGrp staffGrp);

    void addChildElement(Optional<StaffGrp> staffGrp, Staff staff);
}
