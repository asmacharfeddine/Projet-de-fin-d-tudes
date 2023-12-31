package iss4u.ehr.backoffice.parameterization.human_resources.services;

import iss4u.ehr.backoffice.parameterization.human_resources.entities.StaffGrpLink;

import java.util.List;

public interface StaffGrpLinkService {

    List<StaffGrpLink> retrieveStaffGrpLinks();

    void assignStaffToGroup(Long staffKy, Long staffGrpKy);

    void unassignStaffFromGroup(Long staffKy, Long staffGrpKy);
}
